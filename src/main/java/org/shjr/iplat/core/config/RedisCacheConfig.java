/**
 * 
 */
package org.shjr.iplat.core.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author ShenHuaJie
 */
@Configuration
@EnableCaching
@PropertySource("classpath:/redis.properties")
public class RedisCacheConfig extends CachingConfigurerSupport {
	private final ResourceBundle bundle = ResourceBundle.getBundle("redis");

	/** 线程池配置 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setTestOnBorrow(true);
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.maxIdle")));
		config.setMinIdle(Integer.valueOf(bundle.getString("redis.minIdle")));
		config.setMaxTotal(Integer.valueOf(bundle.getString("redis.maxTotal")));
		return config;
	}

	/** 连接配置 */
	@Bean
	public JedisShardInfo jedisShardInfo() {
		return new JedisShardInfo(bundle.getString("redis.host"), Integer.valueOf(bundle.getString("redis.port")));
	}

	/** 创建线程池 */
	@Bean
	public ShardedJedisPool shardedJedisPool(JedisPoolConfig jedisPoolConfig, JedisShardInfo jedisShardInfo) {
		List<JedisShardInfo> shards = new ArrayList<>();
		shards.add(jedisShardInfo);
		return new ShardedJedisPool(jedisPoolConfig, shards);
	}

	/** 重写生成key方法 */
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append(JSON.toJSON(obj));
				}
				return sb.toString();
			}
		};
	}

	/** Spring连接线程池 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig,
			JedisShardInfo jedisShardInfo) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		jedisConnectionFactory.setShardInfo(jedisShardInfo);
		return jedisConnectionFactory;
	}

	/** 缓存模版 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
		stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		stringRedisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
		return stringRedisTemplate;
	}

	/** 缓存管理器 */
	@Bean
	public RedisCacheManager redisCacheManager(StringRedisTemplate stringRedisTemplate) {
		RedisCacheManager redisCacheManager = new RedisCacheManager(stringRedisTemplate);
		redisCacheManager.setDefaultExpiration(Long.valueOf(bundle.getString("redis.timeout")));
		redisCacheManager.setLoadRemoteCachesOnStartup(true);
		return redisCacheManager;
	}
}
