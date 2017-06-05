package org.ibase4j.core.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.SysSession;
import org.ibase4j.model.SysUser;
import org.ibase4j.service.SysAuthorizeService;
import org.ibase4j.service.SysSessionService;
import org.ibase4j.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 权限检查类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:44:45
 */
public class Realm extends AuthorizingRealm {
    private final Logger logger = LogManager.getLogger();
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysSessionService sysSessionService;
    @Autowired
    private SysAuthorizeService sysAuthorizeService;

    // 权限
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Long userId = WebUtil.getCurrentUser();
        List<String> list = sysAuthorizeService.queryPermissionByUserId(userId);
        for (String permission : list) {
            if (StringUtils.isNotBlank(permission)) {
                // 添加基于Permission的权限信息
                info.addStringPermission(permission);
            }
        }
        // 添加用户权限
        info.addStringPermission("user");
        return info;
    }

    // 登录验证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
        throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("countSql", 0);
        params.put("enable", 1);
        params.put("account", token.getUsername());
        Page<SysUser> pageInfo = sysUserService.query(params);
        if (pageInfo.getTotal() == 1) {
            SysUser user = pageInfo.getRecords().get(0);
            StringBuilder sb = new StringBuilder(100);
            for (int i = 0; i < token.getPassword().length; i++) {
                sb.append(token.getPassword()[i]);
            }
            if (user.getPassword().equals(sb.toString())) {
                WebUtil.saveCurrentUser(user.getId());
                saveSession(user.getAccount());
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(),
                    user.getUserName());
                return authcInfo;
            }
            logger.warn("USER [{}] PASSWORD IS WRONG: {}", token.getUsername(), sb.toString());
            return null;
        } else {
            logger.warn("No user: {}", token.getUsername());
            return null;
        }
    }

    /** 保存session */
    private void saveSession(String account) {
        // 踢出用户
        sysSessionService.deleteByAccount(account);
        SysSession record = new SysSession();
        record.setAccount(account);
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        record.setSessionId(session.getId().toString());
        String host = (String)session.getAttribute("HOST");
        record.setIp(StringUtils.isBlank(host) ? session.getHost() : host);
        record.setStartTime(session.getStartTimestamp());
        sysSessionService.update(record);
    }
}
