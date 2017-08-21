package org.ibase4j.core.config;

import org.ibase4j.core.support.cache.RedissonHelper;
import org.ibase4j.core.support.cache.redisson.SpringCacheManager;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.cache.CacheConfig;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis连接配置
 * 
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:17:29
 */
@Configuration
public class RedissonConfig {
	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		SingleServerConfig singleConfig = config.useSingleServer();
		String address = "redis://" + PropertiesUtil.getString("redis.host") + ": "
				+ PropertiesUtil.getString("redis.port");
		singleConfig.setAddress(address);
		singleConfig.setConnectTimeout(PropertiesUtil.getInt("redis.timeout"));
		return Redisson.create(config);
	}

	@Bean
	public CacheManager cacheManager(RedissonClient redisson) {
		SpringCacheManager cacheManager = new SpringCacheManager();
		cacheManager.setRedisson(redisson);
		cacheManager.setPatternConfig(InstanceUtil.newLinkedHashMap("iBase4J:M:*", new CacheConfig(600000, 0)));
		return cacheManager;
	}

	@Bean
	public RedissonHelper redissonHelper(RedissonClient redisson) {
		RedissonHelper helper = new RedissonHelper();
		helper.setRedissonClient(redisson);
		return helper;
	}
}
