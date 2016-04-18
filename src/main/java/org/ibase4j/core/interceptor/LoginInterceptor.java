package org.ibase4j.core.interceptor;

import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.WebUtil;

/**
 * 登录拦截器
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:19:47
 */
public class LoginInterceptor extends BaseInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getServletPath();
		logger.info(url);
		if (super.whiteURL(request)) {
			return true;
		}
		boolean success = true;
		String curruser = WebUtil.getCurrentUser(request);
		if (url.indexOf("online") == -1) { // 后端请求后端数据
			if (curruser == null) {
				success = false;
				response.setStatus(HttpCode.HTTP_CODE_401);
			}
		} else { // 前端请求后端数据
			if (curruser == null) {
				success = false;
				String isAjax = request.getHeader("x-requested-with");
				if (StringUtils.isNotEmpty(isAjax)) {
					response.setStatus(HttpCode.HTTP_CODE_401);
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
		if (!success)
			logger.info("Interceptor:" + HttpCode.MSG.get(response.getStatus()));
		return success;
	}
}
