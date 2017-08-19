/**
 * 
 */
package org.ibase4j.core.support.cache.shiro;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.ibase4j.core.Constants;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * @author ShenHuaJie
 * @version 2017年3月24日 下午8:50:14
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisCacheManager implements CacheManager {
	private final Logger logger = LogManager.getLogger();
	@Resource
	private RedisTemplate redisTemplate;

	// fast lookup by name map
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
	/**
	 * The Redis key prefix for caches
	 */
	private String keyPrefix = Constants.CACHE_NAMESPACE + "shiro_redis_cache:";

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	/**
	 * @param redisTemplate
	 */
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.cache.CacheManager#getCache(java.lang.String)
	 */
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.debug("获取名称为: " + name + " 的RedisCache实例");

		Cache c = caches.get(name);

		if (c == null) {
			// create a new cache instance
			c = new RedisCache<K, V>(keyPrefix, redisTemplate);
			// add it to the cache collection
			caches.put(name, c);
		}
		return c;
	}

}
