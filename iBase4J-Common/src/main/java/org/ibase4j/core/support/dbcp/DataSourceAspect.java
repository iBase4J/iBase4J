/**
 * 
 */
package org.ibase4j.core.support.dbcp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ibase4j.core.base.Parameter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ShenHuaJie
 * @version 2017年9月19日 上午11:44:20
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
    private final Logger logger = LogManager.getLogger();

    @Pointcut("this(org.ibase4j.core.base.BaseProviderImpl)")
    public void aspect() {
    }

    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点
     */
    @Before("aspect()")
    public void before(JoinPoint point) {
        Parameter parameter = (Parameter)point.getArgs()[0];
        String method = parameter.getMethod();
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
