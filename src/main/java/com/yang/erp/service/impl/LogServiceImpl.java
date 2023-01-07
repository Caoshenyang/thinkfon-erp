package com.yang.erp.service.impl;

import com.yang.erp.domain.entity.Log;
import com.yang.erp.mapper.LogMapper;
import com.yang.erp.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
