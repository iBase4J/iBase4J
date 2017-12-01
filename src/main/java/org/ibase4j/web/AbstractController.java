/**
 * 
 */
package org.ibase4j.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.baomidou.mybatisplus.plugins.Page;

import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.base.BaseModel;
import top.ibase4j.core.base.BaseService;

/**
 * 控制器基类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class AbstractController<T extends BaseModel> extends BaseController {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    protected BaseService<T> service;

    public Object query(ModelMap modelMap, Map<String, Object> param) {
        if (param.get("keyword") == null && param.get("search") != null) {
            param.put("keyword", param.get("search"));
        }
        Page<T> list = service.query(param);
        return setSuccessModelMap(modelMap, list);
    }

    public Object queryList(ModelMap modelMap, Map<String, Object> param) {
        List<T> list = service.queryList(param);
        return setSuccessModelMap(modelMap, list);
    }

    public Object get(ModelMap modelMap, T param) {
        T result = service.queryById(param.getId());
        return setSuccessModelMap(modelMap, result);
    }

    public Object update(ModelMap modelMap, T param) {
        Long userId = getCurrUser();
        if (param.getId() == null) {
            param.setCreateBy(userId);
            param.setCreateTime(new Date());
        }
        param.setUpdateBy(userId);
        param.setUpdateTime(new Date());
        service.update(param);
        return setSuccessModelMap(modelMap);
    }

    public Object delete(ModelMap modelMap, T param) {
        service.delete(param.getId());
        return setSuccessModelMap(modelMap);
    }
}
