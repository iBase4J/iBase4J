package org.ibase4j.provider.sys;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysNotice;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysNoticeTemplate")
@DubboService(interfaceClass = ISysNoticeProvider.class)
public class SysNoticeProviderImpl extends BaseProviderImpl<SysNotice> implements ISysNoticeProvider {

}
