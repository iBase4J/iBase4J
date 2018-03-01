package org.ibase4j.mapper.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.ibase4j.model.sys.SysDic;

import top.ibase4j.core.base.BaseMapper;
import top.ibase4j.core.support.cache.mybatis.EhcacheRedisCache;

@CacheNamespace(implementation = EhcacheRedisCache.class)
public interface SysDicMapper extends BaseMapper<SysDic> {
    List<Long> selectIdPage(@Param("cm") Map<String, Object> params);
}