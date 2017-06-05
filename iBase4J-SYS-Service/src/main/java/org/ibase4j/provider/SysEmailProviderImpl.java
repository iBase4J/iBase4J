package org.ibase4j.provider;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.SysEmail;
import org.ibase4j.provider.ISysEmailProvider;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmail")
@DubboService(interfaceClass = ISysEmailProvider.class)
public class SysEmailProviderImpl extends BaseProviderImpl<SysEmail> implements ISysEmailProvider {

}
