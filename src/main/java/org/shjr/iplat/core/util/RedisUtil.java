package org.shjr.iplat.core.util;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shjr.iplat.core.Constants;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:17:22
 */
public class RedisUtil {
	private RedisUtil() {
	}

	private static Logger logger = LogManager.getLogger();

	private static ShardedJedisPool shardedJedisPool = null;
	private static Integer EXPIRE = 60 * 60 * 1; // 1小时

	// 获取线程
	private static ShardedJedisPool getPool() {
		if (shardedJedisPool == null) {
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			shardedJedisPool = wac.getBean(ShardedJedisPool.class);
		}
		return shardedJedisPool;
	}

	// 获取有效期
	private static Integer getExpire(String key) {
		if (key.startsWith(Constants.CURRENT_USER)) {
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			return wac.getServletContext().getSessionCookieConfig().getMaxAge();
		}
		return EXPIRE;
	}

	public static Long rpush(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			Long result = jedis.rpush(key, value);
			jedis.expire(key, getExpire(key));
			return result;
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static String hget(String key, String field) {
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			String result = jedis.hget(key, field);
			jedis.expire(key, getExpire(key));
			return result;
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static List<String> lrange(String key, long start, long end) {
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			List<String> result = jedis.lrange(key, start, end);
			jedis.expire(key, getExpire(key));
			return result;
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static Long del(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.del(key);
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static String get(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			String result = jedis.get(key);
			jedis.expire(key, getExpire(key));
			return result;
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static String set(String key, Object value) {
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.setex(key, getExpire(key), value.toString());
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
}
