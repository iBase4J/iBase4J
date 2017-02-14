package org.ibase4j.core.util;

import org.ibase4j.core.support.cache.CacheManager;
import org.ibase4j.core.support.cache.RedissonHelper;

public final class CacheUtil {
	private CacheUtil() {
	}

	public static CacheManager getCache() {
		return new RedissonHelper();
	}

	/** 获取锁 */
	public static boolean getLock(String key) {
		if (!getCache().exists(key)) {
			synchronized (CacheUtil.class) {
				if (!getCache().exists(key)) {
					if (getCache().setnx(key, String.valueOf(System.currentTimeMillis()))) {
						return true;
					}
				}
			}
		}
		int expires = 1000 * 60 * 3;
		String currentValue = (String) getCache().get(key);
		if (currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis() - expires) {
			unlock(key);
			return getLock(key);
		}
		return false;
	}

	public static void unlock(String key) {
		getCache().unlock(key);
	}
}