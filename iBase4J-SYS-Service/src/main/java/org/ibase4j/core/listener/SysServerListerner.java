package org.ibase4j.core.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.ISysCacheService;
import org.ibase4j.service.ISysDicService;
import org.ibase4j.service.ISysUserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

import top.ibase4j.core.listener.ApplicationReadyListener;

@Component
public class SysServerListerner extends ApplicationReadyListener {
	protected final Logger logger = LogManager.getLogger(this.getClass());

	public void onApplicationEvent(ApplicationReadyEvent event) {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		context.getBean(ISysCacheService.class).flush();
		context.getBean(ISysUserService.class).init();
		ISysDicService sysDicProvider = context.getBean(ISysDicService.class);
		sysDicProvider.getAllDic();
		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		super.onApplicationEvent(event);
	}
}