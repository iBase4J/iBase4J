/**
 * 
 */
package org.shjr.iplat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shjr.iplat.core.Constants;
import org.shjr.iplat.core.support.BusinessException;
import org.shjr.iplat.core.support.HttpCode;
import org.shjr.iplat.core.util.RedisUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author ShenHuaJie
 */
public class BaseController {
	protected Logger logger = LogManager.getLogger(this.getClass());

	/** 获取当前用户Id */
	protected String getUserId(HttpServletRequest request) {
		return RedisUtil.hget(Constants.CURRENT_USER, request.getSession().getId());
	}

	/** 设置成功响应代码 */
	protected ModelMap setSuccessModelMap(ModelMap modelMap) {
		return setSuccessModelMap(modelMap, null);
	}

	/** 设置成功响应代码 */
	protected ModelMap setSuccessModelMap(ModelMap modelMap, Object data) {
		return setModelMap(modelMap, HttpCode.HTTP_CODE_200, data);
	}

	/** 设置相应代码 */
	protected ModelMap setModelMap(ModelMap modelMap, Integer code) {
		return setModelMap(modelMap, code, null);
	}

	/** 设置相应代码 */
	protected ModelMap setModelMap(ModelMap modelMap, Integer code, Object data) {
		if (data != null) {
			modelMap.put("data", data);
		}
		modelMap.put("httpCode", code);
		modelMap.put("msg", HttpCode.MSG.get(code));
		return modelMap;
	}

	/** 异常处理 */
	@ExceptionHandler(RuntimeException.class)
	public void exceptionHandler(HttpServletResponse response, Exception ex) throws Exception {
		logger.error(Constants.Exception_Head, ex);
		ModelMap modelMap = new ModelMap();
		if (ex instanceof IllegalArgumentException) {
			if (StringUtils.isNotBlank(ex.getMessage())) {
				modelMap.put("httpCode", HttpCode.HTTP_CODE_400);
				modelMap.put("msg", ex.getMessage());
			} else {
				setModelMap(modelMap, HttpCode.HTTP_CODE_400);
			}
		} else if (ex instanceof BusinessException) {
			if (StringUtils.isNotBlank(ex.getMessage())) {
				modelMap.put("httpCode", HttpCode.HTTP_CODE_409);
				modelMap.put("msg", ex.getMessage());
			} else {
				setModelMap(modelMap, HttpCode.HTTP_CODE_409);
			}
		} else {
			setModelMap(modelMap, HttpCode.HTTP_CODE_500);
		}
		response.setContentType("application/json;charset=UTF-8");
		byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
		response.getOutputStream().write(bytes);
	}
}
