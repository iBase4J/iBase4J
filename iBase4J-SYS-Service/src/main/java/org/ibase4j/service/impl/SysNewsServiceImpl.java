package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysNewsMapper;
import org.ibase4j.model.SysNews;
import org.ibase4j.service.ISysNewsService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysNews")
@Service(interfaceClass = ISysNewsService.class)
@MotanService(interfaceClass = ISysNewsService.class)
public class SysNewsServiceImpl extends BaseService<SysNews, SysNewsMapper> implements ISysNewsService {

}
