package org.ibase4j.service;

import org.ibase4j.provider.ISysCacheProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class SysCacheService {
	@Reference(async = true)
	private ISysCacheProvider sysCacheProvider;

	public void flushCache() {
		sysCacheProvider.flush();
	}
}
