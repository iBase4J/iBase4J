package org.ibase4j.service.sys;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.mybatis.sys.model.ScheduleJob;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 */
@Service
public class SysScheduleJobService {
	private Logger logger = LogManager.getLogger();

	// 获取任务工厂
	private ScheduledTaskRegistrar geTaskRegistrar() {
		WebApplicationContext app = ContextLoader.getCurrentWebApplicationContext();
		return app.getBean(ScheduledTaskRegistrar.class);
	}

	// 获取所有任务
	public List<ScheduleJob> getAllJobDetail() {
		List<ScheduleJob> result = new LinkedList<ScheduleJob>();
		try {
			ScheduledTaskRegistrar registrar = geTaskRegistrar();
			List<CronTask> cronTasks = registrar.getCronTaskList();
			for (CronTask cronTask : cronTasks) {
				ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) cronTask.getRunnable();
				ScheduleJob job = new ScheduleJob();
				job.setJobId(String.valueOf(cronTask.hashCode()));
				job.setJobName(runnable.getMethod().getName());
				job.setJobGroup(runnable.getTarget().getClass().getSimpleName());
				job.setCronExpression(cronTask.getExpression());
				result.add(job);
			}
		} catch (Exception e) {
			logger.error("Try to load All JobDetail cause error : " + e.getMessage(), e);
		}
		return result;
	}

	// 执行任务
	public boolean execTask(int id) {
		try {
			ScheduledTaskRegistrar registrar = geTaskRegistrar();
			List<CronTask> cronTasks = registrar.getCronTaskList();
			for (CronTask cronTask : cronTasks) {
				if (cronTask.hashCode() == id) {
					cronTask.getRunnable().run();
					return true;
				}
			}
		} catch (Exception e) {
			logger.error("Try to exec Task cause error : " + e.getMessage(), e);
		}
		return false;
	}
}
