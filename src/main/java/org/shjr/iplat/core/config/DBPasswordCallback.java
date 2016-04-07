package org.shjr.iplat.core.config;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.shjr.iplat.core.util.SecurityUtil;

import com.alibaba.druid.util.DruidPasswordCallback;

@SuppressWarnings("serial")
public class DBPasswordCallback extends DruidPasswordCallback {
	private static final byte[] key = { 9, -1, 0, 5, 39, 8, 6, 19 };

	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String pwd = properties.getProperty("password");
		if (StringUtils.isNotBlank(pwd)) {
			try {
				String password = SecurityUtil.decryptDes(pwd, key);
				setPassword(password.toCharArray());
			} catch (Exception e) {
				setPassword(pwd.toCharArray());
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(SecurityUtil.encryptDes("", key));
	}
}
