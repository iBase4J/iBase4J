package org.ibase4j.provider;

import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.SysParam;
import org.ibase4j.provider.ISysParamProvider;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysParam")
@DubboService(interfaceClass = ISysParamProvider.class)
public class SysParamProviderImpl extends BaseProviderImpl<SysParam> implements ISysParamProvider {

}
