package org.ibase4j.scheduled;

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
    }
}
