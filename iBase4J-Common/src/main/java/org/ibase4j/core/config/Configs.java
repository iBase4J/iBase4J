package org.ibase4j.core.config;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class Configs {
	private static Logger logger = LogManager.getLogger();

	@Bean
	@Primary
	public PropertyPlaceholderConfigurer config(ApplicationContext context) throws IOException {
		Environment env = context.getEnvironment();
		PropertiesUtil.getProperties().put("rpc.registry.name", env.getProperty("server.name"));
		PropertiesUtil.getProperties().put("rpc.protocol.port", "1" + env.getProperty("server.port"));
		List<String> list = InstanceUtil.newArrayList();
		list.add("druid.writer.password");
		list.add("druid.reader.password");
		// 加载配置文件
		PropertiesUtil config = new PropertiesUtil();
		config.setDecryptProperties(list);
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
		
		return config;
	}
}
