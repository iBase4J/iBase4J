package org.ibase4j.core.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 * 上传辅助类 与Spring.multipartResolver冲突
 * 
 * @author ShenHuaJie
 */
public class UploadUtil {
	private UploadUtil() {
	}

	/** 上传文件缓存大小限制 */
	private static int fileSizeThreshold = 1024 * 1024 * 1;

	/** 获取所有文本域 */
	public static List<?> getFileItemList(HttpServletRequest request, File saveDir) throws FileUploadException {
		if (!saveDir.isDirectory()) {
			saveDir.mkdir();
		}
		List<?> fileItems = null;
		RequestContext requestContext = new ServletRequestContext(request);
		if (FileUpload.isMultipartContent(requestContext)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(saveDir);
			factory.setSizeThreshold(fileSizeThreshold);
			ServletFileUpload upload = new ServletFileUpload(factory);
			fileItems = upload.parseRequest(request);
		}
		return fileItems;
	}

	/** 获取文本域 */
	public static FileItem[] getFileItem(HttpServletRequest request, File saveDir, String... fieldName)
			throws FileUploadException {
		if (fieldName == null || saveDir == null) {
			return null;
		}
		List<?> fileItemList = getFileItemList(request, saveDir);
		FileItem fileItem = null;
		FileItem[] fileItems = new FileItem[fieldName.length];
		for (int i = 0; i < fieldName.length; i++) {
			for (Iterator<?> iterator = fileItemList.iterator(); iterator.hasNext();) {
				fileItem = (FileItem) iterator.next();
				// 根据名字获得文本域
				if (fieldName[i] != null && fieldName[i].equals(fileItem.getFieldName())) {
					fileItems[i] = fileItem;
					break;
				}
			}
		}
		return fileItems;
	}
}
