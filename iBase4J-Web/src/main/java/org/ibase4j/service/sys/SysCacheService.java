package org.ibase4j.service.sys;

import org.ibase4j.provider.sys.SysCacheProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class SysCacheService {
	@Reference
	private SysCacheProvider sysCacheProvider;

	public void flushCache() {
		sysCacheProvider.flush();
	}
}
