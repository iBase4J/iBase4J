package org.ibase4j.provider.sys;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysNews;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysNews")
@DubboService(interfaceClass = ISysNewsProvider.class)
public class SysNewsProviderImpl extends BaseProviderImpl<SysNews> implements ISysNewsProvider {

}
