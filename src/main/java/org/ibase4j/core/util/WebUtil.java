package org.ibase4j.core.util;

import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.ibase4j.core.Constants;
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

	/** 用户密码加密 */
	public static String encryptPassword(String password) {
		return SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password));
	}

	/** 保存当前用户 */
	public static void saveCurrentUser(Object user) {
		setSession(Constants.CURRENT_USER, user);
	}

	/** 获取当前用户 */
	public static Integer getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				return (Integer) session.getAttribute(Constants.CURRENT_USER);
			}
		}
		return null;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	public static void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	/** 移除当前用户 */
	public static void removeCurrentUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.CURRENT_USER);
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
