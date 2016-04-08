/**
 * 
 */
package org.shjr.ibase4j.service.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:19:58
 */
@Service
public class TestTask {
	Logger logger = LogManager.getLogger();

	public void run() {
		logger.info("I am a test.");
	}
}
