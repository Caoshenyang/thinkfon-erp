package com.yang.erp.service.impl;

import com.yang.erp.domain.entity.Acl;
import com.yang.erp.mapper.AclMapper;
import com.yang.erp.service.IAclService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
@Service
public class AclServiceImpl extends ServiceImpl<AclMapper, Acl> implements IAclService {

}
