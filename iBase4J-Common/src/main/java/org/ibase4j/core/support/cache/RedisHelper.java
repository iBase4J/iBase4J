package org.ibase4j.core.support.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Redis缓存辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:17:22
 */
public final class RedisHelper extends CacheManager {

	private RedisTemplate<Serializable, Serializable> redisTemplate = null;
	private Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

	// 获取连接
	@SuppressWarnings("unchecked")
	private RedisTemplate<Serializable, Serializable> getRedis() {
		if (redisTemplate == null) {
			synchronized (RedisHelper.class) {
				if (redisTemplate == null) {
					WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
					redisTemplate = (RedisTemplate<Serializable, Serializable>) wac.getBean("redisTemplate");
				}
			}
		}
		return redisTemplate;
	}

	public final Object get(final String key) {
		expire(key, EXPIRE);
		return getRedis().boundValueOps(key).get();
	}

	public final Set<Serializable> getAll(final String pattern) {
		Set<Serializable> values = InstanceUtil.newHashSet();
		Set<Serializable> keys = getRedis().keys(pattern);
		for (Serializable key : keys) {
			expire(key.toString(), EXPIRE);
			values.add(getRedis().opsForValue().get(key));
		}
		return values;
	}

	public final void set(final String key, final Serializable value, int seconds) {
		getRedis().boundValueOps(key).set(value);
		expire(key, seconds);
	}

	public final void set(final String key, final Serializable value) {
		getRedis().boundValueOps(key).set(value);
		expire(key, EXPIRE);
	}

	public final Boolean exists(final String key) {
		return getRedis().hasKey(key);
	}

	public final void del(final String key) {
		getRedis().delete(key);
	}

	public final void delAll(final String pattern) {
		getRedis().delete(getRedis().keys(pattern));
	}

	public final String type(final String key) {
		expire(key, EXPIRE);
		return getRedis().type(key).getClass().getName();
	}

	/**
	 * 在某段时间后失效
	 * 
	 * @return
	 */
	public final Boolean expire(final String key, final int seconds) {
		return getRedis().expire(key, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public final Boolean expireAt(final String key, final long unixTime) {
		return getRedis().expireAt(key, new Date(unixTime));
	}

	public final Long ttl(final String key) {
		return getRedis().getExpire(key, TimeUnit.SECONDS);
	}

	public final void setrange(final String key, final long offset, final String value) {
		expire(key, EXPIRE);
		getRedis().boundValueOps(key).set(value, offset);
	}

	public final String getrange(final String key, final long startOffset, final long endOffset) {
		expire(key, EXPIRE);
		return getRedis().boundValueOps(key).get(startOffset, endOffset);
	}

	public final Serializable getSet(final String key, final String value) {
		expire(key, EXPIRE);
		return getRedis().boundValueOps(key).getAndSet(value);
	}

	// 未完，待续...
}
