package org.ibase4j.core.support.scheduled.web;

import java.util.List;

import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.scheduled.ScheduleJob;
import org.ibase4j.core.support.scheduled.service.ScheduleJobFacade;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:20
 */
@Service
public class ScheduleJobService {
	@Reference
	private ScheduleJobFacade sysScheduleJobFacade;

	public List<ScheduleJob> getAllJobDetail() {
		return sysScheduleJobFacade.getAllJobDetail();
	}

	public void execTask(Integer id) {
		Assert.notNull(id, Resources.getMessage("TASKID_IS_NULL"));
		sysScheduleJobFacade.execTask(id);
	}

}
