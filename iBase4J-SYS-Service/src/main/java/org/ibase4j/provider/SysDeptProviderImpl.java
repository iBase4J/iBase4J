package org.ibase4j.provider;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.model.SysDept;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysDept")
@Service(interfaceClass = ISysDeptProvider.class)
public class SysDeptProviderImpl extends BaseProviderImpl<SysDept> implements ISysDeptProvider {
	
}
