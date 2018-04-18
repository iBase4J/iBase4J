package org.ibase4j.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.SysSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@EnableScheduling
public class CoreTask {
	private final Logger logger = LogManager.getLogger();
	@Autowired
	private SysSessionService sessionService;

	/** 定时清除会话信息 */
	@Scheduled(cron = "0 0/5 * * * *")
	public void cleanExpiredSessions() {
		logger.info("cleanExpiredSessions");
		sessionService.cleanExpiredSessions();
        long mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        long total = runtime.totalMemory() / mb;
        long max = runtime.maxMemory() / mb;
        long free = runtime.freeMemory() / mb;
        logger.info("最大内存: {}m; 已分配内存: {}m; 已分配内存中的剩余空间: {}m; 最大可用内存: {}m.", max, total, free, max - total + free);
	}
}
