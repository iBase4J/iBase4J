package org.ibase4j.core.support.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.service.sys.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * URL权限过滤
 */
public class PermisssionFilter extends PermissionsAuthorizationFilter {
	private final Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private SysPermissionService permissionService;

	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		String url = ((HttpServletRequest) request).getServletPath();
		Integer userId = WebUtil.getCurrentUser();
		if (userId == null) {
			return false;
		}
		if (permissionService.doCheckPermissionByUserId(userId, url)) {
			return true;
		}
		logger.warn("[{}]{}:{}", userId, HttpCode.FORBIDDEN.msg(), url);
		return false;
	}
}
