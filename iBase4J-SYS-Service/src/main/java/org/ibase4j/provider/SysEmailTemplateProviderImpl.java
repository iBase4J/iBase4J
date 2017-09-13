package org.ibase4j.provider;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.model.SysEmailTemplate;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailTemplate")
@Service(interfaceClass = ISysEmailTemplateProvider.class)
public class SysEmailTemplateProviderImpl extends BaseProviderImpl<SysEmailTemplate>
		implements ISysEmailTemplateProvider {

}
