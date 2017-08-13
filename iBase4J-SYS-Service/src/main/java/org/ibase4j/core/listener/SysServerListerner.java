package org.ibase4j.core.listener;

import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.provider.ISysDicProvider;
import org.ibase4j.provider.ISysUserProvider;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SysServerListerner extends ServerListener {
	protected final Logger logger = LogManager.getLogger(this.getClass());

	public void contextInitialized(ServletContextEvent contextEvent) {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		context.getBean(ISysUserProvider.class).init();
		ISysDicProvider sysDicProvider = context.getBean(ISysDicProvider.class);
		sysDicProvider.getAllDic();
		super.contextInitialized(contextEvent);
	}
}