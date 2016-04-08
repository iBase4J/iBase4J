/**
 * 
 */
package org.shjr.ibase4j.web.sys;

import java.util.List;

import org.shjr.ibase4j.mybatis.sys.model.ScheduleJob;
import org.shjr.ibase4j.service.sys.SysScheduleJobService;
import org.shjr.ibase4j.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private SysScheduleJobService scheduleJobService;

	@ResponseBody
	@RequestMapping("/read/jobs")
	public ModelMap list(ModelMap modelMap) {
		List<ScheduleJob> jobs = scheduleJobService.getAllJobDetail();
		return setSuccessModelMap(modelMap, jobs);
	}
}
