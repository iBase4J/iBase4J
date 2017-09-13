package org.ibase4j.provider;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.model.SysEmailConfig;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmailConfig")
@Service(interfaceClass = ISysEmailConfigProvider.class)
public class SysEmailConfigProviderImpl extends BaseProviderImpl<SysEmailConfig> implements ISysEmailConfigProvider {

}
