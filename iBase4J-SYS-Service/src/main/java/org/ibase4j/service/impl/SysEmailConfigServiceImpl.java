package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysEmailConfigMapper;
import org.ibase4j.model.SysEmailConfig;
import org.ibase4j.service.SysEmailConfigService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysEmailConfig")
public class SysEmailConfigServiceImpl extends BaseServiceImpl<SysEmailConfig, SysEmailConfigMapper>
implements SysEmailConfigService {

}
