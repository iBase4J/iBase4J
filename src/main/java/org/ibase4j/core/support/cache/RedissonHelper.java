package org.ibase4j.core.support.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Redis缓存辅助类
 */
public class RedissonHelper implements CacheManager {
	@Autowired
	private RedissonClient redissonClient;
	private Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

	private RBucket<Object> getRedisBucket(String key) {
		return redissonClient.getBucket(key);
	}

	public final Object get(final String key) {
		RBucket<Object> temp = getRedisBucket(key);
		expire(temp, EXPIRE);
		return temp.get();
	}

	public final void set(final String key, final Serializable value) {
		RBucket<Object> temp = getRedisBucket(key);
		temp.set(value);
		expire(temp, EXPIRE);
	}

	public final void set(final String key, final Serializable value, int seconds) {
		RBucket<Object> temp = getRedisBucket(key);
		temp.set(value);
		expire(temp, seconds);
	}

	public final void multiSet(final Map<String, Object> temps) {
		redissonClient.getBuckets().set(temps);
	}

	public final Boolean exists(final String key) {
		RBucket<Object> temp = getRedisBucket(key);
		return temp.isExists();
	}

	public final void del(final String key) {
		redissonClient.getKeys().deleteAsync(key);
	}

	public final void delAll(final String pattern) {
		redissonClient.getKeys().deleteByPattern(pattern);
	}

	public final String type(final String key) {
		RType type = redissonClient.getKeys().getType(key);
		if (type == null) {
			return null;
		}
		return type.getClass().getName();
	}

	/**
	 * 在某段时间后失效
	 *
	 * @return
	 */
	private final void expire(final RBucket<Object> bucket, final int seconds) {
		bucket.expire(seconds, TimeUnit.SECONDS);
	}

	/**
	 * 在某个时间点失效
	 *
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public final Boolean expireAt(final String key, final long unixTime) {
		return redissonClient.getBucket(key).expireAt(new Date(unixTime));
	}

	public final Long ttl(final String key) {
		RBucket<Object> rBucket = getRedisBucket(key);
		return rBucket.remainTimeToLive();
	}

	public final Object getSet(final String key, final Serializable value) {
		RBucket<Object> rBucket = getRedisBucket(key);
		return rBucket.getAndSet(value);
	}

	public Set<Object> getAll(String pattern) {
		Set<Object> set = InstanceUtil.newHashSet();
		Iterable<String> keys = redissonClient.getKeys().getKeysByPattern(pattern);
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			set.add(getRedisBucket(key).get());
		}
		return set;
	}

	public Boolean expire(String key, int seconds) {
		RBucket<Object> bucket = getRedisBucket(key);
		expire(bucket, seconds);
		return true;
	}

	public void hset(String key, String field, String value) {
		redissonClient.getMap(key).put(field, value);
	}

	public Object hget(String key, String field) {
		return redissonClient.getMap(key).get(field);
	}

	public void hdel(String key, String field) {
		redissonClient.getMap(key).remove(field);
	}

	public boolean lock(String key) {
		return redissonClient.getLock(key).tryLock();
	}

	public void unlock(String key) {
		redissonClient.getLock(key).unlock();
	}
}
