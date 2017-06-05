package org.ibase4j.provider;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.SysEmailTemplate;
import org.ibase4j.provider.ISysEmailTemplateProvider;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailTemplate")
@DubboService(interfaceClass = ISysEmailTemplateProvider.class)
public class SysEmailTemplateProviderImpl extends BaseProviderImpl<SysEmailTemplate>
		implements ISysEmailTemplateProvider {

}
