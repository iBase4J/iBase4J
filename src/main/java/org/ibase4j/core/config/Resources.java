package org.ibase4j.core.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;

/** 加载配置 */
@PropertySource(value = { "classpath:config/ssh.properties", "classpath:config/email.properties",
		"classpath:config/whiteURL.properties", "claspath:i18n/messages*.properties" })
public final class Resources {
	/** SSH服务器配置 */
	public static final ResourceBundle SSH = ResourceBundle.getBundle("config/ssh");
	/** 邮箱服务器配置 */
	public static final ResourceBundle EMAIL = ResourceBundle.getBundle("config/email");
	/** 拦截器白名单 */
	public static final ResourceBundle WHITEURL = ResourceBundle.getBundle("config/whiteURL");
	/** 国际化信息 */
	private static final Map<String, ResourceBundle> MESSAGES = new HashMap<String, ResourceBundle>();

	/** 国际化信息 */
	public static String getMessage(String key, Object... params) {
		Locale locale = LocaleContextHolder.getLocale();
		ResourceBundle message = MESSAGES.get(locale.getLanguage());
		if (message == null) {
			synchronized (MESSAGES) {
				message = MESSAGES.get(locale.getLanguage());
				if (message == null) {
					message = ResourceBundle.getBundle("i18n/messages", locale);
					MESSAGES.put(locale.getLanguage(), message);
				}
			}
		}
		if (params != null) {
			return String.format(message.getString(key), params);
		}
		return message.getString(key);
	}

	/** 清除国际化信息 */
	public static void flushMessage() {
		MESSAGES.clear();
	}
}
