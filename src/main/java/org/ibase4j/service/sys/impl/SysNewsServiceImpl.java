package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysNewsMapper;
import org.ibase4j.model.sys.SysNews;
import org.ibase4j.service.sys.ISysNewsService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysNews")
public class SysNewsServiceImpl extends BaseService<SysNews, SysNewsMapper> implements ISysNewsService {

}
