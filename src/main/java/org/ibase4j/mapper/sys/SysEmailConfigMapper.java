package org.ibase4j.mapper.sys;

import org.apache.ibatis.annotations.CacheNamespace;
import org.ibase4j.model.sys.SysEmailConfig;

import top.ibase4j.core.base.BaseMapper;
import top.ibase4j.core.support.cache.mybatis.EhcacheRedisCache;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-01-29
 */
@CacheNamespace(implementation = EhcacheRedisCache.class)
public interface SysEmailConfigMapper extends BaseMapper<SysEmailConfig> {

}