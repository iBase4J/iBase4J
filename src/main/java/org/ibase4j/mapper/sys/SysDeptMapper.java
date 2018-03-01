package org.ibase4j.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.ibase4j.model.sys.SysDept;

import top.ibase4j.core.base.BaseMapper;
import top.ibase4j.core.support.cache.mybatis.EhcacheRedisCache;

@CacheNamespace(implementation = EhcacheRedisCache.class)
public interface SysDeptMapper extends BaseMapper<SysDept> {

	List<Long> selectIdPage(@Param("cm") SysDept sysDept);
}