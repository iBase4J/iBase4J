package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysUnitMapper;
import org.ibase4j.model.sys.SysUnit;
import org.ibase4j.service.sys.SysUnitService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysUnit")
public class SysUnitServiceImpl extends BaseServiceImpl<SysUnit, SysUnitMapper> implements SysUnitService {

}
