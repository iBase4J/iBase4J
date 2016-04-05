package org.shjr.iplat.core.util;

import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.shjr.iplat.core.Constants;
import org.springframework.web.util.WebUtils;

/**
 * Web层辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:19:28
 */
public class WebUtil {
	private WebUtil() {
	}

	/**
	 * 获取指定Cookie的值
	 * 
	 * @param cookies cookie集合
	 * @param cookieName cookie名字
	 * @param defaultValue 缺省值
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		if (cookie == null) {
			return defaultValue;
		}
		return cookie.getValue();
	}

	// 获取SessionId
	private static String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		String sessionName = "RSESSIONID";
		String sessionId = getCookieValue(request, sessionName, null);
		if (StringUtils.isBlank(sessionId)) {
			sessionId = request.getSession().getId();
		}
		return sessionId;
	}

	/** 保存当前用户 */
	public static void saveCurrentUser(HttpServletRequest request, HttpServletResponse response, Object user) {
		RedisUtil.hset(Constants.CURRENT_USER, getSessionId(request, response), user);
	}

	/** 获取当前用户 */
	public static String getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
		return RedisUtil.hget(Constants.CURRENT_USER, getSessionId(request, response));
	}

	/**
	 * 获得国际化信息
	 * 
	 * @param key 键
	 * @param request
	 * @return
	 */
	public static String getApplicationResource(String key, HttpServletRequest request) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
		return resourceBundle.getString(key);
	}

	/**
	 * 获得参数Map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		return WebUtils.getParametersStartingWith(request, null);
	}
}
