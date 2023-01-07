package com.yang.erp.service.impl;

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

}
