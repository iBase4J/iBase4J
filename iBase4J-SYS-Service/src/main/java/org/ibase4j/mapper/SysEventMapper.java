package org.ibase4j.mapper;

import org.apache.ibatis.annotations.CacheNamespace;

import top.ibase4j.core.base.BaseMapper;
import top.ibase4j.core.support.cache.mybatis.EhcacheRedisCache;
import top.ibase4j.model.SysEvent;

@CacheNamespace(implementation = EhcacheRedisCache.class)
public interface SysEventMapper extends BaseMapper<SysEvent> {
}