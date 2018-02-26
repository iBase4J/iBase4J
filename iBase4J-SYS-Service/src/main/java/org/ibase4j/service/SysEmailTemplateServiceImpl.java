package org.ibase4j.service;

import org.ibase4j.model.SysEmailTemplate;
import org.ibase4j.service.ISysEmailTemplateService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailTemplate")
@Service(interfaceClass = ISysEmailTemplateService.class)
public class SysEmailTemplateServiceImpl extends BaseService<SysEmailTemplate>
		implements ISysEmailTemplateService {

}
