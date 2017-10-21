package org.ibase4j.core.config;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.support.cache.RedisHelper;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(PropertiesUtil.getInt("redis.minIdle"));
        config.setMaxIdle(PropertiesUtil.getInt("redis.maxIdle"));
        config.setMaxTotal(PropertiesUtil.getInt("redis.maxTotal"));
        config.setMaxWaitMillis(PropertiesUtil.getInt("redis.maxWaitMillis"));
        config.setTestOnBorrow(Boolean.valueOf(PropertiesUtil.getString("redis.testOnBorrow")));
        return config;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory;
        String nodes = PropertiesUtil.getString("redis.cluster.nodes");
        if (StringUtils.isNotBlank(nodes)) {
            List<String> list = InstanceUtil.newArrayList(nodes.split(","));
            RedisClusterConfiguration configuration = new RedisClusterConfiguration(list);
            configuration.setMaxRedirects(PropertiesUtil.getInt("redis.cluster.max-redirects"));
            jedisConnectionFactory = new JedisConnectionFactory(configuration, jedisPoolConfig);
            jedisConnectionFactory.setPassword(PropertiesUtil.getString("redis.password"));
        } else {
            jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
            jedisConnectionFactory.setHostName(PropertiesUtil.getString("redis.host"));
            jedisConnectionFactory.setPort(PropertiesUtil.getInt("redis.port"));
            jedisConnectionFactory.setPassword(PropertiesUtil.getString("redis.password"));
        }
        jedisConnectionFactory.setTimeout(PropertiesUtil.getInt("redis.timeout"));
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<Serializable, Serializable>();
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
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

    @Bean
    @Qualifier("redisTemplate")
    public CacheManager redisCacheManager(RedisTemplate<Serializable, Serializable> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setTransactionAware(true);
        cacheManager.setDefaultExpiration(PropertiesUtil.getInt("redis.expiration", 10));
        return cacheManager;
    }
}
