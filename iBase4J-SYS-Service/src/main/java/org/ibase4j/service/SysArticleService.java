package org.ibase4j.service;

import org.ibase4j.model.SysArticle;
import org.ibase4j.core.base.BaseService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章  服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Service
@CacheConfig(cacheNames = "sysArticle")
public class SysArticleService extends BaseService<SysArticle> {
	
}