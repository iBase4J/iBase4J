package org.ibase4j.service;

import org.ibase4j.model.SysNews;
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
