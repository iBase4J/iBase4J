package org.ibase4j.provider.sys;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysEmailConfig;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailConfig")
@DubboService(interfaceClass = ISysEmailConfigProvider.class)
public class SysEmailConfigProviderImpl extends BaseProviderImpl<SysEmailConfig> implements ISysEmailConfigProvider {

}
