package org.ibase4j.core.support.cache.jedis;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public class JedisTemplate implements ApplicationContextAware {
	private static final Logger logger = LogManager.getLogger();

	private static Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

	private static ShardedJedisPool shardedJedisPool = null;
	protected ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// 获取线程
	private ShardedJedis getJedis() {
		if (shardedJedisPool == null) {
			synchronized (EXPIRE) {
				if (shardedJedisPool == null) {
					JedisConnectionFactory jedisConnectionFactory = applicationContext
							.getBean(JedisConnectionFactory.class);
					JedisPoolConfig poolConfig = jedisConnectionFactory.getPoolConfig();
					JedisShardInfo shardInfo = jedisConnectionFactory.getShardInfo();
					List<JedisShardInfo> list = InstanceUtil.newArrayList(shardInfo);
					shardedJedisPool = new ShardedJedisPool(poolConfig, list);
				}
			}
		}
		return shardedJedisPool.getResource();
	}

	public <K> K run(String key, Executor<K> executor, Integer... expire) {
		ShardedJedis jedis = getJedis();
		if (jedis == null) {
			return null;
		}
		try {
			K result = executor.execute(jedis);
			if (jedis.exists(key)) {
				if (expire == null || expire.length == 0) {
					jedis.expire(key, EXPIRE);
				} else if (expire.length == 1) {
					jedis.expire(key, expire[0]);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public <K> K run(byte[] key, Executor<K> executor, Integer... expire) {
		ShardedJedis jedis = getJedis();
		if (jedis == null) {
			return null;
		}
		try {
			K result = executor.execute(jedis);
			if (jedis.exists(key)) {
				if (expire == null || expire.length == 0) {
					jedis.expire(key, EXPIRE);
				} else if (expire.length == 1) {
					jedis.expire(key, expire[0]);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
}
