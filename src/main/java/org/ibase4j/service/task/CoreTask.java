package org.ibase4j.service.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.config.Resources;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CoreTask {
	Logger logger = LogManager.getLogger();

	/** 定时清除国际化信息 */
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run() {
		Resources.flushMessage();
		logger.info("Messages flushed!");
	}
}
