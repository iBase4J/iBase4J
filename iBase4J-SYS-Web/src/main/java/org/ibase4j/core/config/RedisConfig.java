package org.ibase4j.core.config;

import java.io.Serializable;

import org.ibase4j.core.support.cache.RedisHelper;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

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
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig poolConfig) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setPoolConfig(poolConfig);
		jedisConnectionFactory.setHostName(PropertiesUtil.getString("redis.host"));
		jedisConnectionFactory.setPort(PropertiesUtil.getInt("redis.port"));
		jedisConnectionFactory.setPassword(PropertiesUtil.getString("redis.password"));
		jedisConnectionFactory.setTimeout(PropertiesUtil.getInt("redis.timeout"));
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<Serializable, Serializable> redisTemplate() {
		RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<Serializable, Serializable>();
		StringRedisSerializer keySerializer = new StringRedisSerializer();
		GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
		redisTemplate.setKeySerializer(keySerializer);
		redisTemplate.setValueSerializer(valueSerializer);
		redisTemplate.setHashKeySerializer(keySerializer);
		redisTemplate.setHashValueSerializer(valueSerializer);
		return redisTemplate;
	}

	@Bean
	@Qualifier("redisTemplate")
	public RedisHelper redisHelper(RedisTemplate<Serializable, Serializable> redisTemplate) {
		RedisHelper redisHelper = new RedisHelper();
		redisHelper.setRedisTemplate(redisTemplate);
		return redisHelper;
	}
}
