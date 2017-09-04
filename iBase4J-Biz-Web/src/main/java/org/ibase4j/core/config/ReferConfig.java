package org.ibase4j.core.config;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

public class ReferConfig {
	@Configuration
	@Conditional(DubboConfig.EnableDubbo.class)
	@ImportResource("classpath:refer/dubbo.xml")
	static class DubboReferConfig {
	}

	@Configuration
	@Conditional(MotanConfig.EnableMotan.class)
	@ImportResource("classpath:refer/motan.xml")
	static class MotanReferConfig {
	}
}
