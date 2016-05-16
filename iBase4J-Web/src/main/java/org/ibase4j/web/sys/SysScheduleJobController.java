/**
 * 
 */
package org.ibase4j.web.sys;

import java.util.List;

import org.ibase4j.facade.sys.SysScheduleJobFacade;
import org.ibase4j.mybatis.sys.model.ScheduleJob;
import org.ibase4j.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:20:10
 */
@Controller
@RequestMapping("/schedule")
public class SysScheduleJobController extends BaseController {
	@Reference
	private SysScheduleJobFacade scheduleJobFacade;

	@ResponseBody
	@RequestMapping("/read/jobs")
	public ModelMap list() {
		List<ScheduleJob> jobs = scheduleJobFacade.getAllJobDetail();
		return setSuccessModelMap(modelMap, jobs);
	}

	@ResponseBody
	@RequestMapping("/run/jobs")
	public ModelMap exec(@RequestParam("id") Integer id) {
		scheduleJobFacade.execTask(id);
		return setSuccessModelMap(modelMap);
	}
}
