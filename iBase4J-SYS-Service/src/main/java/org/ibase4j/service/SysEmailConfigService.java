package org.ibase4j.service;

import org.ibase4j.model.SysEmailConfig;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysEmailConfig")
public class SysEmailConfigService extends BaseService<SysEmailConfig> {

}
