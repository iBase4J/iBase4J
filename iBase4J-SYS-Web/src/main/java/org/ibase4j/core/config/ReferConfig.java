package org.ibase4j.core.config;

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

	@Bean
	public ISysProvider sysProvider() {
		return sysProvider;
	}
}
