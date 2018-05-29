package org.ibase4j.core.listener;

import javax.servlet.ServletContextEvent;

import org.ibase4j.service.SysCacheService;
import org.ibase4j.service.SysUserService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

import top.ibase4j.core.listener.ServerListener;

/**
 * @author ShenHuaJie
 * @since 2018年3月3日 下午7:28:29
 */
@SuppressWarnings("deprecation")
public class SysServerListener extends ServerListener {

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        context.getBean(SysCacheService.class).flush();
        context.getBean(SysUserService.class).init();
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        super.contextInitialized(contextEvent);
    }
}