package org.ibase4j.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibase4j.core.support.SysEventService;
import org.ibase4j.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;

/**
 * 日志拦截器
 * 
 * @author ShenHuaJie
 * @version 2016年6月14日 下午6:18:46
 */
public class EventInterceptor extends BaseInterceptor {
	@Autowired
	private SysEventService sysEventService;

	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (logger.isDebugEnabled()) {
			long beginTime = System.currentTimeMillis();// 1、开始时间
			startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
			logger.debug("开始计时: {}; URI: {}.", DateUtil.format(beginTime, "hh:mm:ss.SSS"), request.getServletPath());
		}
		return super.preHandle(request, response, handler);
	}

	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		// 保存日志
		sysEventService.saveEvent(request, response, ex);
		// 打印JVM信息
		if (logger.isDebugEnabled()) {
			long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
			long endTime = System.currentTimeMillis(); // 2、结束时间
			logger.debug("计时结束: {}; URI: {}; 耗时: {}s; 最大内存: {}m; 已分配内存: {}m; 已分配内存中的剩余空间: {}m; 最大可用内存: {}m.",
					DateUtil.format(endTime, "hh:mm:ss.SSS"), request.getServletPath(), (endTime - beginTime) / 1000.00,
					Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024,
					Runtime.getRuntime().freeMemory() / 1024 / 1024, (Runtime.getRuntime().maxMemory()
							- Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
		}
		super.afterCompletion(request, response, handler, ex);
	}
}
