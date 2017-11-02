package org.ibase4j.web;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.ibase4j.core.Constants;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.base.BaseModel;
import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.exception.BaseException;
import org.ibase4j.core.exception.IllegalParameterException;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.provider.ISysProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;

public abstract class AppBaseController<T extends BaseProvider> extends BaseController {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    protected T provider;
    @Autowired
    protected ISysProvider sysProvider;

    public abstract String getService();

    protected Long getCurrUser(HttpServletRequest request) {
        Object id = (String)WebUtil.getCurrentUser(request);
        if (id == null) {
            return null;
        } else {
            return Long.parseLong(id.toString());
        }
    }

    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
        return setModelMap((String)modelMap.get("listKey"), modelMap, code, data);
    }

    protected ResponseEntity<ModelMap> setSuccessModelMap(String listKey, ModelMap modelMap, Object data) {
        return setModelMap(listKey, modelMap, HttpCode.OK, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(String listKey, ModelMap modelMap, HttpCode code, Object data) {
        Map<String, Object> map = InstanceUtil.newLinkedHashMap();
        map.putAll(modelMap);
        modelMap.clear();
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            if (!key.startsWith("org.springframework.validation.BindingResult") && !key.equals("void")
                && !key.equals("listKey")) {
                modelMap.put(key, map.get(key));
            }
        }
        if (data != null) {
            if (data instanceof Page) {
                Page<?> page = (Page<?>)data;
                Map<String, Object> dataMap = InstanceUtil.newHashMap();
                dataMap.put(listKey, page.getRecords());
                dataMap.put("current", page.getCurrent());
                dataMap.put("totalCount", page.getTotal());
                dataMap.put("pageCount", page.getPages());
                modelMap.put("rows", dataMap);
            } else if (data instanceof List<?>) {
                Map<String, Object> dataMap = InstanceUtil.newHashMap();
                dataMap.put(listKey, data);
                dataMap.put("totalCount", ((List<?>)data).size());
                modelMap.put("rows", dataMap);
            } else {
                modelMap.put("rows", data);
            }
        }
        modelMap.put("code", code.value());
        modelMap.put("msg", code.msg());
        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }

    public Object query(ModelMap modelMap, Map<String, Object> param) {
        Parameter parameter = new Parameter(getService(), "query").setParam(param);
        logger.info("{} execute query start...", parameter.getNo());
        Page<?> list = provider.execute(parameter).getResultPage();
        logger.info("{} execute query end.", parameter.getNo());
        return setSuccessModelMap(modelMap, list);
    }

    public Object queryList(ModelMap modelMap, Map<String, Object> param) {
        Parameter parameter = new Parameter(getService(), "queryList").setParam(param);
        logger.info("{} execute queryList start...", parameter.getNo());
        List<?> list = provider.execute(parameter).getResultList();
        logger.info("{} execute queryList end.", parameter.getNo());
        return setSuccessModelMap(modelMap, list);
    }

    public Object get(ModelMap modelMap, BaseModel param) {
        Parameter parameter = new Parameter(getService(), "queryById").setParam(param.getId());
        logger.info("{} execute queryById start...", parameter.getNo());
        Object result = provider.execute(parameter).getResult();
        logger.info("{} execute queryById end.", parameter.getNo());
        return setSuccessModelMap(modelMap, result);
    }

    public Object update(HttpServletRequest request, ModelMap modelMap, BaseModel param) {
        Long userId = getCurrUser(request);
        if (param.getId() == null) {
            param.setCreateBy(userId);
            param.setCreateTime(new Date());
        }
        param.setUpdateBy(userId);
        param.setUpdateTime(new Date());
        Parameter parameter = new Parameter(getService(), "update").setParam(param);
        logger.info("{} execute update start...", parameter.getNo());
        Object recourd = provider.execute(parameter).getResult();
        logger.info("{} execute update end.", parameter.getNo());
        Map<String, Object> result = InstanceUtil.newHashMap("bizeCode", 1);
        result.put("recordInfo", recourd);
        return setSuccessModelMap(modelMap, result);
    }

    public Object delete(ModelMap modelMap, BaseModel param) {
        Parameter parameter = new Parameter(getService(), "delete").setParam(param.getId());
        logger.info("{} execute delete start...", parameter.getNo());
        provider.execute(parameter);
        logger.info("{} execute delete end.", parameter.getNo());
        return setSuccessModelMap(modelMap, InstanceUtil.newHashMap("bizeCode", 1));
    }

    public String getSysParam(String key) {
        Parameter parameter = new Parameter("sysParamService", "getValue").setParam(key);
        Object result = sysProvider.execute(parameter).getResult();
        if (result != null) {
            return (String)result;
        }
        return "";
    }

    /** 异常处理 */
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
        throws Exception {
        logger.error(Constants.Exception_Head, ex);
        ModelMap modelMap = new ModelMap();
        if (ex instanceof BaseException) {
            ((BaseException)ex).handler(modelMap);
        } else if (ex instanceof IllegalArgumentException) {
            new IllegalParameterException(ex.getMessage()).handler(modelMap);
        } else if (ex instanceof UnauthorizedException) {
            modelMap.put("code", HttpCode.FORBIDDEN.value().toString());
            modelMap.put("msg", StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.FORBIDDEN.msg()));
        } else {
            modelMap.put("code", HttpCode.INTERNAL_SERVER_ERROR.value().toString());
            String msg = StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.INTERNAL_SERVER_ERROR.msg());
            logger.debug(msg);
            modelMap.put("msg", msg.length() > 100 ? "系统走神了,请稍候再试." : msg);
        }
        if (modelMap.get("httpCode") != null) {
            modelMap.put("code", modelMap.get("httpCode"));
            modelMap.remove("httpCode");
        }
        response.setContentType("application/json;charset=UTF-8");
        modelMap.put("timestamp", System.currentTimeMillis());
        logger.info(JSON.toJSON(modelMap));
        byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
        response.getOutputStream().write(bytes);
    }
}
