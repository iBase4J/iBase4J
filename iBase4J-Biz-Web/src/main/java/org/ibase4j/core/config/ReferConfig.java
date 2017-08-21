package org.ibase4j.core.config;

import org.ibase4j.provider.IBizProvider;
import org.ibase4j.provider.ISysProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.annotation.Reference;
import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

@Configuration
public class ReferConfig {
	@Reference(check = false)
	@MotanReferer(check = false)
	ISysProvider sysProvider;
	@Reference(check = false)
	@MotanReferer(check = false)
	IBizProvider bizProvider;

	@Bean
	public ISysProvider sysProvider() {
		return sysProvider;
	}
	@Bean
	public IBizProvider bizProvider() {
		return bizProvider;
	}
}
