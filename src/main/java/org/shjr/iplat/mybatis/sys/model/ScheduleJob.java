package org.shjr.iplat.mybatis.sys.model;

import java.io.Serializable;

/**
 * 计划任务信息
 * 
 * @author ShenHuaJie
 * @version $Id: ScheduleJob.java, v 0.1 2015-1-19 下午7:35:44 ShenHuaJie Exp $
 */
@SuppressWarnings("serial")
public class ScheduleJob implements Serializable {

	/** 任务id */
	private String jobId;

	/** 任务名称 */
	private String jobName;

	/** 任务分组 */
	private String jobGroup;

	/** 任务状态 0禁用 1启用 2删除 */
	private String jobStatus;

	/** 任务运行时间表达式 */
	private String cronExpression;

	/** 任务描述 */
	private String desc;

	/**
	 * Getter method for property <tt>jobId</tt>.
	 * 
	 * @return property value of jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * Setter method for property <tt>jobId</tt>.
	 * 
	 * @param jobId value to be assigned to property jobId
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * Getter method for property <tt>jobName</tt>.
	 * 
	 * @return property value of jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * Setter method for property <tt>jobName</tt>.
	 * 
	 * @param jobName value to be assigned to property jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * Getter method for property <tt>jobGroup</tt>.
	 * 
	 * @return property value of jobGroup
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * Setter method for property <tt>jobGroup</tt>.
	 * 
	 * @param jobGroup value to be assigned to property jobGroup
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * Getter method for property <tt>jobStatus</tt>.
	 * 
	 * @return property value of jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * Setter method for property <tt>jobStatus</tt>.
	 * 
	 * @param jobStatus value to be assigned to property jobStatus
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * Getter method for property <tt>cronExpression</tt>.
	 * 
	 * @return property value of cronExpression
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * Setter method for property <tt>cronExpression</tt>.
	 * 
	 * @param cronExpression value to be assigned to property cronExpression
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * Getter method for property <tt>desc</tt>.
	 * 
	 * @return property value of desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Setter method for property <tt>desc</tt>.
	 * 
	 * @param desc value to be assigned to property desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
