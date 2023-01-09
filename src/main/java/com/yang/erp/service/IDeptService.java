package com.yang.erp.service;

import com.yang.erp.dto.DeptTreeDto;
import com.yang.erp.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.erp.vo.DeptVo;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
public interface IDeptService extends IService<Dept> {

    /**
     * 保存部门
     *
     * @param deptVo deptVo
     */
    void saveDept(DeptVo deptVo);

    /**
     * 更新部门数据
     *
     * @param deptVo deptVo
     */
    void updateDept(DeptVo deptVo);

    /**
     * 查询部门树
     *
     * @return List<DeptTreeDto>
     */
    List<DeptTreeDto> deptTree();


}
