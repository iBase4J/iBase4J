package org.ibase4j.provider.sys;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.util.RedisUtil;
import org.ibase4j.provider.sys.ISysCacheProvider;

@DubboService(interfaceClass = ISysCacheProvider.class)
public class SysCacheProviderImpl implements ISysCacheProvider {

	// 清缓存
	public void flush() {
		RedisUtil.delAll("*:sysDics:*");
		RedisUtil.delAll("*:sysDicMap:*");
		RedisUtil.delAll("*:getAuthorize:*");
		RedisUtil.delAll("*:sysPermission:*");
	}
}