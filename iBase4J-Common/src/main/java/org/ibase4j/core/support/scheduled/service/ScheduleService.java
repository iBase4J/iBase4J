package org.ibase4j.core.support.scheduled.service;

import java.util.List;

import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.scheduled.TaskScheduler;
import org.ibase4j.core.support.scheduled.provider.ScheduleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:20
 */
@Service
public class ScheduleService {
	@Autowired
	private ScheduleProvider scheduleFacade;

	public List<TaskScheduler> getAllJobDetail() {
		return scheduleFacade.getAllTaskDetail();
	}

	public boolean execTask(String taskGroup, String taskName) {
		Assert.notNull(taskGroup, Resources.getMessage("TASKGROUP_IS_NULL"));
		Assert.notNull(taskName, Resources.getMessage("TASKNAME_IS_NULL"));
		return scheduleFacade.execTask(taskName, taskGroup);
	}

	public boolean openTask(String taskGroup, String taskName) {
		Assert.notNull(taskGroup, Resources.getMessage("TASKGROUP_IS_NULL"));
		Assert.notNull(taskName, Resources.getMessage("TASKNAME_IS_NULL"));
		return scheduleFacade.openCloseTask(taskGroup, taskName, "start");
	}

	public boolean closeTask(String taskGroup, String taskName) {
		Assert.notNull(taskGroup, Resources.getMessage("TASKGROUP_IS_NULL"));
		Assert.notNull(taskName, Resources.getMessage("TASKNAME_IS_NULL"));
		return scheduleFacade.openCloseTask(taskGroup, taskName, "stop");
	}
}
