package org.ibase4j.core.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;

/**
 * Redis连接配置
 * 
 * @author ShenHuaJie
 */
//@Configuration
public class RedissonConfig {
	/**
	 * Redis server address
	 *
	 */
	private String address;

	/**
	 * Password for Redis authentication. Should be null if not needed
	 */
	private String password;

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
			SingleServerConfig serverConfig = config.useSingleServer().setAddress(address);
			if (StringUtils.isNotBlank(password)) {
				serverConfig.setPassword(password);
			}
		} else if (!nodeAddresses.isEmpty()) {
			ClusterServersConfig serverConfig = config.useClusterServers()
					.addNodeAddress(nodeAddresses.toArray(new String[] {}));
			if (StringUtils.isNotBlank(password)) {
				serverConfig.setPassword(password);
			}
		} else if (masterAddress != null && !slaveAddresses.isEmpty()) {
			MasterSlaveServersConfig serverConfig = config.useMasterSlaveServers().setMasterAddress(masterAddress)
					.addSlaveAddress(slaveAddresses.toArray(new String[] {}));
			if (StringUtils.isNotBlank(password)) {
				serverConfig.setPassword(password);
			}
		}
		return Redisson.create(config);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(String password) {
		this.password = password;
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
