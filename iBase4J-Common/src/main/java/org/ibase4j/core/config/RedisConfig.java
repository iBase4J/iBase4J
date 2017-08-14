package org.ibase4j.core.config;

import org.ibase4j.core.support.cache.jedis.JedisShardInfo;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接配置
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:17:29
 */
@Configuration
public class RedisConfig {

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMinIdle(PropertiesUtil.getInt("redis.minIdle"));
		config.setMaxIdle(PropertiesUtil.getInt("redis.maxIdle"));
		config.setMaxTotal(PropertiesUtil.getInt("redis.maxTotal"));
		config.setMaxWaitMillis(PropertiesUtil.getInt("redis.maxWaitMillis"));
		config.setTestOnBorrow(Boolean.getBoolean(PropertiesUtil.getString("redis.testOnBorrow")));
		return config;
	}

	@Bean
	public JedisShardInfo jedisShardInfo() {
		JedisShardInfo jedisShardInfo = new JedisShardInfo(PropertiesUtil.getString("redis.host"),
				PropertiesUtil.getInt("redis.port"));
		jedisShardInfo.setPassword(PropertiesUtil.getString("redis.password"));
		jedisShardInfo.setConnectionTimeout(PropertiesUtil.getInt("redis.timeout"));
		return jedisShardInfo;
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig poolConfig, JedisShardInfo shardInfo) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setPoolConfig(poolConfig);
		jedisConnectionFactory.setShardInfo(shardInfo);
		return jedisConnectionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate redisTemplate(JedisConnectionFactory connectionFactory) {
		RedisSerializer<String> keySerializer = new StringRedisSerializer();
		RedisSerializer<Object> valueSerializer = new GenericJackson2JsonRedisSerializer();
		RedisTemplate redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(keySerializer);
		redisTemplate.setValueSerializer(valueSerializer);
		redisTemplate.setHashKeySerializer(keySerializer);
		redisTemplate.setHashValueSerializer(valueSerializer);
		return redisTemplate;
	}

	@Bean
	public RedisCacheManager redisCacheManager(RedisTemplate<String, Object> redisOperations) {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisOperations);
		redisCacheManager.setDefaultExpiration(10);
		return redisCacheManager;
	}
}
