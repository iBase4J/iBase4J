package org.ibase4j.mybatis.scheduler.model;

import java.io.Serializable;

import org.ibase4j.mybatis.generator.model.TaskScheduler;

/**
 * 计划任务信息
 * 
 * @author ShenHuaJie
 * @version $Id: ScheduleJob.java, v 0.1 2015-1-19 下午7:35:44 ShenHuaJie Exp $
 */
@SuppressWarnings("serial")
public class TaskSchedulerBean extends TaskScheduler implements Serializable {

	private String taskGroup;
	private String status;

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
