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

import com.alibaba.dubbo.config.ProtocolConfig;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

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
            MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        } else if (event instanceof ContextStoppedEvent) { // 应用停止
            ProtocolConfig.destroyAll();
        } else if (event instanceof ContextClosedEvent) { // 应用关闭
            ProtocolConfig.destroyAll();
        }
        super.onApplicationEvent(event);
    }
}
