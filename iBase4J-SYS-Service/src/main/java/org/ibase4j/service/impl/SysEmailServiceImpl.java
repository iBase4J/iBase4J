package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysEmailMapper;
import org.ibase4j.model.SysEmail;
import org.ibase4j.service.SysEmailService;
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
