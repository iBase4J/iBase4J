/**
 * 
 */
package org.ibase4j.core.support.cache.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * @author ShenHuaJie
 * @version 2017年3月24日 下午8:54:08
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RedisCache<K, V> implements Cache<K, V> {
	private final Logger logger = LogManager.getLogger();
	private RedisTemplate redisTemplate;

	/**
	 * The Redis key prefix for the sessions
	 */
	private String keyPrefix = "shiro_redis_session:";

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

	/**
	 * Constructs a cache instance with the specified Redis manager and using a
	 * custom key prefix.
	 * 
	 * @param prefix
	 *            The Redis key prefix
	 */
	public RedisCache(String prefix, RedisTemplate redisTemplate) {
		this.keyPrefix = prefix;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public V get(K key) throws CacheException {
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");
		V value = (V) redisTemplate.boundValueOps(getKey(key)).get();
		return value;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		logger.debug("根据key从存储 key [" + key + "]");
		redisTemplate.boundValueOps(getKey(key)).set(value);
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		logger.debug("从redis中删除 key [" + key + "]");
		V previous = get(key);
		redisTemplate.delete(getKey(key));
		return previous;
	}

	@Override
	public void clear() throws CacheException {
		logger.debug("从redis中删除所有元素");
		redisTemplate.delete(redisTemplate.keys(this.keyPrefix + "*"));
	}

	@Override
	public int size() {
		return redisTemplate.keys(this.keyPrefix + "*").size();
	}

	@Override
	public Set<K> keys() {
		Set<Object> keys = redisTemplate.keys(this.keyPrefix + "*");
		if (CollectionUtils.isEmpty(keys)) {
			return Collections.emptySet();
		} else {
			Set<K> newKeys = new HashSet<K>();
			for (Object key : keys) {
				newKeys.add((K) key);
			}
			return newKeys;
		}
	}

	@Override
	public Collection<V> values() {
		Set<Object> keys = redisTemplate.keys(this.keyPrefix + "*");
		if (!CollectionUtils.isEmpty(keys)) {
			List<V> values = new ArrayList<V>(keys.size());
			for (Object key : keys) {
				V value = get((K) key);
				if (value != null) {
					values.add(value);
				}
			}
			return Collections.unmodifiableList(values);
		} else {
			return Collections.emptyList();
		}
	}

	private String getKey(K key) {
		return this.keyPrefix + key;
	}
}
