package org.ibase4j.core.base;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.support.dbcp.ChooseDataSource;
import org.ibase4j.core.support.dbcp.HandleDataSource;
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
			Long id = parameter.getId();
			BaseModel model = parameter.getModel();
			List<?> list = parameter.getList();
			Map<?, ?> map = parameter.getMap();
			String method = parameter.getMethod();
			Object[] param = parameter.getParam();
			Object result = null;
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
			if (param != null) {
				result = InstanceUtil.invokeMethod(service, method, param);
			} else if (id != null) {
				result = InstanceUtil.invokeMethod(service, method, id);
			} else if (model != null) {
				result = InstanceUtil.invokeMethod(service, method, model);
			} else if (list != null) {
				result = InstanceUtil.invokeMethod(service, method, list);
			} else if (map != null) {
				result = InstanceUtil.invokeMethod(service, method, map);
			} else {
				result = InstanceUtil.invokeMethod(service, method);
			}
			HandleDataSource.clear();
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
