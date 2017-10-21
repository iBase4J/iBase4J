package org.ibase4j.core.config;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.support.cache.RedissonHelper;
import org.ibase4j.core.support.cache.redisson.Client;
import org.ibase4j.core.util.PropertiesUtil;
import org.redisson.api.RedissonClient;
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
        Client client = new Client();
        String nodes = PropertiesUtil.getString("redis.cluster.nodes");
        if (StringUtils.isNotBlank(nodes)) {
            client.setNodeAddresses(nodes);
        } else {
            String address = "redis://" + PropertiesUtil.getString("redis.host") + ":"
                + PropertiesUtil.getString("redis.port");
            client.setAddress(address);
        }
        client.setPassword(PropertiesUtil.getString("redis.password"));
        client.setTimeout(PropertiesUtil.getInt("redis.timeout"));
        return client.getRedissonClient();
    }

    @Bean
    public RedissonHelper redissonHelper(RedissonClient redisson) {
        RedissonHelper helper = new RedissonHelper();
        helper.setRedissonClient(redisson);
        return helper;
    }
}
