package org.ibase4j.core.config;

import org.ibase4j.core.listener.SessionListener;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * 会话管理
 * 
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:15:44
 */
@Configuration
public class SessionConfig {
	@Bean
	public RedisHttpSessionConfiguration redisConfig() {
		RedisHttpSessionConfiguration config = new RedisHttpSessionConfiguration();
		config.setMaxInactiveIntervalInSeconds(PropertiesUtil.getInt("session.maxInactiveInterval"));
		config.setRedisNamespace(PropertiesUtil.getString("session.redis.namespace"));
		return config;
	}

	@Bean
	public SpringHttpSessionConfiguration springSessionConfig() {
		SpringHttpSessionConfiguration config = new SpringHttpSessionConfiguration();
		config.setHttpSessionListeners(InstanceUtil.newArrayList(new SessionListener()));
		return config;
	}

	@Bean
	public DefaultCookieSerializer cookie() {
		DefaultCookieSerializer cookie = new DefaultCookieSerializer();
		cookie.setCookieName("JSESSIONID");
		cookie.setCookiePath("/");
		cookie.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		return cookie;
	}
}
