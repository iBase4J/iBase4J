package org.ibase4j.core.config;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.util.InstanceUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Redis连接配置
 * 
 * @author ShenHuaJie
 */
public class RedissonConfig {
	private static final Logger logger = LogManager.getLogger();
	private JedisConnectionFactory jedisConnectionFactory;

	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		this.jedisConnectionFactory = jedisConnectionFactory;
	}

	@Bean(name = "redissonClient")
	public RedissonClient getRedissonClient() {
		Config config = new Config();
		RedisStandaloneConfiguration standaloneConfig = jedisConnectionFactory.getStandaloneConfiguration();
		if (jedisConnectionFactory.isRedisClusterAware()) {
			logger.info("Cluster redis.");
			RedisClusterConfiguration clusterConfig = jedisConnectionFactory.getClusterConfiguration();
			Set<RedisNode> clusterNodes = clusterConfig.getClusterNodes();
			List<String> nodeAddress = InstanceUtil.newArrayList();
			for (RedisNode redisNode : clusterNodes) {
				nodeAddress.add(redisNode.getHost() + ":" + redisNode.getPort());
			}
			ClusterServersConfig serverConfig = config.useClusterServers()
					.addNodeAddress(nodeAddress.toArray(new String[] {}));
			if (clusterConfig.getPassword().isPresent()) {
				serverConfig.setPassword(new String(clusterConfig.getPassword().get()));
			}
		} else if (jedisConnectionFactory.isRedisSentinelAware()) {
			logger.info("Sentinel redis.");
			RedisSentinelConfiguration sentinelConfig = jedisConnectionFactory.getSentinelConfiguration();
			Set<RedisNode> sentinels = sentinelConfig.getSentinels();
			List<String> slaveAddresses = InstanceUtil.newArrayList();
			for (RedisNode redisNode : sentinels) {
				slaveAddresses.add(redisNode.getHost() + ":" + redisNode.getPort());
			}
			MasterSlaveServersConfig serverConfig = config.useMasterSlaveServers()
					.setMasterAddress(slaveAddresses.get(0));
			slaveAddresses.remove(0);
			serverConfig.addSlaveAddress(slaveAddresses.toArray(new String[] {}));
			if (sentinelConfig.getPassword().isPresent()) {
				serverConfig.setPassword(new String(sentinelConfig.getPassword().get()));
			}
		} else if (standaloneConfig != null) {
			logger.info("Standalone redis.");
			SingleServerConfig serverConfig = config.useSingleServer()
					.setAddress(standaloneConfig.getHostName() + ":" + standaloneConfig.getPort());
			if (standaloneConfig.getPassword().isPresent()) {
				serverConfig.setPassword(new String(standaloneConfig.getPassword().get()));
			}
		}
		return Redisson.create(config);
	}
}
