package org.ibase4j.core.config;

import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;

/**
 * @author ShenHuaJie
 * @since 2017年8月14日 下午1:22:31
 */
@Configuration
@Conditional(MotanConfig.EnableMotan.class)
public class MotanConfig {
	static class EnableMotan implements Condition {
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return "motan".equals(context.getEnvironment().getProperty("rpc.type"));
		}
	}

	@Bean
	public AnnotationBean annotationBean() {
		AnnotationBean bean = new AnnotationBean();
		bean.setPackage("org.ibase4j");
		return bean;
	}

	@Bean
	public RegistryConfigBean registryConfig() {
		RegistryConfigBean config = new RegistryConfigBean();
		config.setName(PropertiesUtil.getString("rpc.registry.name"));
		config.setRegProtocol(PropertiesUtil.getString("rpc.registry"));
		config.setConnectTimeout(PropertiesUtil.getInt("rpc.timeout"));
		config.setAddress(PropertiesUtil.getString("rpc.address"));
		return config;
	}

	@Bean
	public ProtocolConfigBean protocolConfig1() {
		ProtocolConfigBean config = new ProtocolConfigBean();
		config.setDefault(true);
		config.setName("motan");
		config.setMaxContentLength(1048576);
		config.setMaxServerConnection(8000);
		config.setMaxWorkerThread(PropertiesUtil.getInt("rpc.protocol.maxThread"));
		config.setMinWorkerThread(PropertiesUtil.getInt("rpc.protocol.minThread"));
		return config;
	}

	@Bean
	public BasicServiceConfigBean baseServiceConfig() {
		BasicServiceConfigBean config = new BasicServiceConfigBean();
		config.setRegistry(PropertiesUtil.getString("rpc.registry.name"));
		config.setExport("motan:" + PropertiesUtil.getString("rpc.protocol.port"));
		config.setAccessLog(false);
		config.setShareChannel(true);
		return config;
	}

	@Bean
	public BasicRefererConfigBean baseRefererConfig() {
		BasicRefererConfigBean config = new BasicRefererConfigBean();
		config.setProtocol("motan");
		config.setRegistry(PropertiesUtil.getString("rpc.registry.name"));
		config.setRetries(PropertiesUtil.getInt("rpc.consumer.retries"));
		config.setCheck(false);
		config.setAccessLog(true);
		config.setThrowException(true);
		return config;
	}
}