package org.ibase4j.core.config;

import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.ibase4j.core.shiro.Realm;
import org.ibase4j.core.support.cache.shiro.RedisCacheManager;
import org.ibase4j.core.util.InstanceUtil;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 权限拦截配置
 * 
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:40:20
 */
@Configuration
@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
public class ShiroConfig {
	String filters = "/=anon;/app/**=anon;/index.jsp=anon;/regin=anon;/login=anon;/*.ico=anon;/upload/*=anon;"
			+ "/unauthorized=anon;/forbidden=anon;/sns*=anon;/*/api-docs=anon;/callback*=anon;/swagger*=anon;"
			+ "/configuration/*=anon;/*/configuration/*=anon;/webjars/**=anon;" + "/**=authc,user";

	@Bean
	public Realm realm() {
		return new Realm();
	}

	@Bean
	public DefaultWebSecurityManager securityManager(Realm realm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(realm);
		manager.setCacheManager(new RedisCacheManager());
		return manager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
		factory.setSecurityManager(securityManager);
		factory.setLoginUrl("/unauthorized");
		factory.setUnauthorizedUrl("/forbidden");
		Map<String, String> filterMap = InstanceUtil.newLinkedHashMap();
		for (String filter : filters.split("\\;")) {
			String[] keyValue = filter.split("\\=");
			filterMap.put(keyValue[0], keyValue[1]);
		}
		factory.setFilterChainDefinitionMap(filterMap);
		return factory;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAdvisor(
			org.apache.shiro.mgt.SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
