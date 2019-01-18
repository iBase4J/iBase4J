/**
 *
 */
package org.ibase4j.web.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysUser;
import org.ibase4j.service.sys.SysAuthorizeService;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.support.Assert;
import top.ibase4j.core.support.http.HttpCode;
import top.ibase4j.core.util.SecurityUtil;
import top.ibase4j.core.util.UploadUtil;

/**
 * 用户管理控制器
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:12:12
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping(value = "/user")
public class SysUserController extends BaseController<SysUser, SysUserService> {
    @Resource
    private SysAuthorizeService sysAuthorizeService;

    @Override
    @PostMapping
    @ApiOperation(value = "修改用户信息")
    @RequiresPermissions("sys.base.user.update")
    public Object update(ModelMap modelMap, SysUser param) {
        Assert.isNotBlank(param.getAccount(), "ACCOUNT");
        Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
        if (param.getEnable() == null) {
            param.setEnable(0);
        }
        return super.update(modelMap, param);
    }

    // 查询用户
    @Override
    @ApiOperation(value = "查询用户")
    @RequiresPermissions("sys.base.user.read")
    @GetMapping(value = "/read/page")
    public Object query(ModelMap modelMap, Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    // 查询用户
    @Override
    @ApiOperation(value = "查询用户")
    @RequiresPermissions("sys.base.user.read")
    @GetMapping(value = "/read/list")
    public Object queryList(ModelMap modelMap, Map<String, Object> param) {
        return super.queryList(modelMap, param);
    }

    // 用户详细信息
    @ApiOperation(value = "用户详细信息")
    @RequiresPermissions("sys.base.user.read")
    @GetMapping(value = "/read/detail")
    public Object get(ModelMap modelMap, SysUser param) {
        SysUser result = service.queryById(param.getId());
        result.setPassword(null);
        return setSuccessModelMap(modelMap, result);
    }

    // 用户详细信息
    @Override
    @ApiOperation(value = "删除用户")
    @RequiresPermissions("sys.base.user.delete")
    @DeleteMapping
    public Object delete(ModelMap modelMap, SysUser param) {
        return super.delete(modelMap, param);
    }

    // 当前用户
    @ApiOperation(value = "当前用户信息")
    @GetMapping(value = "/read/promission")
    public Object promission(ModelMap modelMap) {
        Long id = getCurrUser().getId();
        SysUser sysUser = service.queryById(id);
        sysUser.setPassword(null);
        modelMap.put("user", sysUser);
        List<?> menus = sysAuthorizeService.queryAuthorizeByUserId(id);
        modelMap.put("menus", menus);
        return setSuccessModelMap(modelMap);
    }

    // 当前用户
    @ApiOperation(value = "当前用户信息")
    @GetMapping(value = "/read/current")
    public Object current(ModelMap modelMap) {
        SysUser result = service.queryById(getCurrUser().getId());
        result.setPassword(null);
        return setSuccessModelMap(modelMap, result);
    }

    @ApiOperation(value = "修改个人信息")
    @PostMapping(value = "/update/person")
    public Object updatePerson(ModelMap modelMap, SysUser param) {
        param.setId(getCurrUser().getId());
        param.setPassword(null);
        Assert.isNotBlank(param.getAccount(), "ACCOUNT");
        Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
        return super.update(modelMap, param);
    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping(value = "/upload/avatar")
    public Object updateAvatar(HttpServletRequest request, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImageData(request);
        if (fileNames.size() > 0) {
            SysUser param = new SysUser();
            param.setId(getCurrUser().getId());
            for (int i = 0; i < fileNames.size(); i++) {
                String filePath = UploadUtil.getUploadDir(request) + fileNames.get(i);
                String avatar = UploadUtil.remove2FDFS("sysUser", filePath).getRemotePath();
                param.setAvatar(avatar);
            }
            modelMap.put("data", param);
            return super.update(modelMap, param);
        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

    // 修改密码
    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/update/password")
    public Object updatePassword(ModelMap modelMap, SysUser param) {
        Assert.isNotBlank(param.getOldPassword(), "OLDPASSWORD");
        Assert.isNotBlank(param.getPassword(), "PASSWORD");
        String encryptPassword = SecurityUtil.encryptPassword(param.getOldPassword());
        SysUser sysUser = service.queryById(getCurrUser().getId());
        Assert.notNull(sysUser, "USER", param.getId());
        if (!sysUser.getPassword().equals(encryptPassword)) {
            throw new UnauthorizedException("原密码错误.");
        }
        sysUser.setPassword(SecurityUtil.encryptPassword(param.getPassword()));
        return super.update(modelMap, sysUser);
    }
}
