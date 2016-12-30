package org.ibase4j.core.support.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.PersistJobDataAfterExecution;

/**
 * 阻塞调度
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:30:46
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulJob extends BaseJob implements Job {
}
