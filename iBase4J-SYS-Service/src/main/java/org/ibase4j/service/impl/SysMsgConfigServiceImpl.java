package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysMsgConfigMapper;
import org.ibase4j.model.SysMsgConfig;
import org.ibase4j.service.SysMsgConfigService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;

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
public class SysMsgConfigServiceImpl extends BaseServiceImpl<SysMsgConfig, SysMsgConfigMapper>
implements SysMsgConfigService {

}
