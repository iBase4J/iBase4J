package org.ibase4j.provider;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.SysUnit;
import org.ibase4j.provider.ISysUnitProvider;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysUnit")
@DubboService(interfaceClass = ISysUnitProvider.class)
public class SysUnitProviderImpl extends BaseProviderImpl<SysUnit> implements ISysUnitProvider {

}
