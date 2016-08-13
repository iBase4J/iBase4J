package org.ibase4j.provider;

import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.generator.SysEvent;
import org.ibase4j.provider.SysEventProvider;
import org.springframework.cache.annotation.CacheConfig;

import com.github.pagehelper.PageInfo;

@CacheConfig(cacheNames = "sysEvent")
@DubboService(interfaceClass = SysEventProvider.class)
public class SysEventProviderImpl extends BaseProviderImpl<SysEvent> implements SysEventProvider {
	
	public PageInfo<SysEvent> query(Map<String, Object> params) {
		return null;
	}
}
