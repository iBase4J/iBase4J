package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysEmailConfigMapper;
import org.ibase4j.model.sys.SysEmailConfig;
import org.ibase4j.service.sys.SysEmailConfigService;
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
