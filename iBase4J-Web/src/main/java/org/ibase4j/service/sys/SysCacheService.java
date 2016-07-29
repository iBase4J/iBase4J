package org.ibase4j.service.sys;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.provider.sys.SysCacheProvider;
import org.springframework.stereotype.Service;

@Service
public class SysCacheService {
    @DubboReference
	private SysCacheProvider sysCacheProvider;

	public void flushCache() {
		sysCacheProvider.flush();
	}
}
