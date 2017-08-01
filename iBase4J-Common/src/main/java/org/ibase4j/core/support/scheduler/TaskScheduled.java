package org.ibase4j.core.support.scheduler;

import java.util.Date;

import org.ibase4j.core.base.BaseModel;

/**
 * 计划任务信息
 * 
 * @author ShenHuaJie
 * @version $Id: ScheduleJob.java, v 0.1 2015-1-19 下午7:35:44 ShenHuaJie Exp $
 */
@SuppressWarnings("serial")
public class TaskScheduled extends BaseModel {
	public interface JobType {
		String job = "job";
		String statefulJob = "statefulJob";
	}

	public interface TaskType {
		String local = "LOCAL";
		String dubbo = "DUBBO";
	}

	public TaskScheduled() {
	}

	public TaskScheduled(String taskGroup, String taskName) {
		this.taskGroup = taskGroup;
		this.taskName = taskName;
	}

	/** 任务名称 */
	private String taskName;
	/** 任务分组 */
	private String taskGroup;
	/** 任务状态 0禁用 1启用 2删除 */
	private String status;
	/** 任务运行时间表达式 */
	private String taskCron;
	/** 最后一次执行时间 */
	private Date previousFireTime;
	/** 下次执行时间 */
	private Date nextFireTime;
	/** 任务描述 */
	private String taskDesc;
	// 任务类型(是否阻塞)
	private String jobType;
	// 本地任务/dubbo任务
	private String taskType;
	// 运行系统(dubbo任务必须)
	private String targetSystem;
	// 任务对象
	private String targetObject;
	// 任务方法
	private String targetMethod;
	// 通知邮箱地址
	private String contactName;
	private String contactEmail;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

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

	public String getTaskCron() {
		return taskCron;
	}

	public void setTaskCron(String taskCron) {
		this.taskCron = taskCron;
	}

	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * @param jobType
	 *            the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the taskType
	 */
	public String getTaskType() {
		return taskType;
	}

	/**
	 * @param taskType
	 *            the taskType to set
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return the targetSystem
	 */
	public String getTargetSystem() {
		return targetSystem;
	}

	/**
	 * @param targetSystem
	 *            the targetSystem to set
	 */
	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}

	/**
	 * @return the targetObject
	 */
	public String getTargetObject() {
		return targetObject;
	}

	/**
	 * @param targetObject
	 *            the targetObject to set
	 */
	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	/**
	 * @return the targetMethod
	 */
	public String getTargetMethod() {
		return targetMethod;
	}

	/**
	 * @param targetMethod
	 *            the targetMethod to set
	 */
	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param contactEmail
	 *            the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
}
