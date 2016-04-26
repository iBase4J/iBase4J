package org.ibase4j.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 权限拦截器 */
public class PermissionInterceptor extends BaseInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (super.whiteURL(request)) {
			return true;
		}
		boolean success = true;
		// String url = request.getServletPath();
		// 判断用户是否有该请求的权限
		return success;
	}
}
