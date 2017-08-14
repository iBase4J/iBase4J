package org.ibase4j.core.config;

import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.ibase4j.core.shiro.Realm;
import org.ibase4j.core.support.cache.shiro.RedisCacheManager;
import org.ibase4j.core.util.InstanceUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 权限拦截配置
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:40:20
 */
@Configuration
@EnableAutoConfiguration(exclude=RedisAutoConfiguration.class)
public class ShiroConfig {
	String filters = "/=anon;/app/**=anon;/index.jsp=anon;/regin=anon;/login=anon;/*.ico=anon;/upload/*=anon;"
			+ "/unauthorized=anon;/forbidden=anon;/sns*=anon;/*/api-docs=anon;/callback*=anon;/swagger*=anon;"
			+ "/configuration/*=anon;/*/configuration/*=anon;/webjars/**=anon;" + "/**=authc,user";

	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(new Realm());
		manager.setCacheManager(new RedisCacheManager());
		return manager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
		ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
		factory.setSecurityManager(securityManager);
		factory.setLoginUrl("/unauthorized");
		factory.setUnauthorizedUrl("/forbidden");
		Map<String, String> filterMap = InstanceUtil.newHashMap();
		for (String filter : filters.split("\\;")) {
			String[] keyValue = filter.split("\\=");
			filterMap.put(keyValue[0], keyValue[1]);
		}
		factory.setFilterChainDefinitionMap(filterMap);
		return factory;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycle() {
		return new LifecycleBeanPostProcessor();
	}
}
