package org.ibase4j.provider.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.provider.sys.ISysSessionProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@DubboService(interfaceClass = CoreTaskProvider.class)
public class CoreTaskService implements CoreTaskProvider {
    private final Logger logger = LogManager.getLogger();
    @Autowired
    private ISysSessionProvider sessionProvider;

    /** 定时清除会话信息 */
    public void cleanExpiredSessions() {
        logger.info("cleanExpiredSessions");
        sessionProvider.cleanExpiredSessions();
    }
}
