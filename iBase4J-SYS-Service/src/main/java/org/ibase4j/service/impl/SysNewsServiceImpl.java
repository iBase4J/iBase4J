package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysNewsMapper;
import org.ibase4j.model.SysNews;
import org.ibase4j.service.SysNewsService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysNews")
public class SysNewsServiceImpl extends BaseServiceImpl<SysNews, SysNewsMapper> implements SysNewsService {

}
