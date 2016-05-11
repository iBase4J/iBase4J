package org.ibase4j.core.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.support.HttpCode;
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
		HttpSession session = request.getSession();
		Long preRequestTime = (Long) session.getAttribute("PREREQUESTTIME");
		if (preRequestTime != null) { // 过滤频繁操作
			if (System.currentTimeMillis() - preRequestTime < 1000) {
				Integer illRequestTimes = (Integer) session.getAttribute("ILLREQUESTTIMES");
				if (illRequestTimes == null) {
					illRequestTimes = 1;
				} else {
					illRequestTimes++;
				}
				if (illRequestTimes > 2) {
					response.setStatus(HttpCode.CONFLICT.value());
					return false;
				}
				session.setAttribute("ILLREQUESTTIMES", illRequestTimes);
			} else {
				session.setAttribute("ILLREQUESTTIMES", 0);
			}
		}
		session.setAttribute("PREREQUESTTIME", System.currentTimeMillis());
		// 设置客户端语言
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
		return nextInterceptor(request, response, handler);
	}
}
