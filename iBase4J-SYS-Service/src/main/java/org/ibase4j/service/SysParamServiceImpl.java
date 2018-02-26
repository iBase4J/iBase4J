package org.ibase4j.service;

import org.ibase4j.model.SysParam;
import org.ibase4j.service.ISysParamService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysParam")
@Service(interfaceClass = ISysParamService.class)
public class SysParamServiceImpl extends BaseService<SysParam> implements ISysParamService {

}
