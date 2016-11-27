/**
 * 
 */
package org.ibase4j.core.provider.scheduler;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.scheduler.TaskFireLog;
import org.ibase4j.model.scheduler.TaskGroup;
import org.ibase4j.model.scheduler.TaskScheduler;
import org.ibase4j.model.scheduler.ext.TaskScheduled;
import org.ibase4j.model.scheduler.ext.TaskSchedulerBean;

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
    public boolean execTask(String taskGroup, String taskName);

    /** 启停 */
    public boolean openCloseTask(String taskGroup, String taskName, String status);
    
    /** 删除作业 */
    public boolean delTask(String taskGroup, String taskName);

    public TaskGroup getGroupById(String id);

    public TaskGroup updateGroup(TaskGroup record);

    public Page<TaskGroup> queryGroup(Map<String, Object> params);

    public TaskScheduler getSchedulerById(String id);

    public TaskScheduler updateScheduler(TaskScheduler record);

    public TaskFireLog updateLog(TaskFireLog record);

    public Page<TaskSchedulerBean> queryScheduler(Map<String, Object> params);

    public TaskFireLog getFireLogById(String id);

    public Page<TaskFireLog> queryLog(Map<String, Object> params);
}
