package org.ibase4j.service.impl;

import org.ibase4j.model.SysUnit;
import org.ibase4j.service.ISysUnitService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service(interfaceClass=ISysUnitService.class)
@MotanService(interfaceClass=ISysUnitService.class)
@CacheConfig(cacheNames = "sysUnit")
public class SysUnitServiceImpl extends BaseService<SysUnit> implements ISysUnitService {

}
