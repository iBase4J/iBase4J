/**
 * 
 */
package org.ibase4j.core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.ibase4j.core.Constants;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.support.exception.BaseException;
import org.ibase4j.core.support.exception.IllegalParameterException;
import org.ibase4j.core.util.WebUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 控制器基类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseController {
	protected final Logger logger = LogManager.getLogger(this.getClass());

	/** 获取当前用户Id */
	protected Integer getCurrUser() {
		return WebUtil.getCurrentUser();
	}

	/** 设置成功响应代码 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
		return setSuccessModelMap(modelMap, null);
	}

	/** 设置成功响应代码 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
		return setModelMap(modelMap, HttpCode.OK, data);
	}

	/** 设置响应代码 */
	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
		return setModelMap(modelMap, code, null);
	}

	/** 设置响应代码 */
	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
		modelMap.remove("void");
		if (data != null) {
			modelMap.put("data", data);
		}
		modelMap.put("httpCode", code.value());
		modelMap.put("msg", code.msg());
		modelMap.put("timestamp", System.currentTimeMillis());
		return ResponseEntity.ok(modelMap);
	}

	/** 异常处理 */
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {
		logger.error(Constants.Exception_Head, ex);
		ModelMap modelMap = new ModelMap();
		if (ex instanceof BaseException) {
			((BaseException) ex).handler(modelMap);
		} else if (ex instanceof IllegalArgumentException) {
			new IllegalParameterException(ex.getMessage()).handler(modelMap);
		} else if (ex instanceof UnauthorizedException) {
			setModelMap(modelMap, HttpCode.FORBIDDEN);
		} else {
			setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
		}
		request.setAttribute("msg", modelMap.get("msg"));
		response.setContentType("application/json;charset=UTF-8");
		byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
		response.getOutputStream().write(bytes);
	}
}
