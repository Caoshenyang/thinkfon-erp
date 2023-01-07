package com.yang.erp.service.impl;

import com.yang.erp.domain.entity.User;
import com.yang.erp.mapper.UserMapper;
import com.yang.erp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
