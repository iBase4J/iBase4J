package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysEmailTemplateMapper;
import org.ibase4j.model.SysEmailTemplate;
import org.ibase4j.service.SysEmailTemplateService;
import org.springframework.cache.annotation.CacheConfig;

import org.apache.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailTemplate")
@Service(interfaceClass = SysEmailTemplateService.class)
public class SysEmailTemplateServiceImpl extends BaseServiceImpl<SysEmailTemplate, SysEmailTemplateMapper>
implements SysEmailTemplateService {

}
