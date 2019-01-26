package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysEmailMapper;
import org.ibase4j.model.SysEmail;
import org.ibase4j.service.SysEmailService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmail")
@Service(interfaceClass = SysEmailService.class)
public class SysEmailServiceImpl extends BaseServiceImpl<SysEmail, SysEmailMapper> implements SysEmailService {

}
