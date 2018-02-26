package org.ibase4j.core.config;

import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.ibase4j.core.shiro.Realm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import top.ibase4j.core.listener.SessionListener;
import top.ibase4j.core.support.cache.shiro.RedisCacheManager;
import top.ibase4j.core.support.cache.shiro.RedisSessionDAO;
import top.ibase4j.core.util.FileUtil;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.PropertiesUtil;

/**
 * 权限拦截配置
 * 
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:40:20
 */
@Configuration
@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
public class ShiroConfig {
    static String filters = "/=anon;/app/**=anon;/index.jsp=anon;/regin=anon;/login=anon;/*.ico=anon;/upload/*=anon;"
        + "/unauthorized=anon;/forbidden=anon;/sns*=anon;/*/api-docs=anon;/callback*=anon;/swagger*=anon;"
        + "/configuration/*=anon;/*/configuration/*=anon;/webjars/**=anon;" + "/**=authc,user";
    static {
        String path = ShiroConfig.class.getResource("/").getFile();
        try {
            List<String> urlList = FileUtil.readFile(path + "config/shiro.config");
            if (urlList != null) {
                StringBuilder sb = new StringBuilder();
                for (String url : urlList) {
                    sb.append(url.trim());
                    if (!url.trim().endsWith(";")) {
                        sb.append(";");
                    }
                }
                filters = sb.toString();
            }
        } catch (Exception e) {
        }
    }

    @Bean
    public Realm realm(SessionDAO sessionDAO) {
        return new Realm();
    }

    @Bean
    public SessionListener sessionListener() {
        return new SessionListener();
    }

    @Bean
    public SessionDAO sessionDAO() {
        return new RedisSessionDAO();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm, SessionManager sessionManager,
        RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        manager.setCacheManager(new RedisCacheManager());
        manager.setSessionManager(sessionManager);
        manager.setRememberMeManager(rememberMeManager);
        return manager;
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO, SessionListener sessionListener, Cookie cookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.getSessionListeners().add(sessionListener);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    @Bean
    public Cookie cookie() {
        SimpleCookie cookie = new SimpleCookie("IBASE4JSESSIONID");
        cookie.setSecure(PropertiesUtil.getBoolean("session.cookie.secure", false));
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        return cookie;
    }

    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.getCookie().setMaxAge(PropertiesUtil.getInt("rememberMe.cookie.maxAge", 60 * 60 * 24));
        return rememberMeManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
        factory.setSecurityManager(securityManager);
        factory.setLoginUrl("/unauthorized");
        factory.setUnauthorizedUrl("/forbidden");
        Map<String, String> filterMap = InstanceUtil.newLinkedHashMap();
        for (String filter : filters.split("\\;")) {
            String[] keyValue = filter.split("\\=");
            filterMap.put(keyValue[0], keyValue[1]);
        }
        factory.setFilterChainDefinitionMap(filterMap);
        return factory;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAdvisor(
        org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
