package org.ibase4j.service;

import java.util.List;

import org.ibase4j.core.config.Resources;
import org.ibase4j.facade.sys.SysScheduleJobFacade;
import org.ibase4j.mybatis.sys.model.ScheduleJob;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:20
 */
@Service
public class SysScheduleJobService {
	@Reference
	private SysScheduleJobFacade sysScheduleJobFacade;

	public List<ScheduleJob> getAllJobDetail() {
		return sysScheduleJobFacade.getAllJobDetail();
	}

	public void execTask(Integer id) {
		Assert.notNull(id, Resources.getMessage("TASKID_IS_NULL"));
		sysScheduleJobFacade.execTask(id);
	}

}
