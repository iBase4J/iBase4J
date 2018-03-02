package org.ibase4j.core;

import org.ibase4j.service.sys.SysCacheService;
import org.ibase4j.service.sys.SysDicService;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import top.ibase4j.core.listener.ApplicationReadyListener;

@Component
public class ServerListener extends ApplicationReadyListener {
	public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        context.getBean(SysCacheService.class).flush();
        context.getBean(SysUserService.class).init();
        SysDicService sysDicService = context.getBean(SysDicService.class);
        sysDicService.getAllDic();
		super.onApplicationEvent(event);
	}
}