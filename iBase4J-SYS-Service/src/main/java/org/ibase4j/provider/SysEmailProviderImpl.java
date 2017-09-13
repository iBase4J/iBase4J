package org.ibase4j.provider;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.model.SysEmail;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmail")
@Service(interfaceClass = ISysEmailProvider.class)
public class SysEmailProviderImpl extends BaseProviderImpl<SysEmail> implements ISysEmailProvider {

}
