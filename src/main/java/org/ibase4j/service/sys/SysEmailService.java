package org.ibase4j.service.sys;

import org.ibase4j.mapper.sys.SysEmailMapper;
import org.ibase4j.model.sys.SysEmail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysEmail")
public class SysEmailService extends BaseService<SysEmail, SysEmailMapper> {

}
