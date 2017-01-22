package org.ibase4j.core.util;

import org.ibase4j.core.support.cache.CacheManager;
import org.ibase4j.core.support.cache.RedisHelper;

public final class CacheUtil {
	private CacheUtil() {
	}

	public static CacheManager getCache() {
		return new RedisHelper();
	}
}
