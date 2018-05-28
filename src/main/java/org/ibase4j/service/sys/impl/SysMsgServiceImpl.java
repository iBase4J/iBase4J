package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysMsgMapper;
import org.ibase4j.model.sys.SysMsg;
import org.ibase4j.service.sys.SysMsgService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * <p>
 * 短信  服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Service
@CacheConfig(cacheNames = "sysMsg")
public class SysMsgServiceImpl extends BaseServiceImpl<SysMsg, SysMsgMapper> implements SysMsgService {

}
