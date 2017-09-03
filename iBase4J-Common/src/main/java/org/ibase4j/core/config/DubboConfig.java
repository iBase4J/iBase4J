package org.ibase4j.core.config;

import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

/**
 * RPC服务配置
 * 
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:16:18
 */
@Configuration
@Conditional(DubboConfig.EnableDubbo.class)
public class DubboConfig {
	static class EnableDubbo implements Condition {
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return "dubbo".equals(PropertiesUtil.getString("rpc.type"));
		}
	}

	@Bean
	public AnnotationBean dubboAnnotation(ApplicationContext context) {
		AnnotationBean bean = new AnnotationBean();
		bean.setApplicationContext(context);
		bean.setPackage("org.ibase4j");
		return bean;
	}

	@Bean
	public ApplicationConfig dubboApplication() {
		ApplicationConfig config = new ApplicationConfig();
		config.setName(PropertiesUtil.getString("rpc.registry.name"));
		config.setLogger("slf4j");
		return config;
	}

	@Bean
	public RegistryConfig dubboRegistry() {
		RegistryConfig config = new RegistryConfig();
		String address = PropertiesUtil.getString("rpc.registry") + "://" + PropertiesUtil.getString("rpc.address");
		config.setAddress(address);
		config.setTimeout(PropertiesUtil.getInt("rpc.timeout"));
		String file = PropertiesUtil.getString("rpc.cache.dir") + "/dubbo-"
				+ PropertiesUtil.getString("rpc.registry.name") + ".cache";
		config.setFile(file);
		return config;
	}

	@Bean
	public ProtocolConfig dubboProtocol() {
		ProtocolConfig config = new ProtocolConfig();
		config.setPort(PropertiesUtil.getInt("rpc.protocol.port"));
		config.setThreads(PropertiesUtil.getInt("rpc.protocol.threads"));
		return config;
	}

	@Bean
	public ConsumerConfig dubboConsumer() {
		ConsumerConfig config = new ConsumerConfig();
		config.setTimeout(PropertiesUtil.getInt("rpc.timeout"));
		config.setRetries(PropertiesUtil.getInt("rpc.consumer.retries"));
		config.setCheck(false);
		config.setLoadbalance("leastactive");
		return config;
	}
}