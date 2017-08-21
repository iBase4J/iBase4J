package org.ibase4j.core.config;

import org.ibase4j.core.listener.SessionListener;
import org.ibase4j.core.support.cache.RedisHelper;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.DefaultCookieSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 会话管理
 * 
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:15:44
 */
@Configuration
@SuppressWarnings("rawtypes")
public class SessionConfig {
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
	public RedisHttpSessionConfiguration redisConfig() {
		RedisHttpSessionConfiguration config = new RedisHttpSessionConfiguration();
		config.setMaxInactiveIntervalInSeconds(PropertiesUtil.getInt("session.maxInactiveInterval"));
		config.setRedisNamespace(PropertiesUtil.getString("session.redis.namespace"));
		return config;
	}

	@Bean
	public SessionListener sessionListener() {
		return new SessionListener();
	}

	@Bean
	public DefaultCookieSerializer cookie() {
		DefaultCookieSerializer cookie = new DefaultCookieSerializer();
		cookie.setCookieName("JSESSIONID");
		cookie.setCookiePath("/");
		cookie.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		return cookie;
	}

	@Bean
	public RedisHelper redisHelper(RedisTemplate redisTemplate) {
		RedisHelper redisHelper = new RedisHelper();
		redisHelper.setRedisTemplate(redisTemplate);
		return redisHelper;
	}
}
