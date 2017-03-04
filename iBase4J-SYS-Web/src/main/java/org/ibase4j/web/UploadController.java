package org.ibase4j.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.support.decoder.BASE64Decoder;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.UploadUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件上传控制器
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:42
 */
@RestController
@Api(value = "文件上传接口", description = "文件上传接口")
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadController extends BaseController {

	public String getService() {
		return null;
	}

	// 上传文件(支持批量)
	@RequestMapping("/image")
	@ApiOperation(value = "上传图片")
	public Object uploadImage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		List<String> fileNames = UploadUtil.uploadImage(request, true);
		if (fileNames.size() > 0) {
			modelMap.put("imgName", fileNames);
			return setSuccessModelMap(modelMap);
		} else {
			setModelMap(modelMap, HttpCode.BAD_REQUEST);
			modelMap.put("msg", "请选择要上传的文件！");
			return modelMap;
		}
	}

	// 上传文件(支持批量)
	@RequestMapping("/imageData")
	@ApiOperation(value = "上传图片")
	public Object uploadImageData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		List<String> fileNames = InstanceUtil.newArrayList();
		String[] fileDatas = request.getParameterValues("fileData");
		for (int i = 0; i < fileDatas.length; i++) {
			String fileStr = fileDatas[i];
			if (fileStr != null && !"".equals(fileStr)) {
				int index = fileStr.indexOf("base64");
				if (index > 0) {
					try {
						String fileName = UUID.randomUUID().toString();
						String preStr = fileStr.substring(0, index + 7);
						String prefix = preStr.substring(preStr.indexOf("/") + 1, preStr.indexOf(";")).toLowerCase();
						fileStr = fileStr.substring(fileStr.indexOf(",") + 1);
						String pathDir = UploadUtil.getUploadDir(request);
						BASE64Decoder decoder = new BASE64Decoder();
						byte[] bb = decoder.decodeBuffer(fileStr);
						for (int j = 0; j < bb.length; ++j) {
							if (bb[j] < 0) {// 调整异常数据
								bb[j] += 256;
							}
						}
						File dir = new File(pathDir);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						String distPath = pathDir + fileName + "." + prefix;
						OutputStream out = new FileOutputStream(distPath);
						out.write(bb);
						out.flush();
						out.close();
						fileNames.add(fileName + "." + prefix);
					} catch (Exception e) {
						logger.error("上传文件异常：", e);
					}
				} else {
					setModelMap(modelMap, HttpCode.BAD_REQUEST);
					modelMap.put("msg", "请选择要上传的文件！");
					return modelMap;
				}
			}
		}
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
