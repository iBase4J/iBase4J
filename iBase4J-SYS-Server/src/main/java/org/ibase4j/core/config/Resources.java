package org.ibase4j.core.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.context.annotation.PropertySource;

/** 加载配置 */
@PropertySource(value = { "classpath:config/ssh.properties", "classpath:config/email.properties",
		"claspath:i18n/messages*.properties" })
public final class Resources {
	/** SSH服务器配置 */
	public static final ResourceBundle SSH = ResourceBundle.getBundle("config/ssh");
	/** 邮箱服务器配置 */
	public static final ResourceBundle EMAIL = ResourceBundle.getBundle("config/email");
	/** 国际化信息 */
	private static final Map<Locale, ResourceBundle> MESSAGES = new HashMap<Locale, ResourceBundle>();

	/** 国际化信息 */
	public static String getMessage(String key, Object... params) {
		ResourceBundle message = MESSAGES.get(Locale.getDefault());
		if (message == null) {
			synchronized (MESSAGES) {
				message = MESSAGES.get(Locale.getDefault());
				if (message == null) {
					message = ResourceBundle.getBundle("i18n/messages", Locale.getDefault());
					MESSAGES.put(Locale.getDefault(), message);
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
