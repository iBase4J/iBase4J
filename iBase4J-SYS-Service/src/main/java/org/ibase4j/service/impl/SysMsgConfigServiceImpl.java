package org.ibase4j.service.impl;

import org.ibase4j.model.SysMsgConfig;
import org.ibase4j.service.ISysMsgConfigService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * <p>
 *   服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Service(interfaceClass = ISysMsgConfigService.class)
@MotanService(interfaceClass = ISysMsgConfigService.class)
@CacheConfig(cacheNames = "sysMsgConfig")
public class SysMsgConfigServiceImpl extends BaseService<SysMsgConfig> implements ISysMsgConfigService {
	
}