package org.ibase4j.service;

import org.ibase4j.model.SysNews;
import org.ibase4j.service.ISysNewsService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysNews")
@Service(interfaceClass = ISysNewsService.class)
public class SysNewsServiceImpl extends BaseService<SysNews> implements ISysNewsService {

}
