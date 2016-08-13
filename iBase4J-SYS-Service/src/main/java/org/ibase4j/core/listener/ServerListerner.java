package org.ibase4j.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.provider.SysDicProvider;
import org.ibase4j.provider.SysUserProvider;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ServerListerner implements ServletContextListener {
	protected final Logger logger = LogManager.getLogger(this.getClass());

	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		context.getBean(SysUserProvider.class).init();
		SysDicProvider sysDicProvider = context.getBean(SysDicProvider.class);
		sysDicProvider.getAllDic();
		logger.info("=================================");
		logger.info("系统[{}]启动完成!!!", contextEvent.getServletContext().getServletContextName());
		logger.info("=================================");
	}
}