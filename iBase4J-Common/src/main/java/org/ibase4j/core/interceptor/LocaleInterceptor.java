package org.ibase4j.core.interceptor;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.util.WebUtil;
import org.springframework.context.i18n.LocaleContextHolder;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

/**
 * 国际化信息设置(基于SESSION)
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:45
 */
public class LocaleInterceptor extends BaseInterceptor {
	protected static Logger logger = LogManager.getLogger();

	static UASparser uasParser = null;

	// 初始化uasParser对象
	static {
		try {
			uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
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
		session.setAttribute("HOST", WebUtil.getHost(request));
		LocaleContextHolder.setLocale(locale);

		try {
			UserAgentInfo userAgentInfo = uasParser.parse(request.getHeader("user-agent"));
			String userAgent = userAgentInfo.getOsName() + " " + userAgentInfo.getType() + " "
					+ userAgentInfo.getUaName();
			session.setAttribute(Constants.USER_AGENT, userAgent);
		} catch (IOException e) {
			logger.error("", e);
		}
		return super.preHandle(request, response, handler);
	}
}
