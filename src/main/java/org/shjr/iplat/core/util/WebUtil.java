package org.shjr.iplat.core.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.shjr.iplat.core.Constants;

/**
 * Web层辅助类
 * 
 * @author ShenHuaJie
 * @version $Id: WebUtil.java, v 0.1 2014年4月4日 上午8:58:19 ShenHuaJie Exp $
 */
public class WebUtil {

	/**
	 * 获取一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	public static Object getSessionAttribute(HttpServletRequest request, String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}

	/**
	 * 设置一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	public static void setSessionAttribute(HttpServletRequest request, String sessionKey, Object objSessionAttribute) {
		HttpSession session = request.getSession();
		if (session != null)
			session.setAttribute(sessionKey, objSessionAttribute);
	}

	/**
	 * 移除Session对象属性值
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	public static void removeSessionAttribute(HttpServletRequest request, String sessionKey) {
		HttpSession session = request.getSession();
		if (session != null)
			session.removeAttribute(sessionKey);
	}

	/**
	 * 获取指定Cookie的值
	 * 
	 * @param cookies cookie集合
	 * @param cookieName cookie名字
	 * @param defaultValue 缺省值
	 * @return
	 */
	public static String getCookieValue(Cookie[] cookies, String cookieName, String defaultValue) {
		if (cookies == null) {
			return defaultValue;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
				return (cookie.getValue());
		}
		return defaultValue;
	}

	// 获取SessionId
	private static String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String sessionName = "RSESSIONID";
		String sessionId = getCookieValue(cookies, sessionName, null);
		if (StringUtil.isBlank(sessionId)) {
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
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> names = null;
		names = request.getParameterNames();
		String name = null;
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			map.put(name, request.getParameter(name));
		}
		return map;
	}
}
