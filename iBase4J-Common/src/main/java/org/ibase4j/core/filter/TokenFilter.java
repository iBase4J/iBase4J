package org.ibase4j.core.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.support.Token;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.TokenUtil;
import org.ibase4j.core.util.WebUtil;

import com.alibaba.fastjson.JSON;

public class TokenFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(TokenFilter.class);

	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String token = request.getHeader("token");
		if (StringUtils.isNotBlank(token)) {
			try {
				Token tokenInfo = TokenUtil.getTokenInfo(token);
				if (tokenInfo != null) {
					Long now = System.currentTimeMillis();
					if (now - tokenInfo.getTime() < 1000 * 60 * 30) {
						String value = tokenInfo.getValue();
						WebUtil.setSession(Constants.CURRENT_USER, Long.parseLong(value));
						TokenUtil.setTokenInfo(token, value);
						chain.doFilter(request, response);
						return;
					}
				}
			} catch (Exception e) {
				logger.error("token检查发生异常:", e);
			}
		}
		// 响应
		response.setContentType("text/html; charset=UTF-8");
		Map<String, Object> modelMap = InstanceUtil.newLinkedHashMap();
		modelMap.put("httpCode", HttpCode.UNAUTHORIZED.value());
		modelMap.put("msg", HttpCode.UNAUTHORIZED.msg());
		modelMap.put("timestamp", System.currentTimeMillis());
		PrintWriter out = response.getWriter();
		out.println(JSON.toJSONString(modelMap));
		out.flush();
		out.close();
	}

	public void destroy() {

	}

	public void init(FilterConfig config) throws ServletException {

	}
}
