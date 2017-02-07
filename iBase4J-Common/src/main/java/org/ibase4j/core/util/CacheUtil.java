package org.ibase4j.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.support.cache.CacheManager;
import org.ibase4j.core.support.cache.RedissonHelper;

public final class CacheUtil {
	private static Logger logger = LogManager.getLogger(CacheUtil.class);

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
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			logger.error(Constants.Exception_Head, e);
		}
		return getLock(key);
	}

	public static void unlock(String key) {
		getCache().unlock(key);
	}
}