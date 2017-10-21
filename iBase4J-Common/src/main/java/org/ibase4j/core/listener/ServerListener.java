package org.ibase4j.core.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ServerListener implements ApplicationListener<ApplicationReadyEvent> {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("=================================");
        String server = event.getSpringApplication().getSources().iterator().next().toString();
        logger.info("系统[{}]启动完成!!!", server.substring(server.lastIndexOf(".") + 1));
        logger.info("=================================");
    }
}
