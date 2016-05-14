package org.ibase4j.facade.sys;

import java.util.List;

import org.ibase4j.mybatis.sys.model.ScheduleJob;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 */
public interface SysScheduleJobFacade {
	// 获取所有任务
	public List<ScheduleJob> getAllJobDetail();

	// 执行任务
	public boolean execTask(int id);
}
