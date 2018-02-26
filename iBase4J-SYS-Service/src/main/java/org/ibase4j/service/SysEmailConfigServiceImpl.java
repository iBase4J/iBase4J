package org.ibase4j.service;

import org.ibase4j.model.SysEmailConfig;
import org.ibase4j.service.ISysEmailConfigService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailConfig")
@Service(interfaceClass = ISysEmailConfigService.class)
public class SysEmailConfigServiceImpl extends BaseService<SysEmailConfig> implements ISysEmailConfigService {

}
