package org.shjr.iplat.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	public static Long rpush(String key, String value, Integer expire) {
		ShardedJedis jedis = null;
		try {
			if (expire == null) {
				expire = EXPIRE;
			}
			jedis = getPool().getResource();
			Long result = jedis.rpush(key, value);
			jedis.expire(key, expire);
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

	public static String get(String key, Integer expire) {
		ShardedJedis jedis = null;
		try {
			if (expire == null) {
				expire = EXPIRE;
			}
			jedis = getPool().getResource();
			jedis.expire(key, expire);
			return jedis.get(key);
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static String set(String key, Object value, Integer expire) {
		ShardedJedis jedis = null;
		try {
			if (expire == null) {
				expire = EXPIRE;
			}
			jedis = getPool().getResource();
			return jedis.setex(key, expire, value.toString());
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
