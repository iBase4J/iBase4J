package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysMsgMapper;
import org.ibase4j.model.SysMsg;
import org.ibase4j.service.ISysMsgService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * <p>
 * 短信  服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@CacheConfig(cacheNames = "sysMsg")
@Service(interfaceClass = ISysMsgService.class)
@MotanService(interfaceClass = ISysMsgService.class)
public class SysMsgServiceImpl extends BaseService<SysMsg, SysMsgMapper> implements ISysMsgService {
	
}