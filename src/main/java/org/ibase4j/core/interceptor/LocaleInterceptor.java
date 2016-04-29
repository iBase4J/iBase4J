package org.ibase4j.core.interceptor;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.config.Resources;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 国际化信息设置(基于SESSION)
 * 
 * @author ShenHuaJie
 */
public class LocaleInterceptor extends BaseInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getServletPath();
		logger.info(url);
		// 设置客户端语言
		HttpSession session = request.getSession();
		Locale locale = (Locale) session.getAttribute("LOCALE");
		if (locale == null) {
			String language = request.getParameter("locale");
			if (StringUtils.isNotBlank(language)) {
				locale = new Locale(language);
				session.setAttribute("LOCALE", locale);
			} else {
				locale = request.getLocale();
			}
		}
		LocaleContextHolder.setLocale(locale);
		// 拦截器白名单
		boolean success = true;
		for (String s : notFilter) { // 如果uri中包含不过滤的uri，则不进行过滤
			if (url.indexOf(s) != -1 && StringUtils.isNotBlank(s)) {
				return success;
			}
		}
		return nextInterceptor(request, response, handler);
	}

	private static String[] notFilter = new String[] {};

	static {
		String url = "";
		for (Enumeration<?> iterator = Resources.WHITEURL.getKeys(); iterator.hasMoreElements();) {
			String key = (String) iterator.nextElement();
			url += "," + Resources.WHITEURL.getString(key) + ",";
		}
		notFilter = StringUtils.split(url, ",");
	}
}
