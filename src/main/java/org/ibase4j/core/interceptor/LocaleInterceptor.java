package org.ibase4j.core.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 国际化信息设置(基于SESSION)
 * 
 * @author ShenHuaJie
 */
public class LocaleInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
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
		return true;
	}
}
