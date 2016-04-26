package org.ibase4j.core.config;

import java.util.ResourceBundle;

import org.springframework.context.annotation.PropertySource;

/** 加载配置 */
@PropertySource(value = { "classpath:config/ssh.properties", "classpath:config/email.properties",
		"classpath:config/whiteURL.properties", "claspath:/local/resource*.properties" })
public interface Resource {
	/** SSH服务器配置 */
	ResourceBundle SSH = ResourceBundle.getBundle("config/ssh");
	/** 邮箱服务器配置 */
	ResourceBundle EMAIL = ResourceBundle.getBundle("config/email");
	/** 国际化信息 */
	ResourceBundle RESOURCE = ResourceBundle.getBundle("local/resource");
	/** 拦截器白名单 */
	ResourceBundle WHITEURL = ResourceBundle.getBundle("config/whiteURL");
}
