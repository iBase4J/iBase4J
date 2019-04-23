package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysUnitMapper;
import org.ibase4j.model.SysUnit;
import org.ibase4j.service.SysUnitService;
import org.springframework.cache.annotation.CacheConfig;

import org.apache.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysUnit")
@Service(interfaceClass = SysUnitService.class)
public class SysUnitServiceImpl extends BaseServiceImpl<SysUnit, SysUnitMapper> implements SysUnitService {

}
