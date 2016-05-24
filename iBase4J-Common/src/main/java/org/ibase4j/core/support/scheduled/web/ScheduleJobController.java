/**
 * 
 */
package org.ibase4j.core.support.scheduled.web;

import java.util.List;

import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.support.scheduled.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:20:10
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleJobController extends BaseController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	@ResponseBody
	@RequestMapping("/read/jobs")
	public ModelMap list(ModelMap modelMap) {
		List<ScheduleJob> jobs = scheduleJobService.getAllJobDetail();
		return setSuccessModelMap(modelMap, jobs);
	}

	@ResponseBody
	@RequestMapping("/run/jobs")
	public ModelMap exec(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id) {
		scheduleJobService.execTask(id);
		return setSuccessModelMap(modelMap);
	}
}
