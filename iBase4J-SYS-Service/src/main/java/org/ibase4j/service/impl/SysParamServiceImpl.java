package org.ibase4j.service.impl;

import org.ibase4j.model.SysParam;
import org.ibase4j.service.ISysParamService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysParam")
@Service(interfaceClass = ISysParamService.class)
@MotanService(interfaceClass = ISysParamService.class)
public class SysParamServiceImpl extends BaseService<SysParam> implements ISysParamService {

}
