package org.ibase4j.core;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.ProtocolConfig;

import top.ibase4j.core.listener.ApplicationReadyListener;

@Component
public class WebServerListener extends ApplicationReadyListener {
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextStoppedEvent) { // 应用停止
            ProtocolConfig.destroyAll();
        } else if (event instanceof ContextClosedEvent) { // 应用关闭
            ProtocolConfig.destroyAll();
        }
        super.onApplicationEvent(event);
    }
}
