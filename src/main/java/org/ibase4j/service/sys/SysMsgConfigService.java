package org.ibase4j.service.sys;

import org.ibase4j.mapper.sys.SysMsgConfigMapper;
import org.ibase4j.model.sys.SysMsgConfig;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * <p>
 *   服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Service
@CacheConfig(cacheNames = "sysMsgConfig")
public class SysMsgConfigService extends BaseService<SysMsgConfig, SysMsgConfigMapper> {
	
}