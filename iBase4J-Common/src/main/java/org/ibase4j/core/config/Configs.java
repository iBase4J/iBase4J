package org.ibase4j.core.config;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.ibase4j.core.util.SecurityUtil;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author ShenHuaJie
 * @since 2017年8月15日 上午9:03:17
 */
@Configuration
public class Configs implements EnvironmentPostProcessor, Ordered {
	private static Logger logger = LogManager.getLogger();
	private static final byte[] KEY = { 9, -1, 0, 5, 39, 8, 6, 19 };

	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		// 此处可以http方式 到配置服务器拉取一堆公共配置+本项目个性配置的json串,拼到Properties里
		// ......省略new Properties的过程
		MutablePropertySources propertySources = environment.getPropertySources();
		// addLast 结合下面的 getOrder() 保证顺序 读者也可以试试其他姿势的加载顺序
		PropertiesUtil.getProperties().put("rpc.registry.name", environment.getProperty("server.name"));
		PropertiesUtil.getProperties().put("rpc.protocol.port", "1" + environment.getProperty("server.port"));
		try {
			Properties props = getConfig(environment);
			for (Object key : props.keySet()) {
				String keyStr = key.toString();
				String value = props.getProperty(keyStr);
				if ("druid.writer.password,druid.reader.password".contains(keyStr)) {
					value = SecurityUtil.decryptDes(value, KEY);
					props.setProperty(keyStr, value);
				}
				PropertiesUtil.getProperties().put(keyStr, value);
			}
			propertySources.addLast(new PropertiesPropertySource("thirdEnv", props));
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	public int getOrder() {
		// +1 保证application.propertie里的内容能覆盖掉本配置文件中默认的
		// 如果不想被覆盖 可以去掉 +1 或者 -1 试试
		return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
	}

	// 加载配置文件
	public Properties getConfig(ConfigurableEnvironment env) throws IOException {
		PropertiesFactoryBean config = new PropertiesFactoryBean();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		List<Resource> resouceList = InstanceUtil.newArrayList();
		String[] locationPattern = ArrayUtils.addAll(new String[] { "" }, env.getActiveProfiles());
		// 不同环境配置
		for (String profile : locationPattern) {
			try {
				Resource[] resources;
				if (DataUtil.isEmpty(profile)) {
					resources = resolver.getResources("classpath*:config/*.properties");
				} else {
					resources = resolver.getResources("classpath*:config/" + profile + "/*.properties");
				}
				for (Resource resource : resources) {
					resouceList.add(resource);
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		config.setLocations(resouceList.toArray(new Resource[] {}));
		config.afterPropertiesSet();
		return config.getObject();
	}

	public static void main(String[] args) {
		String encrypt = SecurityUtil.encryptDes("buzhidao", KEY);
		System.out.println(encrypt);
		System.out.println(SecurityUtil.decryptDes(encrypt, KEY));
	}
}
