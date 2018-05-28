package org.ibase4j.core;

import org.ibase4j.service.sys.SysCacheService;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import top.ibase4j.core.listener.ApplicationReadyListener;

@Component
public class ServerListener extends ApplicationReadyListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
            ConfigurableApplicationContext context = ((ApplicationReadyEvent)event).getApplicationContext();
            context.getBean(SysCacheService.class).flush();
            context.getBean(SysUserService.class).init();
        }
        super.onApplicationEvent(event);
    }
}
