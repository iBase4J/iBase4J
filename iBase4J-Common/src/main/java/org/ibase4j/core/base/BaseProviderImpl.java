package org.ibase4j.core.base;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.util.ExceptionUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import com.alibaba.fastjson.JSON;

public abstract class BaseProviderImpl implements ApplicationContextAware, BaseProvider {
    protected static Logger logger = LogManager.getLogger();
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Parameter execute(Parameter parameter) {
        String no = parameter.getNo();
        logger.info("{} request：{}", no, JSON.toJSONString(parameter));
        Object service = applicationContext.getBean(parameter.getService());
        try {
            Long id = parameter.getId();
            BaseModel model = parameter.getModel();
            List<?> list = parameter.getList();
            Map<?, ?> map = parameter.getMap();
            Object result = null;
            if (id != null) {
                Method method = ReflectionUtils.findMethod(service.getClass(), parameter.getMethod(),
                    parameter.getId().getClass());
                result = ReflectionUtils.invokeMethod(method, service, parameter.getId());
            } else if (model != null) {
                Method method = ReflectionUtils.findMethod(service.getClass(), parameter.getMethod(),
                    parameter.getModel().getClass());
                result = ReflectionUtils.invokeMethod(method, service, parameter.getModel());
            } else if (list != null) {
                Method method = ReflectionUtils.findMethod(service.getClass(), parameter.getMethod(),
                    parameter.getList().getClass());
                result = ReflectionUtils.invokeMethod(method, service, parameter.getList());
            } else if (map != null) {
                Method method = ReflectionUtils.findMethod(service.getClass(), parameter.getMethod(),
                    parameter.getMap().getClass());
                result = ReflectionUtils.invokeMethod(method, service, parameter.getMap());
            } else {
                Method method = ReflectionUtils.findMethod(service.getClass(), parameter.getMethod());
                result = ReflectionUtils.invokeMethod(method, service);
            }
            if (result != null) {
                Parameter response = new Parameter(result);
                logger.info("{} response：{}", no, JSON.toJSONString(response));
                return response;
            }
            logger.info("{} response empty.", no);
            return null;
        } catch (Exception e) {
            String msg = ExceptionUtil.getStackTraceAsString(e);
            logger.error(no + " " + Constants.Exception_Head + msg, e);
            throw e;
        }
    }
}
