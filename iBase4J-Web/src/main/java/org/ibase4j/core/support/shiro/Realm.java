package org.ibase4j.core.support.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.mybatis.generator.model.SysSession;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.mybatis.sys.model.SysUserBean;
import org.ibase4j.service.sys.SysSessionService;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
/**
 * 权限检查类
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:44:45
 */
public class Realm extends AuthorizingRealm {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysSessionService sysSessionService;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("countSql", 0);
		params.put("usable", 1);
		params.put("account", token.getUsername());
		StringBuilder sb = new StringBuilder(100);
		for (int i = 0; i < token.getPassword().length; i++) {
			sb.append(token.getPassword()[i]);
		}
		params.put("password", sb.toString());
		PageInfo<SysUserBean> pageInfo = sysUserService.queryBeans(params);
		if (pageInfo.getSize() == 1) {
			SysUser user = pageInfo.getList().get(0);
			WebUtil.saveCurrentUser(user.getId());
			saveSession(user.getAccount());
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(),
					user.getUserName());
			return authcInfo;
		} else {
			return null;
		}
	}

	/** 保存session */
	private void saveSession(String account) {
		SysSession record = new SysSession();
		record.setAccount(account);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		record.setSessionId(session.getId().toString());
		String host = (String) session.getAttribute("HOST");
		record.setIp(StringUtils.isBlank(host) ? session.getHost() : host);
		record.setStartTime(session.getStartTimestamp());
		sysSessionService.update(record);
	}
}
