package org.ibase4j.service.impl;

import org.ibase4j.model.SysEmail;
import org.ibase4j.service.ISysEmailService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmail")
@Service(interfaceClass = ISysEmailService.class)
@MotanService(interfaceClass = ISysEmailService.class)
public class SysEmailServiceImpl extends BaseService<SysEmail> implements ISysEmailService {

}
