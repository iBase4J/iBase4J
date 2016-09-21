package org.ibase4j.core.aspect;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 切换数据源(不同方法调用不同数据源)
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:17:52
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
	private final Logger logger = LogManager.getLogger();

	@Pointcut("execution(* org.ibase4j.provider..*.*(..))")
	public void aspect() {
	}

	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 */
	@Before("aspect()")
	public void before(JoinPoint point) {
		String className = point.getTarget().getClass().getName();
		String method = point.getSignature().getName();
		logger.info(className + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
		try {
			L: for (String key : ChooseDataSource.METHODTYPE.keySet()) {
				for (String type : ChooseDataSource.METHODTYPE.get(key)) {
					if (method.startsWith(type)) {
						logger.info(key);
						HandleDataSource.putDataSource(key);
						break L;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			HandleDataSource.putDataSource("write");
		}
	}

	@After("aspect()")
	public void after(JoinPoint point) {
		HandleDataSource.clear();
	}
}
