package com.yang.erp.service.impl;

import com.yang.erp.common.advice.ErpException;
import com.yang.erp.common.constant.ResponseCodeEnum;
import com.yang.erp.common.utils.LevelUtil;
import com.yang.erp.domain.dto.DeptDto;
import com.yang.erp.domain.entity.Dept;
import com.yang.erp.mapper.DeptMapper;
import com.yang.erp.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public void saveDept(DeptDto deptDto) {
        if (checkExist(deptDto.getParentId(), deptDto.getName(), deptDto.getId())) {
            throw new ErpException(ResponseCodeEnum.DEPT_EXIST);
        }
        Dept dept = Dept.builder()
                .name(deptDto.getName())
                .parentId(deptDto.getParentId())
                .sort(deptDto.getSort())
                .remark(deptDto.getRemark())
                .build();
        // 查询出上级的层级 计算出当前层级
        dept.setLevel(LevelUtil.calculateLevel(getLevel(deptDto.getParentId()), deptDto.getParentId()));
        dept.setCreator(1L);// todo 创建人
        dept.setOperator(1L);// todo 操作人
        this.save(dept);
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
