package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysNoticeMapper;
import org.ibase4j.model.SysNotice;
import org.ibase4j.service.ISysNoticeService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysNotice")
@Service(interfaceClass = ISysNoticeService.class)
@MotanService(interfaceClass = ISysNoticeService.class)
public class SysNoticeServiceImpl extends BaseService<SysNotice, SysNoticeMapper> implements ISysNoticeService {

}
