package org.ibase4j.core.util;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Redis缓存辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:17:22
 */
@PropertySource("classpath:config/redis.properties")
public final class RedisUtil {
	private RedisUtil() {
	}

	private static RedisTemplate<Serializable, Serializable> redisTemplate = null;
	private static Integer EXPIRE = Integer
			.valueOf(ResourceBundle.getBundle("config/redis").getString("redis.expiration"));

	// 获取连接
	@SuppressWarnings("unchecked")
	private static RedisTemplate<Serializable, Serializable> getRedis() {
		if (redisTemplate == null) {
			synchronized (RedisUtil.class) {
				if (redisTemplate == null) {
					WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
					redisTemplate = (RedisTemplate<Serializable, Serializable>) wac.getBean("redisTemplate");
				}
			}
		}
		return redisTemplate;
	}

	public static final Serializable get(final String key) {
		expire(key, EXPIRE);
		return getRedis().opsForValue().get(key);
	}

	public static final void set(final String key, final Serializable value) {
		getRedis().boundValueOps(key).set(value);
		expire(key, EXPIRE);
	}

	public static final Boolean exists(final String key) {
		expire(key, EXPIRE);
		return getRedis().hasKey(key);
	}

	public static final void del(final String key) {
		getRedis().delete(key);
	}

	public static final String type(final String key) {
		expire(key, EXPIRE);
		return getRedis().type(key).getClass().getName();
	}

	/**
	 * 在某段时间后失效
	 * 
	 * @return
	 */
	public static final Boolean expire(final String key, final int seconds) {
		return getRedis().expire(key, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public static final Boolean expireAt(final String key, final long unixTime) {
		return getRedis().expireAt(key, new Date(unixTime));
	}

	public static final Long ttl(final String key) {
		return getRedis().getExpire(key, TimeUnit.SECONDS);
	}

	public static final void setrange(final String key, final long offset, final String value) {
		expire(key, EXPIRE);
		getRedis().boundValueOps(key).set(value, offset);
	}

	public static final String getrange(final String key, final long startOffset, final long endOffset) {
		expire(key, EXPIRE);
		return getRedis().boundValueOps(key).get(startOffset, endOffset);
	}

	public static final Serializable getSet(final String key, final String value) {
		expire(key, EXPIRE);
		return getRedis().boundValueOps(key).getAndSet(value);
	}
	
	// 未完，待续...
}
