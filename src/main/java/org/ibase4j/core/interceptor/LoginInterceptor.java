package org.ibase4j.core.interceptor;

import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.mybatis.generator.model.SysUser;

/**
 * 登录拦截器
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:19:47
 */
public class LoginInterceptor extends BaseInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean success = true;
		HttpCode httpCode = null;
		SysUser curruser = WebUtil.getCurrentUser();
		String url = request.getServletPath();
		if (url.indexOf("online") == -1) { // 后端请求
			if (curruser == null) {
				success = false;
				httpCode = HttpCode.FORBIDDEN;
			}
		} else { // 前端请求
			if (curruser == null) {
				success = false;
				String isAjax = request.getHeader("x-requested-with");
				if (StringUtils.isNotEmpty(isAjax)) { // 异步
					httpCode = HttpCode.FORBIDDEN;
				} else {
					String host = InetAddress.getLocalHost().getHostAddress();
					String redirect = "/home.html#/login";
					if (host != null && host.startsWith("192.168.1")) {
						redirect = "/";
					}
					PrintWriter writer = response.getWriter();
					writer.write("<script type=\"text/javascript\">window.location.href='" + redirect + "'</script>");
					writer.flush();
					writer.close();
				}
			}
		}
		if (httpCode != null) {
			response.setStatus(httpCode.value());
			logger.info("Interceptor:" + httpCode.msg());
		}
		if (success) {
			return nextInterceptor(request, response, handler);
		} else {
			return success;
		}
	}
}
