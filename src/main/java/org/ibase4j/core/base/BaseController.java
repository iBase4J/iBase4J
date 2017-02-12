/**
 * 
 */
package org.ibase4j.core.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 控制器基类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseController<T extends BaseModel> extends AbstractController {
	@Autowired
	protected BaseService<T> service;

	public Object query(ModelMap modelMap, Map<String, Object> params) {
		Page<?> list = service.query(params);
		return setSuccessModelMap(modelMap, list);
	}

	public Object queryList(ModelMap modelMap, Map<String, Object> params) {
		List<?> list = service.queryList(params);
		return setSuccessModelMap(modelMap, list);
	}

	public Object get(ModelMap modelMap, T param) {
		T result = service.queryById(param.getId());
		return setSuccessModelMap(modelMap, result);
	}

	public Object update(ModelMap modelMap, T param) {
		service.update(param);
		return setSuccessModelMap(modelMap);
	}

	public Object delete(ModelMap modelMap, T param) {
		service.delete(param.getId());
		return setSuccessModelMap(modelMap);
	}
}
