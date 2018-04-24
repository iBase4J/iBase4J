package org.ibase4j.core.listener;

import javax.servlet.ServletContextEvent;

import org.ibase4j.service.ISysCacheService;
import org.ibase4j.service.ISysDicService;
import org.ibase4j.service.ISysUserService;
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

	public void contextInitialized(ServletContextEvent contextEvent) {
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		context.getBean(ISysCacheService.class).flush();
		context.getBean(ISysUserService.class).init();
		context.getBean(ISysDicService.class).getAllDic();
		super.contextInitialized(contextEvent);
	}
}