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

	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
