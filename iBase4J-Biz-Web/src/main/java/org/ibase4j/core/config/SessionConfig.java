package org.ibase4j.core.config;

import org.ibase4j.core.listener.SessionListener;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * 会话管理
 * 
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:15:44
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfig {
	@Bean
	public RedisHttpSessionConfiguration redisHttpSessionConfiguration() {
		RedisHttpSessionConfiguration config = new RedisHttpSessionConfiguration();
		config.setMaxInactiveIntervalInSeconds(PropertiesUtil.getInt("session.maxInactiveInterval"));
		config.setRedisNamespace(PropertiesUtil.getString("session.redis.namespace"));
		return config;
	}

	@Bean
	public SessionListener sessionListener() {
		return new SessionListener();
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
