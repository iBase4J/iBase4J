package org.ibase4j.core.config;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import top.ibase4j.core.config.RpcConfig;

public class ReferConfig {
	@Configuration
	@Conditional(RpcConfig.EnableDubbo.class)
	@ImportResource("classpath:refer/dubbo.xml")
	static class DubboReferConfig {
	}

	@Configuration
	@Conditional(RpcConfig.EnableMotan.class)
	@ImportResource("classpath:refer/motan.xml")
	static class MotanReferConfig {
	}
}
