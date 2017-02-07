package org.ibase4j.provider.sys;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysEmail;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmail")
@DubboService(interfaceClass = ISysEmailProvider.class)
public class SysEmailProviderImpl extends BaseProviderImpl<SysEmail> implements ISysEmailProvider {

}
