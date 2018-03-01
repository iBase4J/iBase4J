package org.ibase4j.service.sys;

import org.ibase4j.model.sys.SysNews;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysNews")
public class SysNewsService extends BaseService<SysNews> {

}
