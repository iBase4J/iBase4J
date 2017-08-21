package org.ibase4j.core.support.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ibase4j.core.util.CacheUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RType;
import org.redisson.api.RedissonClient;

/**
 * 
 * Redis缓存辅助类
 * 
 */
public class RedissonHelper implements CacheManager {
	private RedissonClient redissonClient;
	private final Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
		CacheUtil.setCacheManager(this);
	}

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
		redissonClient.getKeys().delete(key);
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
	 * 
	 * 在某段时间后失效
	 *
	 * 
	 * 
	 * @return
	 * 
	 */
	private final void expire(final RBucket<Object> bucket, final int seconds) {
		bucket.expire(seconds, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * 在某个时间点失效
	 *
	 * 
	 * 
	 * @param key
	 * 
	 * @param unixTime
	 * 
	 * @return
	 * 
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

	public void hset(String key, Serializable field, Serializable value) {
		redissonClient.getMap(key).put(field, value);
	}

	public Object hget(String key, Serializable field) {
		return redissonClient.getMap(key).get(field);
	}

	public void hdel(String key, Serializable field) {
		redissonClient.getMap(key).remove(field);
	}

	public void sadd(String key, Serializable value) {
		redissonClient.getSet(key).add(value);
	}

	public Set<Object> sall(String key) {
		return redissonClient.getSet(key).readAll();
	}

	public boolean sdel(String key, Serializable value) {
		return redissonClient.getSet(key).remove(value);
	}

	public boolean lock(String key) {
		return redissonClient.getLock(key).tryLock();
	}

	public void unlock(String key) {
		redissonClient.getLock(key).unlock();
	}

	public boolean setnx(String key, long value) {
		try {
			return redissonClient.getLock(key).tryLock(value, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			return false;
		}
	}
}