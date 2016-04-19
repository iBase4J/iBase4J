package org.ibase4j.core.interceptor;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@PropertySource("classpath:config/whiteURL.properties")
public class BaseInterceptor extends HandlerInterceptorAdapter {
	protected static Logger logger = LogManager.getLogger();
	protected static String[] notFilter = new String[] {};

	static {
		String url = "";
		ResourceBundle bundle = ResourceBundle.getBundle("config/whiteURL");
		for (Enumeration<?> iterator = bundle.getKeys(); iterator.hasMoreElements();) {
			String key = (String) iterator.nextElement();
			url += "," + bundle.getString(key) + ",";
		}
		notFilter = url.split(",");
	}

	// 拦截器白名单
	protected boolean whiteURL(HttpServletRequest request) throws Exception {
		String url = request.getServletPath();
		boolean success = true;
		for (String s : notFilter) { // 如果uri中包含不过滤的uri，则不进行过滤
			if (url.indexOf(s) != -1 && StringUtils.isNotBlank(s)) {
				return success;
			}
		}
		return false;
	}
}
