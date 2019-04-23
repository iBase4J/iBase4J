package org.ibase4j.core;

import org.apache.dubbo.config.DubboShutdownHook;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import top.ibase4j.core.listener.ApplicationReadyListener;

@Component
public class WebServerListener extends ApplicationReadyListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextStoppedEvent) { // 应用停止
            DubboShutdownHook.getDubboShutdownHook().doDestroy();
        } else if (event instanceof ContextClosedEvent) { // 应用关闭
            DubboShutdownHook.getDubboShutdownHook().doDestroy();
        }
        super.onApplicationEvent(event);
    }
}