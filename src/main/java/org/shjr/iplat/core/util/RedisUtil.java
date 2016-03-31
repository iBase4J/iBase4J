package org.shjr.iplat.core.util;

import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by jonson.xu on 15-4-23.
 */
public class RedisUtil {
	private RedisUtil() {
	}

	private static ShardedJedisPool shardedJedisPool = null;
	private static Integer EXPIRE = 60 * 60 * 1; // 1小时

	private static ShardedJedisPool getPool() {
		if (shardedJedisPool == null) {
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			EXPIRE = wac.getServletContext().getSessionCookieConfig().getMaxAge();
			shardedJedisPool = wac.getBean(ShardedJedisPool.class);
		}
		return shardedJedisPool;
	}

	public static Long rpush(String key, String value) {
		ShardedJedis jedis = getPool().getResource();
		try {
			Long result = jedis.rpush(key, value);
			jedis.expire(key, EXPIRE);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static String hget(String key, String field) {
		ShardedJedis jedis = getPool().getResource();
		String result = "";
		try {
			result = jedis.hget(key, field);
			jedis.expire(key, EXPIRE);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}

	public static List<String> lrange(String key, long start, long end) {
		ShardedJedis jedis = getPool().getResource();
		try {
			List<String> result = jedis.lrange(key, start, end);
			jedis.expire(key, EXPIRE);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static Long del(String key) {
		ShardedJedis jedis = getPool().getResource();
		try {
			return jedis.del(key);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static String get(String key) {
		ShardedJedis jedis = getPool().getResource();
		try {
			String result = jedis.get(key);
			jedis.expire(key, EXPIRE);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static String set(String key, Object value) {
		ShardedJedis jedis = getPool().getResource();
		try {
			String result = jedis.set(key, value.toString());
			jedis.expire(key, EXPIRE);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static Long hset(String key, String field, Object value) {
		ShardedJedis jedis = getPool().getResource();
		try {
			Long result = jedis.hset(key, field, value.toString());
			jedis.expire(key, EXPIRE);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static Long hdel(String key, String... field) {
		ShardedJedis jedis = getPool().getResource();
		try {
			Long result = jedis.hdel(key, field);
			jedis.expire(key, EXPIRE);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static Map<String, String> getAll(String key) {
		ShardedJedis jedis = getPool().getResource();
		try {
			Map<String, String> result = jedis.hgetAll(key);
			jedis.expire(key, EXPIRE);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
}
