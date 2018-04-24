package org.ibase4j.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.ISysCacheService;
import org.ibase4j.service.ISysDicService;
import org.ibase4j.service.ISysUserService;
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

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
            ConfigurableApplicationContext context = ((ApplicationReadyEvent)event).getApplicationContext();
            context.getBean(ISysCacheService.class).flush();
            context.getBean(ISysUserService.class).init();
            ISysDicService sysDicService = context.getBean(ISysDicService.class);
            sysDicService.getAllDic();
            MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        } else if (event instanceof ContextStoppedEvent) { // 应用停止
            ProtocolConfig.destroyAll();
        } else if (event instanceof ContextClosedEvent) { // 应用关闭
            ProtocolConfig.destroyAll();
        }
        super.onApplicationEvent(event);
    }
}
