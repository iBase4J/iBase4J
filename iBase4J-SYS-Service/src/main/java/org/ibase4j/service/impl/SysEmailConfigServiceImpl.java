package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysEmailConfigMapper;
import org.ibase4j.model.SysEmailConfig;
import org.ibase4j.service.SysEmailConfigService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailConfig")
@Service(interfaceClass = SysEmailConfigService.class)
public class SysEmailConfigServiceImpl extends BaseServiceImpl<SysEmailConfig, SysEmailConfigMapper>
implements SysEmailConfigService {

}
