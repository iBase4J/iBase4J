package org.ibase4j.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseInterceptor extends HandlerInterceptorAdapter {
	protected final Logger logger = LogManager.getLogger();
	private BaseInterceptor[] nextInterceptor;

	public void setNextInterceptor(BaseInterceptor... nextInterceptor) {
		this.nextInterceptor = nextInterceptor;
	}

	protected boolean nextInterceptor(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (nextInterceptor == null) {
			return true;
		}
		for (int i = 0; i < nextInterceptor.length; i++) {
			if (!nextInterceptor[i].preHandle(request, response, handler)) {
				return false;
			}
		}
		return true;
	}
}
