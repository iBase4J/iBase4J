package org.ibase4j.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.SysCacheService;
import org.ibase4j.service.SysUserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import top.ibase4j.core.listener.ApplicationReadyListener;

@Component
public class SysServerListener extends ApplicationReadyListener {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
            ConfigurableApplicationContext context = ((ApplicationReadyEvent)event).getApplicationContext();
            context.getBean(SysCacheService.class).flush();
            context.getBean(SysUserService.class).init();
        } else if (event instanceof ContextStoppedEvent) { // 应用停止
        } else if (event instanceof ContextClosedEvent) { // 应用关闭
        }
        super.onApplicationEvent(event);
    }
}
