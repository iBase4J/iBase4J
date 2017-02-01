package org.ibase4j.provider.sys;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysUnit;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysUnit")
@DubboService(interfaceClass = ISysUnitProvider.class)
public class SysUnitProviderImpl extends BaseProviderImpl<SysUnit> implements ISysUnitProvider {

}
