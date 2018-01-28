package org.ibase4j.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.ibase4j.model.SysArticle;

import top.ibase4j.core.base.BaseMapper;
import top.ibase4j.core.support.cache.mybatis.EhcacheRedisCache;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@CacheNamespace(implementation = EhcacheRedisCache.class)
public interface SysArticleMapper extends BaseMapper<SysArticle> {

}