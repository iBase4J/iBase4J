package org.ibase4j.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.ISysCacheService;
import org.ibase4j.service.ISysDicService;
import org.ibase4j.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

import top.ibase4j.core.listener.ApplicationReadyListener;

@Component
public class SysServerListerner extends ApplicationReadyListener {
	protected final Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	ISysCacheService cacheService;
	@Autowired
	ISysDicService dicService;
	@Autowired
	ISysUserService userService;

	public void onApplicationEvent(ApplicationReadyEvent event) {
		cacheService.flush();
		userService.init();
		dicService.getAllDic();
		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		super.onApplicationEvent(event);
	}
}