package org.ibase4j.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.AbstractController;
import top.ibase4j.core.support.http.HttpCode;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.UploadUtil;

/**
 * 文件上传控制器
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:42
 */
@RestController
@Api(value = "文件上传接口", description = "文件上传接口")
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadController extends AbstractController {
    // 上传文件(支持批量)
    @RequestMapping("/temp/file")
    @ApiOperation(value = "上传文件")
    public Object uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadFile(request);
        if (fileNames.size() > 0) {
            modelMap.put("fileNames", fileNames);
            return setSuccessModelMap(modelMap);
        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

    // 上传文件(支持批量)
    @RequestMapping("/temp/image")
    @ApiOperation(value = "上传图片")
    public Object uploadImage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImage(request, false);
        if (fileNames.size() > 0) {
            modelMap.put("fileNames", fileNames);
            return setSuccessModelMap(modelMap);
        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

    // 上传文件(支持批量)
    @RequestMapping("/temp/imageData")
    @ApiOperation(value = "上传图片")
    public Object uploadImageData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImageData(request);
        if (fileNames.size() > 0) {
            modelMap.put("fileNames", fileNames);
            return setSuccessModelMap(modelMap);
        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

    // 上传文件(支持批量)
    @RequestMapping("/file")
    @ApiOperation(value = "上传文件")
    public Object uploadFile2Ftp(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadFile(request);
        if (fileNames.size() > 0) {
            List<String> resultList = InstanceUtil.newArrayList();
            for (int i = 0; i < fileNames.size(); i++) {
                String filePath = UploadUtil.getUploadDir(request) + fileNames.get(i);
                String file = UploadUtil.remove2FDFS(filePath).getRemotePath();
                resultList.add(file);
            }
            modelMap.put("fileNames", resultList);
            return setSuccessModelMap(modelMap);
        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

    // 上传文件(支持批量)
    @RequestMapping("/image")
    @ApiOperation(value = "上传图片")
    public Object uploadImage2Ftp(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImage(request, false);
        if (fileNames.size() > 0) {
            List<String> resultList = InstanceUtil.newArrayList();
            for (int i = 0; i < fileNames.size(); i++) {
                String filePath = UploadUtil.getUploadDir(request) + fileNames.get(i);
                String file = UploadUtil.remove2FDFS(filePath).getRemotePath();
                resultList.add(file);
            }
            modelMap.put("fileNames", resultList);
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
    public Object uploadImageData2Ftp(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImageData(request);
        if (fileNames.size() > 0) {
            List<String> resultList = InstanceUtil.newArrayList();
            for (int i = 0; i < fileNames.size(); i++) {
                String filePath = UploadUtil.getUploadDir(request) + fileNames.get(i);
                String file = UploadUtil.remove2FDFS(filePath).getRemotePath();
                resultList.add(file);
            }
            modelMap.put("fileNames", resultList);
            return setSuccessModelMap(modelMap);
        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }
}
