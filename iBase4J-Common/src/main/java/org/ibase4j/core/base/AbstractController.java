/**
 * 
 */
package org.ibase4j.core.base;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 控制器基类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class AbstractController<T extends BaseProvider> extends BaseController {
	protected final Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	protected T provider;

	public abstract String getService();

	public Object query(ModelMap modelMap, Map<String, Object> param) {
		Parameter parameter = new Parameter(getService(), "query").setMap(param);
		Page<?> list = provider.execute(parameter).getPage();
		return setSuccessModelMap(modelMap, list);
	}

	public Object queryList(ModelMap modelMap, Map<String, Object> param) {
		Parameter parameter = new Parameter(getService(), "queryList").setMap(param);
		List<?> list = provider.execute(parameter).getList();
		return setSuccessModelMap(modelMap, list);
	}

	public Object get(ModelMap modelMap, BaseModel param) {
		Parameter parameter = new Parameter(getService(), "queryById").setId(param.getId());
		BaseModel result = provider.execute(parameter).getModel();
		return setSuccessModelMap(modelMap, result);
	}

	public Object update(ModelMap modelMap, BaseModel param) {
		Long userId = getCurrUser();
		if (param.getId() == null) {
			param.setCreateBy(userId);
		}
		param.setUpdateBy(userId);
		Parameter parameter = new Parameter(getService(), "update").setModel(param);
		provider.execute(parameter);
		return setSuccessModelMap(modelMap);
	}

	public Object delete(ModelMap modelMap, BaseModel param) {
		Parameter parameter = new Parameter(getService(), "delete").setId(param.getId());
		provider.execute(parameter);
		return setSuccessModelMap(modelMap);
	}
}
