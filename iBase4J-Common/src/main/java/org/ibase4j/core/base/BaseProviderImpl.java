package org.ibase4j.core.base;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.util.ExceptionUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.fastjson.JSON;
import com.esotericsoftware.reflectasm.MethodAccess;

public abstract class BaseProviderImpl implements ApplicationContextAware, BaseProvider {
	protected static Logger logger = LogManager.getLogger();
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public Parameter execute(Parameter parameter) {
		logger.info("请求：{}", JSON.toJSONString(parameter));
		Object service = applicationContext.getBean(parameter.getService());
		try {
			Long id = parameter.getId();
			BaseModel model = parameter.getModel();
			List<?> list = parameter.getList();
			Map<?, ?> map = parameter.getMap();
			Object result = null;
			MethodAccess methodAccess = MethodAccess.get(service.getClass());
			if (id != null) {
				result = methodAccess.invoke(service, parameter.getMethod(), parameter.getId());
			} else if (model != null) {
				result = methodAccess.invoke(service, parameter.getMethod(), parameter.getModel());
			} else if (list != null) {
				result = methodAccess.invoke(service, parameter.getMethod(), parameter.getList());
			} else if (map != null) {
				result = methodAccess.invoke(service, parameter.getMethod(), parameter.getMap());
			} else {
				result = methodAccess.invoke(service, parameter.getMethod());
			}
			if (result != null) {
				Parameter response = new Parameter(result);
				logger.info("响应：{}", JSON.toJSONString(response));
				return response;
			}
			logger.info("空响应");
			return null;
		} catch (Exception e) {
			throw new BusinessException(Constants.Exception_Head + ExceptionUtil.getStackTraceAsString(e), e);
		}
	}
}
