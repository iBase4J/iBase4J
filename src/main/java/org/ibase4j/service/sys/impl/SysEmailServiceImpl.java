package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysEmailMapper;
import org.ibase4j.model.sys.SysEmail;
import org.ibase4j.service.sys.SysEmailService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysEmail")
public class SysEmailServiceImpl extends BaseServiceImpl<SysEmail, SysEmailMapper> implements SysEmailService {

}
