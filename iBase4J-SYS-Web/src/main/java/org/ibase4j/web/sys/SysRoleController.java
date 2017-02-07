package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.sys.SysRole;
import org.ibase4j.service.sys.SysRoleService;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:15:43
 */
@RestController
@Api(value = "角色管理", description = "角色管理")
@RequestMapping(value = "role")
public class SysRoleController extends BaseController {
	@Autowired
	private SysRoleService sysRoleService;

	@ApiOperation(value = "查询角色")
	@RequiresPermissions("sys.base.role.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object get(ModelMap modelMap, @RequestBody Map<String, Object> sysRole) {
		Page<?> list = sysRoleService.query(sysRole);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "角色详情")
	@RequiresPermissions("sys.base.role.read")
	@RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
	public Object detail(ModelMap modelMap, @RequestBody SysRole param) {
		SysRole record = sysRoleService.queryById(param.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "修改角色")
	@RequiresPermissions("sys.base.role.update")
	@RequestMapping(method = RequestMethod.POST)
	public Object update(ModelMap modelMap, @RequestBody SysRole record) {
		sysRoleService.update(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除角色")
	@RequiresPermissions("sys.base.role.delete")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete(ModelMap modelMap, @RequestBody SysRole record) {
		sysRoleService.delete(record.getId());
		return setSuccessModelMap(modelMap);
	}
}
