package org.ibase4j.service.sys;

import org.ibase4j.model.sys.SysUnit;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysUnit")
public class SysUnitService extends BaseService<SysUnit> {

}
