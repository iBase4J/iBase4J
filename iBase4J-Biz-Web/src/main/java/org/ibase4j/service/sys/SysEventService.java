package org.ibase4j.service.sys;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.core.util.DateUtil;
import org.ibase4j.core.util.ExceptionUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.sys.SysEvent;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.provider.sys.ISysEventProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

@Service
public class SysEventService extends BaseService<ISysEventProvider, SysEvent>
		implements org.ibase4j.core.support.SysEventService {
	@DubboReference
	public void setProvider(ISysEventProvider provider) {
		this.provider = provider;
	}

	@Autowired
	private SysMenuService sysMenuService;
	static UASparser uasParser = null;

	// 初始化uasParser对象
	static {
		try {
			uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	private ExecutorService executorService = Executors.newCachedThreadPool();

	public void saveEvent(final HttpServletRequest request, final HttpServletResponse response, final Exception ex,
			final Long startTime, final Long endTime) {
		String userAgent = null;
		try {
			UserAgentInfo userAgentInfo = uasParser.parse(request.getHeader("user-agent"));
			userAgent = userAgentInfo.getOsName() + " " + userAgentInfo.getType() + " " + userAgentInfo.getUaName();
		} catch (IOException e) {
			logger.error("", e);
		}
		String path = request.getServletPath();
		if (!path.contains("/read/") && !path.contains("/unauthorized") && !path.contains("/forbidden")) {
			final String referurl = request.getHeader("Referer");
			final SysEvent record = new SysEvent();
			Long uid = WebUtil.getCurrentUser();
			record.setMethod(request.getMethod());
			record.setRequestUri(request.getServletPath());
			record.setClientHost(WebUtil.getHost(request));
			record.setUserAgent(userAgent);
			if (path.contains("/upload/")) {
				record.setParameters("");
			} else {
				record.setParameters(JSON.toJSONString(request.getParameterMap()));
			}
			record.setStatus(response.getStatus());
			record.setCreateBy(uid);
			record.setUpdateBy(uid);
			final String msg = (String) request.getAttribute("msg");

			executorService.submit(new Runnable() {
				public void run() {
					try { // 保存操作
						Map<String, Object> params = InstanceUtil.newHashMap();
						params.put("pageSize", -1);
						Page<SysMenu> page = sysMenuService.query(params);
						page.getRecords().add(new SysMenu("/login", "登录系统"));
						page.getRecords().add(new SysMenu("/logout", "登出系统"));
						for (SysMenu sysMenu : page.getRecords()) {
							if (referurl.endsWith(sysMenu.getRequest())) {
								record.setTitle(sysMenu.getMenuName());
								break;
							}
						}
						if (StringUtils.isNotBlank(msg)) {
							record.setRemark(msg);
						} else {
							record.setRemark(ExceptionUtil.getStackTraceAsString(ex));
						}

						update(record);
						// 内存信息
						if (logger.isDebugEnabled()) {
							String message = "开始时间: {}; 结束时间: {}; 耗时: {}s; URI: {}; ";
							// 最大内存: {}M; 已分配内存: {}M; 已分配内存中的剩余空间: {}M; 最大可用内存:
							// {}M.
							// long total = Runtime.getRuntime().totalMemory() /
							// 1024 / 1024;
							// long max = Runtime.getRuntime().maxMemory() /
							// 1024 / 1024;
							// long free = Runtime.getRuntime().freeMemory() /
							// 1024 / 1024;
							// , max, total, free, max - total + free
							logger.debug(message, DateUtil.format(startTime, "HH:mm:ss.SSS"),
									DateUtil.format(endTime, "HH:mm:ss.SSS"), (endTime - startTime) / 1000.00,
									record.getRequestUri());
						}
					} catch (Exception e) {
						logger.error("Save event log cause error :", e);
					}
				}
			});
		} else if (path.contains("/unauthorized")) {
			logger.warn("用户[{}]没有登录", WebUtil.getHost(request) + "@" + userAgent);
		} else if (path.contains("/forbidden")) {
			logger.warn("用户[{}]没有权限", WebUtil.getCurrentUser() + "@" + WebUtil.getHost(request) + "@" + userAgent);
		}
	}
}
