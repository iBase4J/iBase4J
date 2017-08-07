package org.ibase4j.core.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Parsing The Configuration File
 * 
 * @author ShenHuaJie
 * @version 2016年7月30日 下午11:41:53
 */
public final class PropertiesUtil extends PropertyPlaceholderConfigurer {
	private static final byte[] KEY = { 9, -1, 0, 5, 39, 8, 6, 19 };
	private static Map<String, String> ctxPropertiesMap;
	private List<String> decryptProperties;

	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		ctxPropertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			if (decryptProperties != null && decryptProperties.contains(keyStr)) {
				value = SecurityUtil.decryptDes(value, KEY);
				props.setProperty(keyStr, value);
			}
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	/**
	 * @param decryptPropertiesMap
	 *            the decryptPropertiesMap to set
	 */
	public void setDecryptProperties(List<String> decryptProperties) {
		this.decryptProperties = decryptProperties;
	}

	/**
	 * Get a value based on key , if key does not exist , null is returned
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return ctxPropertiesMap.get(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * Get a value based on key , if key does not exist , null is returned
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key, String defaultValue) {
		try {
			String value = ctxPropertiesMap.get(key);
			if (DataUtil.isEmpty(value)) {
				return defaultValue;
			}
			return value;
		} catch (MissingResourceException e) {
			return defaultValue;
		}
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(ctxPropertiesMap.get(key));
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		String value = ctxPropertiesMap.get(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = ctxPropertiesMap.get(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return new Boolean(value);
	}

	public static void main(String[] args) {
		String encrypt = SecurityUtil.encryptDes("buzhidao", KEY);
		System.out.println(encrypt);
		System.out.println(SecurityUtil.decryptDes(encrypt, KEY));
	}
}
