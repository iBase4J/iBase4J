package org.ibase4j.service;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.provider.ISysCacheProvider;
import org.springframework.stereotype.Service;

@Service
public class SysCacheService {
	@DubboReference(async = true)
	private ISysCacheProvider sysCacheProvider;

	public void flushCache() {
		sysCacheProvider.flush();
	}
}
