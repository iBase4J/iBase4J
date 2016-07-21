/**
 * 
 */
package org.ibase4j.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.scheduler.TaskScheduled;
import org.ibase4j.service.sys.SchedulerService;
import org.ibase4j.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:20:10
 */
@Controller
@RequestMapping("/schedule")
public class SysScheduleJobController extends BaseController {
    @Autowired
    private SchedulerService schedulerService;

    @ResponseBody
    @RequestMapping("/read/jobs")
    public ModelMap list(ModelMap modelMap) {
        List<TaskScheduled> jobs = schedulerService.getAllTaskDetail();
        return setSuccessModelMap(modelMap, jobs);
    }

    @ResponseBody
    @RequestMapping("/run/jobs")
    public Object exec(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
        @RequestParam(value = "taskName", required = false) String taskName) {
        schedulerService.execTask(taskGroup, taskName);
        return setSuccessModelMap(modelMap);
    }// 启动

    @RequestMapping("/open/task")
    @RequiresPermissions("task.scheduled.open")
    public Object open(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
        @RequestParam(value = "taskName", required = false) String taskName) {
        schedulerService.openCloseTask(taskGroup, taskName, "start");
        return setSuccessModelMap(modelMap);
    }

    // 暂停
    @RequestMapping("/close/task")
    @RequiresPermissions("task.scheduled.close")
    public Object close(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
        @RequestParam(value = "taskName", required = false) String taskName) {
        schedulerService.openCloseTask(taskGroup, taskName, "stop");
        return setSuccessModelMap(modelMap);
    }

    // 执行记录
    @RequestMapping("/read/log")
    @RequiresPermissions("task.log.read")
    public Object getFireLog(HttpServletRequest request, ModelMap modelMap) {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        PageInfo<?> list = schedulerService.queryLog(params);
        return setSuccessModelMap(modelMap, list);
    }
}
