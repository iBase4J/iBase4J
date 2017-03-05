/**
 * 
 */
package org.ibase4j.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.support.Assert;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.core.util.UploadUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.SysUser;
import org.ibase4j.provider.ISysProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理控制器
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:12:12
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping(value = "/user")
public class SysUserController extends AbstractController<ISysProvider> {
	public String getService() {
		return "sysUserService";
	}

	@PostMapping
	@ApiOperation(value = "修改用户信息")
	@RequiresPermissions("sys.base.user.update")
	public Object update(ModelMap modelMap, @RequestBody SysUser param) {
		Assert.isNotBlank(param.getAccount(), "ACCOUNT");
		Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
		if (param.getId() != null) {
			Parameter parameter = new Parameter(getService(), "queryById").setId(param.getId());
			SysUser user = (SysUser) provider.execute(parameter).getModel();
			Assert.notNull(user, "USER", param.getId());
			if (StringUtils.isNotBlank(param.getPassword())) {
				if (!param.getPassword().equals(user.getPassword())) {
					param.setPassword(SecurityUtil.encryptPassword(param.getPassword()));
				}
			}
		}
		return super.update(modelMap, param);
	}

	// 查询用户
	@ApiOperation(value = "查询用户")
	@RequiresPermissions("sys.base.user.read")
	@PutMapping(value = "/read/list")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	// 用户详细信息
	@ApiOperation(value = "用户详细信息")
	@RequiresPermissions("sys.base.user.read")
	@PutMapping(value = "/read/detail")
	public Object get(ModelMap modelMap, @RequestBody SysUser param) {
		return super.get(modelMap, param);
	}

	// 用户详细信息
	@ApiOperation(value = "删除用户")
	@RequiresPermissions("sys.base.user.delete")
	@DeleteMapping
	public Object delete(ModelMap modelMap, @RequestBody SysUser param) {
		return super.delete(modelMap, param);
	}

	// 当前用户
	@ApiOperation(value = "当前用户信息")
	@GetMapping(value = "/read/promission")
	public Object promission(ModelMap modelMap) {
		Long id = getCurrUser();
		Parameter parameter = new Parameter(getService(), "queryById").setId(id);
		SysUser sysUser = (SysUser) provider.execute(parameter).getModel();
		modelMap.put("user", sysUser);
		parameter = new Parameter("sysAuthorizeService", "queryAuthorizeByUserId").setId(id);
		List<?> menus = provider.execute(parameter).getList();
		modelMap.put("menus", menus);
		return setSuccessModelMap(modelMap);
	}

	// 当前用户
	@ApiOperation(value = "当前用户信息")
	@GetMapping(value = "/read/current")
	public Object current(ModelMap modelMap) {
		SysUser param = new SysUser();
		param.setId(getCurrUser());
		return super.get(modelMap, param);
	}

	@ApiOperation(value = "修改个人信息")
	@PostMapping(value = "/update/person")
	public Object updatePerson(ModelMap modelMap, @RequestBody SysUser param) {
		param.setId(WebUtil.getCurrentUser());
		param.setPassword(null);
		Assert.isNotBlank(param.getAccount(), "ACCOUNT");
		Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
		return super.update(modelMap, param);
	}

	@ApiOperation(value = "修改用户头像")
	@PostMapping(value = "/update/avatar")
	public Object updateAvatar(HttpServletRequest request, ModelMap modelMap) {
		List<String> fileNames = UploadUtil.uploadImage(request, false);
		if (fileNames.size() > 0) {
			SysUser param = new SysUser();
			param.setId(WebUtil.getCurrentUser());
			String filePath = UploadUtil.getUploadDir(request) + fileNames.get(0);
			String avatar = UploadUtil.remove2DFS("sysUser", "U" + param.getId(), filePath).getRemotePath();
			param.setAvatar(avatar);
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
	public Object updatePassword(ModelMap modelMap, @RequestBody SysUser param) {
		Assert.notNull(param.getId(), "USER_ID");
		Assert.isNotBlank(param.getOldPassword(), "OLDPASSWORD");
		Assert.isNotBlank(param.getPassword(), "PASSWORD");
		String encryptPassword = SecurityUtil.encryptPassword(param.getOldPassword());
		Parameter parameter = new Parameter(getService(), "queryById").setId(param.getId());
		SysUser sysUser = (SysUser) provider.execute(parameter).getModel();
		Assert.notNull(sysUser, "USER", param.getId());
		Long userId = WebUtil.getCurrentUser();
		if (!param.getId().equals(userId)) {
			SysUser current = new SysUser();
			current.setId(userId);
			parameter = new Parameter(getService(), "queryById").setId(current.getId());
			SysUser user = (SysUser) provider.execute(parameter).getModel();
			if (user.getUserType() == 1) {
				throw new UnauthorizedException("您没有权限修改用户密码.");
			}
		} else {
			if (!sysUser.getPassword().equals(encryptPassword)) {
				throw new UnauthorizedException("原密码错误.");
			}
		}
		param.setPassword(encryptPassword);
		param.setUpdateBy(WebUtil.getCurrentUser());
		return super.update(modelMap, param);
	}
}
