package org.ibase4j.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.util.ExceptionUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
			String method = parameter.getMethod();
			Object[] param = parameter.getParam();
			Object result = InstanceUtil.invokeMethod(service, method, param);
			Parameter response = new Parameter(result);
			logger.info("{} response：{}", no, JSON.toJSONString(response));
			return response;
		} catch (Exception e) {
			String msg = ExceptionUtil.getStackTraceAsString(e);
			logger.error(no + " " + Constants.Exception_Head + msg, e);
			throw e;
		}
	}
}
