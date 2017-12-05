package org.ibase4j.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.SysCacheService;
import org.ibase4j.service.SysDicService;
import org.ibase4j.service.SysUserService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

public class ServerListener implements ServletContextListener {
	protected final Logger logger = LogManager.getLogger();

	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		context.getBean(SysCacheService.class).flush();
		context.getBean(SysUserService.class).init();
		SysDicService sysDicService = context.getBean(SysDicService.class);
		sysDicService.getAllDic();
		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		logger.info("=================================");
		logger.info("系统[{}]启动完成!!!", contextEvent.getServletContext().getServletContextName());
		logger.info("=================================");
	}
}