package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysMsgConfigMapper;
import org.ibase4j.model.sys.SysMsgConfig;
import org.ibase4j.service.sys.ISysMsgConfigService;
import org.springframework.cache.annotation.CacheConfig;

import top.ibase4j.core.base.BaseService;

/**
 * <p>
 *   服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@CacheConfig(cacheNames = "sysMsgConfig")
public class SysMsgConfigServiceImpl extends BaseService<SysMsgConfig, SysMsgConfigMapper>
implements ISysMsgConfigService {

}
