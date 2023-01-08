package com.yang.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.yang.erp.common.advice.ErpException;
import com.yang.erp.common.constant.ResponseCodeEnum;
import com.yang.erp.common.utils.LevelUtil;
import com.yang.erp.dto.DeptTreeDto;
import com.yang.erp.entity.Dept;
import com.yang.erp.mapper.DeptMapper;
import com.yang.erp.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.erp.vo.DeptVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiMapUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public void saveDept(DeptVo deptVo) {
        if (checkExist(deptVo.getParentId(), deptVo.getName(), deptVo.getId())) {
            throw new ErpException(ResponseCodeEnum.DEPT_EXIST);
        }
        Dept dept = Dept.builder()
                .name(deptVo.getName())
                .parentId(deptVo.getParentId())
                .sort(deptVo.getSort())
                .remark(deptVo.getRemark())
                .build();
        // 根据父节点计算出 当前层级
        dept.setLevel(LevelUtil.calculateLevel(getLevel(deptVo.getParentId()), deptVo.getParentId()));
        dept.setCreator(1L);// todo 创建人
        dept.setOperator(1L);// todo 操作人
        this.save(dept);
    }

    @Override
    public List<DeptTreeDto> deptTree() {
        List<Dept> deptList = baseMapper.selectList(new LambdaQueryWrapper<>());
        List<DeptTreeDto> deptDtoList = new ArrayList<>();
        deptList.forEach(dept -> {
            DeptTreeDto deptTreeDto = DeptTreeDto.adapt(dept);
            deptDtoList.add(deptTreeDto);
        });
        return buildDeptTree(deptDtoList);
    }

    /**
     * 组装部门树
     *
     * @param deptDtoList dtoList
     * @return List<DeptTreeDto>
     */
    private List<DeptTreeDto> buildDeptTree(List<DeptTreeDto> deptDtoList) {
        if (CollectionUtils.isEmpty(deptDtoList)) {
            return new ArrayList<>();
        }
        MultiValuedMap<String, DeptTreeDto> levelDeptMap = MultiMapUtils.newListValuedHashMap();
        List<DeptTreeDto> rootList = new ArrayList<>();
        deptDtoList.forEach(deptTreeDto -> {
            levelDeptMap.put(deptTreeDto.getLevel(), deptTreeDto);
            if (LevelUtil.ROOT.equals(deptTreeDto.getLevel())) {
                rootList.add(deptTreeDto);
            }
        });
        // 按照 sort 从小到大排序
        rootList.sort(Comparator.comparingInt(Dept::getSort));
        // 递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    /**
     * 组装树形数据
     * @param deptDtoList 目标结果
     * @param level 当前层级
     * @param levelDeptMap 根据层级划分的map
     */
    private void transformDeptTree(List<DeptTreeDto> deptDtoList, String level, MultiValuedMap<String, DeptTreeDto> levelDeptMap) {
        deptDtoList.forEach(dto -> {
            // 遍历该层的每一个元素
            // 处理当前层级数据
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            // 处理下一层
            List<DeptTreeDto> tempDeptTreeDtoList = (List<DeptTreeDto>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptTreeDtoList)) {
                // 排序
                tempDeptTreeDtoList.sort(Comparator.comparingInt(Dept::getSort));
                // 设置下一层部门
                dto.setChildren(tempDeptTreeDtoList);
                // 进入到下一层处理
                transformDeptTree(tempDeptTreeDtoList, nextLevel, levelDeptMap);
            }
        });
    }

    /**
     * 校验同层级下是否存在相同名称部门
     *
     * @param parentId 父节点id
     * @param deptName 部门名称
     * @param deptId   部门id
     * @return true/false
     */
    private boolean checkExist(Long parentId, String deptName, Long deptId) {
        // todo 校验同级别下部门唯一
        return false;
    }

    /**
     * 查询部门的层级
     *
     * @param deptId 部门id
     * @return level
     */
    private String getLevel(Long deptId) {
        Dept dept = baseMapper.selectById(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }
}
