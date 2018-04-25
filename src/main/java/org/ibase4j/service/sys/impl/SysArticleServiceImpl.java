package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysArticleMapper;
import org.ibase4j.model.sys.SysArticle;
import org.ibase4j.service.sys.ISysArticleService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

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
public class SysArticleServiceImpl extends BaseService<SysArticle, SysArticleMapper> implements ISysArticleService {

}
