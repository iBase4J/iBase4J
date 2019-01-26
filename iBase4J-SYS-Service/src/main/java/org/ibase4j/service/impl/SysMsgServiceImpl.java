package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysMsgMapper;
import org.ibase4j.model.SysMsg;
import org.ibase4j.service.SysMsgService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * <p>
 * 短信  服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@CacheConfig(cacheNames = "sysMsg")
@Service(interfaceClass = SysMsgService.class)
public class SysMsgServiceImpl extends BaseServiceImpl<SysMsg, SysMsgMapper> implements SysMsgService {

}
