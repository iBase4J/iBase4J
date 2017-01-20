package org.ibase4j.service.sys;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.provider.sys.ISysCacheProvider;
import org.springframework.stereotype.Service;

@Service
public class SysCacheService {
	@DubboReference(async = true)
	private ISysCacheProvider sysCacheProvider;

	public void flushCache() {
		sysCacheProvider.flush();
	}
}
