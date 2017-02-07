package org.ibase4j.provider.sys;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysEmailTemplate;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailTemplate")
@DubboService(interfaceClass = ISysEmailTemplateProvider.class)
public class SysEmailTemplateProviderImpl extends BaseProviderImpl<SysEmailTemplate>
		implements ISysEmailTemplateProvider {

}
