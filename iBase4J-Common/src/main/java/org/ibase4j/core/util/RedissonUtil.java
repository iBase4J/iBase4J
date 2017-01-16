package org.ibase4j.core.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RType;
import org.redisson.api.RedissonClient;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Redis缓存辅助类
 */
public final class RedissonUtil {
	private RedissonUtil() {
	}

	private static RedissonClient redisTemplate = null;
	private static Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

	// 获取连接
	private static RedissonClient getRedis() {
		if (redisTemplate == null) {
			synchronized (RedissonUtil.class) {
				if (redisTemplate == null) {
					WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
					redisTemplate = wac.getBean(Redisson.class);
				}
			}
		}
		return redisTemplate;
	}

	private static RBucket<Object> getRedisBucket(String key) {
		return getRedis().getBucket(key);
	}

	public static final Object get(final String key) {
		RBucket<Object> temp = getRedisBucket(key);
		expire(temp, EXPIRE);
		return temp.get();
	}

	public static final void set(final String key, final Serializable value) {
		RBucket<Object> temp = getRedisBucket(key);
		expire(temp, EXPIRE);
		temp.set(value);
	}

	public static final void set(final String key, final Serializable value, int seconds) {
		RBucket<Object> temp = getRedisBucket(key);
		expire(temp, seconds);
		temp.set(value);
	}

	public static final void multiSet(final Map<String, Object> temps) {
		getRedis().getBuckets().set(temps);
	}

	public static final Boolean exists(final String key) {
		RBucket<Object> temp = getRedisBucket(key);
		return temp.isExists();
	}

	public static final void del(final String key) {
		getRedis().getKeys().deleteAsync(key);
	}

	public static final void delAll(final String pattern) {
		getRedis().getKeys().deleteByPattern(pattern);
	}

	public static final String type(final String key) {
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
	public static final void expire(final RBucket<Object> bucket, final int seconds) {
		bucket.expireAsync(seconds, TimeUnit.SECONDS);
	}

	/**
	 * 在某个时间点失效
	 *
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public static final Boolean expireAt(final String key, final long unixTime) {
		return getRedis().getBucket(key).expireAt(new Date(unixTime));
	}

	public static final Long ttl(final String key) {
		RBucket<Object> rBucket = getRedisBucket(key);
		return rBucket.remainTimeToLive();
	}

	public static final Object getSet(final String key, final Object value) {
		RBucket<Object> rBucket = getRedisBucket(key);
		return rBucket.getAndSet(value);
	}

}
