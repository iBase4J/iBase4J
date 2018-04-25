package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysUnitMapper;
import org.ibase4j.model.sys.SysUnit;
import org.ibase4j.service.sys.ISysUnitService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysUnit")
public class SysUnitServiceImpl extends BaseService<SysUnit, SysUnitMapper> implements ISysUnitService {

}
