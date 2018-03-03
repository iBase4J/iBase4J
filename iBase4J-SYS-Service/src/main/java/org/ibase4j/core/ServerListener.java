package org.ibase4j.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.SysCacheService;
import org.ibase4j.service.SysDicService;
import org.ibase4j.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

/**
 * @author ShenHuaJie
 * @since 2018年3月3日 下午7:28:29
 */
public class ServerListener implements ServletContextListener {
	protected final Logger logger = LogManager.getLogger();
	@Autowired
	SysCacheService cacheService;
	@Autowired
	SysDicService dicService;
	@Autowired
	SysUserService userService;

	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		cacheService.flush();
		userService.init();
		dicService.getAllDic();
		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		logger.info("=================================");
		logger.info("系统[{}]启动完成!!!", contextEvent.getServletContext().getServletContextName());
		logger.info("=================================");
	}
}