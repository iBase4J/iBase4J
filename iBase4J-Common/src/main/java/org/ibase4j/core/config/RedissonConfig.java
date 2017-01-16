package org.ibase4j.core.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis连接配置
 * 
 * @author ShenHuaJie
 */
@Configuration
public class RedissonConfig {
	/**
	 * Redis server address
	 *
	 */
	private String address;
	/**
	 * Redis cluster node urls list
	 */
	private List<String> nodeAddresses = new ArrayList<String>();

	/**
	 * Redis master server address
	 */
	private String masterAddress;

	/**
	 * Redis slave servers addresses
	 */
	private Set<String> slaveAddresses = new HashSet<String>();

	@Bean(name = "redissonClient")
	public RedissonClient getRedissonClient() {
		Config config = new Config();
		if (StringUtils.isNotBlank(address)) {
			config.useSingleServer().setAddress(address);
		} else if (!nodeAddresses.isEmpty()) {
			config.useClusterServers().addNodeAddress(nodeAddresses.toArray(new String[] {}));
		} else if (masterAddress != null && !slaveAddresses.isEmpty()) {
			config.useMasterSlaveServers().setMasterAddress(masterAddress)
					.addSlaveAddress(slaveAddresses.toArray(new String[] {}));
		}
		return Redisson.create(config);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNodeAddresses(String... nodeAddresses) {
		if (nodeAddresses != null) {
			for (int i = 0; i < nodeAddresses.length; i++) {
				this.nodeAddresses.add(nodeAddresses[i]);
			}
		}
	}

	public void setMasterAddress(String masterAddress) {
		this.masterAddress = masterAddress;
	}

	public void setSlaveAddresses(String... slaveAddresses) {
		if (slaveAddresses != null) {
			for (int i = 0; i < slaveAddresses.length; i++) {
				this.slaveAddresses.add(slaveAddresses[i]);
			}
		}
	}
}
