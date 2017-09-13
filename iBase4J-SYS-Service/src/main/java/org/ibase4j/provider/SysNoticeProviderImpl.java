package org.ibase4j.provider;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.model.SysNotice;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysNoticeTemplate")
@Service(interfaceClass = ISysNoticeProvider.class)
public class SysNoticeProviderImpl extends BaseProviderImpl<SysNotice> implements ISysNoticeProvider {

}
