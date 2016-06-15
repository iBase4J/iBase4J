package org.ibase4j.web.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.UploadUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件上传控制器
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:42
 */
@RestController
public class UploadController extends BaseController {

	// 上传文件(支持批量)
	@RequestMapping("/upload/image")
	public Object uploadImage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		List<String> fileNames = UploadUtil.uploadImage(request);
		if (fileNames.size() > 0) {
			modelMap.put("imgName", fileNames);
			return setSuccessModelMap(modelMap);
		} else {
			setModelMap(modelMap, HttpCode.BAD_REQUEST);
			modelMap.put("msg", "请选择要上传的文件！");
			return modelMap;
		}
	}
}
