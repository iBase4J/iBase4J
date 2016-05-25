/**
 * 
 */
package org.ibase4j.core.support.scheduled.web;

import java.util.List;

import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.support.scheduled.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:20:10
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleJobController extends BaseController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	@RequestMapping("/read/jobs")
	public Object list(ModelMap modelMap) {
		List<ScheduleJob> jobs = scheduleJobService.getAllJobDetail();
		return setSuccessModelMap(modelMap, jobs);
	}

	@RequestMapping("/run/jobs")
	public Object exec(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id) {
		scheduleJobService.execTask(id);
		return setSuccessModelMap(modelMap);
	}
}
