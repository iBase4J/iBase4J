package org.ibase4j.service.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.config.Resources;
import org.springframework.stereotype.Component;

@Component("coreTask")
public class CoreTask {
	private final Logger logger = LogManager.getLogger();

	/** 定时清除国际化信息 */
	public void run() {
		Resources.flushMessage();
		logger.info("Messages flushed!");
	}
}
