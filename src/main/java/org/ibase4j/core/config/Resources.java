package org.ibase4j.core.config;

import java.util.ResourceBundle;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;

/** 加载配置 */
@PropertySource(value = { "classpath:config/ssh.properties", "classpath:config/email.properties",
		"classpath:config/whiteURL.properties", "claspath:/local/resource*.properties" })
public final class Resources {
	/** SSH服务器配置 */
	public static final ResourceBundle SSH = ResourceBundle.getBundle("config/ssh");
	/** 邮箱服务器配置 */
	public static final ResourceBundle EMAIL = ResourceBundle.getBundle("config/email");
	/** 拦截器白名单 */
	public static final ResourceBundle WHITEURL = ResourceBundle.getBundle("config/whiteURL");

	/** 国际化信息 */
	public static String getResouce(String key) {
		return ResourceBundle.getBundle("local/resource", LocaleContextHolder.getLocale()).getString(key);
	}
}
