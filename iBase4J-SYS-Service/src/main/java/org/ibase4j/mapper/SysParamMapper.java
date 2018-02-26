package org.ibase4j.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.ibase4j.model.SysParam;

import top.ibase4j.core.base.BaseMapper;
import top.ibase4j.core.support.cache.mybatis.EhcacheRedisCache;

@CacheNamespace(implementation = EhcacheRedisCache.class)
public interface SysParamMapper extends BaseMapper<SysParam> {
}