/**
 *
 */
package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import top.ibase4j.core.support.scheduler.TaskScheduled;
import top.ibase4j.model.TaskFireLog;

/**
 * 定时任务管理
 *
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:06:49
 */
public interface SchedulerService {

    /** 获取所有任务 */
    List<TaskScheduled> getAllTaskDetail();

    /** 执行任务 */
    void execTask(TaskScheduled taskScheduler);

    /** 启停 */
    void openTask(TaskScheduled taskScheduler);

    /** 启停 */
    void closeTask(TaskScheduled taskScheduler);

    /** 删除作业 */
    void delTask(TaskScheduled taskScheduler);

    TaskFireLog updateLog(TaskFireLog record);

    TaskFireLog getFireLogById(Long id);

    Object queryLog(Map<String, Object> params);

    /** 修改执行计划 */
    void updateTask(TaskScheduled taskScheduled);
}
