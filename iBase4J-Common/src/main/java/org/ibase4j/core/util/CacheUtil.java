package org.ibase4j.core.util;

import org.ibase4j.core.support.cache.CacheManager;

public class CacheUtil {
    private static CacheManager cacheManager;
    private static CacheManager lockManager;

    public static void setCacheManager(CacheManager cacheManager) {
        CacheUtil.cacheManager = cacheManager;
    }

    public static void setLockManager(CacheManager cacheManager) {
        CacheUtil.lockManager = cacheManager;
    }

    public static CacheManager getCache() {
        return cacheManager;
    }

    public static CacheManager getLockManager() {
        return lockManager;
    }

    /** 获取锁 */
    public static boolean tryLock(String key) {
        int expires = 1000 * PropertiesUtil.getInt("redis.lock.expires", 180);
        return lockManager.setnx(key, expires);
    }

    /** 获取锁 */
    public static boolean getLock(String key) {
        return lockManager.lock(key);
    }

    /** 解锁 */
    public static void unlock(String key) {
        lockManager.unlock(key);
    }
}
