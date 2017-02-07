package org.ibase4j.core.support.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RType;
import org.redisson.api.RedissonClient;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;

/**
 * Redis缓存辅助类
 */
public class RedissonHelper extends CacheManager {

	private RedissonClient redisTemplate = null;
	private Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

	// 获取连接
	private RedissonClient getRedis() {
		if (redisTemplate == null) {
			synchronized (RedissonHelper.class) {
				if (redisTemplate == null) {
					WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
					redisTemplate = wac.getBean(Redisson.class);
				}
			}
		}
		return redisTemplate;
	}

	private RBucket<Object> getRedisBucket(String key) {
		return getRedis().getBucket(key);
	}

	public final Object get(final String key) {
		RBucket<Object> temp = getRedisBucket(key);
		expire(temp, EXPIRE);
		return temp.get();
	}

	public final void set(final String key, final Serializable value) {
		RBucket<Object> temp = getRedisBucket(key);
		expire(temp, EXPIRE);
		temp.set(value);
	}

	public final void set(final String key, final Serializable value, int seconds) {
		RBucket<Object> temp = getRedisBucket(key);
		expire(temp, seconds);
		temp.set(value);
	}

	public final void multiSet(final Map<String, Object> temps) {
		getRedis().getBuckets().set(temps);
	}

	public final Boolean exists(final String key) {
		RBucket<Object> temp = getRedisBucket(key);
		return temp.isExists();
	}

	public final void del(final String key) {
		getRedis().getKeys().deleteAsync(key);
	}

	public final void delAll(final String pattern) {
		getRedis().getKeys().deleteByPattern(pattern);
	}

	public final String type(final String key) {
		RType type = getRedis().getKeys().getType(key);
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
		bucket.expireAsync(seconds, TimeUnit.SECONDS);
	}

	/**
	 * 在某个时间点失效
	 *
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public final Boolean expireAt(final String key, final long unixTime) {
		return getRedis().getBucket(key).expireAt(new Date(unixTime));
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
		Iterable<String> keys = getRedis().getKeys().getKeysByPattern(pattern);
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

	public boolean setnx(String key, Serializable value) {
		return getRedis().getLock(key).tryLock();
	}

	public void unlock(String key) {
		getRedis().getLock(key).unlock();
	}
}
