package org.ibase4j.service.sys;

import org.ibase4j.mapper.sys.SysEmailConfigMapper;
import org.ibase4j.model.sys.SysEmailConfig;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysEmailConfig")
public class SysEmailConfigService extends BaseService<SysEmailConfig, SysEmailConfigMapper> {

}
