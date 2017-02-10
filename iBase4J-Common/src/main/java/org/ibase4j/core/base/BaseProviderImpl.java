package org.ibase4j.core.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.fastjson.JSON;

public class BaseProviderImpl implements ApplicationContextAware, BaseProvider {
	protected static Logger logger = LogManager.getLogger();
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public Parameter exec(Parameter parameter) {
		logger.info("请求：{}", JSON.toJSONString(parameter));
		Object service = applicationContext.getBean(parameter.getService());
		try {
			Long id = parameter.getId();
			BaseModel model = parameter.getModel();
			List<?> list = parameter.getList();
			Map<?, ?> map = parameter.getMap();
			Object result = null;
			if (id != null) {
				Method method = service.getClass().getMethod(parameter.getMethod(), Long.class);
				result = method.invoke(service, parameter.getId());
			} else if (model != null) {
				Method method = service.getClass().getMethod(parameter.getMethod(), model.getClass());
				result = method.invoke(service, parameter.getModel());
			} else if (list != null) {
				Method method = service.getClass().getMethod(parameter.getMethod(), List.class);
				result = method.invoke(service, parameter.getList());
			} else if (map != null) {
				Method method = service.getClass().getMethod(parameter.getMethod(), Map.class);
				result = method.invoke(service, parameter.getMap());
			} else {
				Method method = service.getClass().getMethod(parameter.getMethod());
				result = method.invoke(service);
			}
			if (result != null) {
				Parameter response = new Parameter(result);
				logger.info("响应：{}", JSON.toJSONString(response));
				return response;
			}
			logger.info("空响应");
			return null;
		} catch (NoSuchMethodException e) {
			throw new BusinessException("没有该方法", e);
		} catch (SecurityException e) {
			throw new BusinessException("没有该方法", e);
		} catch (IllegalAccessException e) {
			throw new BusinessException("参数错误", e);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("参数错误", e);
		} catch (InvocationTargetException e) {
			throw new BusinessException("执行方法异常", e);
		}
	}
}
