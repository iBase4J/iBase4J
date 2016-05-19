package org.ibase4j.web.sys;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.DateUtil;
import org.ibase4j.core.util.ImageUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class UploadController extends BaseController {
	private final String uploadFileDir = "/WEB-INF/upload/";

	// 上传文件(支持批量)
	@ResponseBody
	@RequestMapping("/upload")
	public ModelMap upload() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		List<String> fileNames = InstanceUtil.newArrayList();
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multiRequest.getFileNames();
			String pathDir = request.getSession().getServletContext().getRealPath(uploadFileDir + DateUtil.getDate());
			File dirFile = new File(pathDir);
			if (!dirFile.isDirectory()) {
				dirFile.mkdir();
			}
			while (iterator.hasNext()) {
				String key = iterator.next();
				MultipartFile multipartFile = multiRequest.getFile(key);
				if (multipartFile != null) {
					String name = multipartFile.getOriginalFilename();
					if (name.indexOf(".") == -1 && "blob".equals(name)) {
						name = name + ".png";
					}
					String uuid = UUID.randomUUID().toString();
					String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
					String fileName = uuid + postFix;
					String filePath = pathDir + File.separator + fileName;
					File file = new File(filePath);
					file.setWritable(true, false);
					try {
						multipartFile.transferTo(file);
						fileNames.add(fileName);
					} catch (Exception e) {
						logger.error(name + "保存失败", e);
					}
					try { // 缩放
						BufferedImage bufferedImg = ImageIO.read(file);
						int orgwidth = bufferedImg.getWidth();// 原始宽度
						ImageUtil.scaleWidth(file, 100);
						if (orgwidth > 300) {
							ImageUtil.scaleWidth(file, 300);
						}
						if (orgwidth > 500) {
							ImageUtil.scaleWidth(file, 500);
						}
					} catch (Exception e) {
					}
				}
			}
		}
		if (fileNames.size() > 0) {
			modelMap.put("imgName", fileNames);
			return setSuccessModelMap();
		} else {
			setModelMap(HttpCode.BAD_REQUEST);
			modelMap.put("msg", "请选择要上传的文件！");
			return modelMap;
		}
	}
}
