package org.ibase4j.service.sys;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.SysEventService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.core.util.ExceptionUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.generator.SysEvent;
import org.ibase4j.model.generator.SysPermission;
import org.ibase4j.provider.sys.SysEventProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

@Service
public class SysEventServiceImpl extends BaseService<SysEventProvider, SysEvent> implements SysEventService {
	@DubboReference
	public void setProvider(SysEventProvider provider) {
		this.provider = provider;
	}

	@Autowired
	private SysPermissionService sysPermissionService;

	public void saveEvent(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		try {
			final SysEvent record = new SysEvent();
			record.setMethod(request.getMethod());
			record.setRequestUri(request.getServletPath());
			record.setClientHost(WebUtil.getHost(request));
			record.setUserAgent(request.getHeader("user-agent"));
			record.setParammeters(JSON.toJSONString(request.getParameterMap()));
			record.setStatus(response.getStatus());
			record.setException(ExceptionUtil.getStackTraceAsString(ex));
			record.setCreateBy(WebUtil.getCurrentUser());

			ExecutorService executorService = Executors.newFixedThreadPool(1);
			executorService.submit(new Runnable() {
				public void run() {
					Map<String, Object> params = InstanceUtil.newHashMap();
					params.put("permission_url", record.getRequestUri());
					PageInfo<SysPermission> pageInfo = sysPermissionService.query(params);
					if (pageInfo.getSize() > 0) {
						record.setTitle(pageInfo.getList().get(0).getPermissionName());
					}
					add(record);
				}
			});
			executorService.shutdown();
		} catch (Exception e) {
			logger.error("Save event log cause error :", e);
		}
	}
}
