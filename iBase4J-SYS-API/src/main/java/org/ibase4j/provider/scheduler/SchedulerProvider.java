/**
 * 
 */
package org.ibase4j.provider.scheduler;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.scheduler.TaskFireLog;
import org.ibase4j.model.scheduler.TaskScheduled;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:06:49
 */
public interface SchedulerProvider {

    /** 获取所有任务 */
    public List<TaskScheduled> getAllTaskDetail();

    /** 执行任务 */
    public void execTask(String taskGroup, String taskName);

    /** 启停 */
    public void openCloseTask(String taskGroup, String taskName, String status);

    /** 删除作业 */
    public void delTask(String taskGroup, String taskName);

    public TaskFireLog updateLog(TaskFireLog record);

    public TaskFireLog getFireLogById(Long id);

    public Page<TaskFireLog> queryLog(Map<String, Object> params);

    /** 新增执行计划 */
    public void updateTask(TaskScheduled taskScheduled);

    /** 修改执行计划 */
    public void updateTaskCron(TaskScheduled scheduleJob);
}
