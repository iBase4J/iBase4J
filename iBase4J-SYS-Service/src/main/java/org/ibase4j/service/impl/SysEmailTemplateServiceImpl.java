package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysEmailTemplateMapper;
import org.ibase4j.model.SysEmailTemplate;
import org.ibase4j.service.SysEmailTemplateService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysEmailTemplate")
public class SysEmailTemplateServiceImpl extends BaseServiceImpl<SysEmailTemplate, SysEmailTemplateMapper>
implements SysEmailTemplateService {

}
