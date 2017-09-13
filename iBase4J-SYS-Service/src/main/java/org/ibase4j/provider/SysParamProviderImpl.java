package org.ibase4j.provider;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.model.SysParam;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysParam")
@Service(interfaceClass = ISysParamProvider.class)
public class SysParamProviderImpl extends BaseProviderImpl<SysParam> implements ISysParamProvider {

}
