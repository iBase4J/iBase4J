package org.ibase4j.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.service.sys.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:54
 */
@RestController
@Api(value = "菜单管理", description = "菜单管理")
@RequestMapping(value = "menu")
public class SysMenuController extends BaseController {
	@Autowired
	private SysMenuService sysMenuService;

	@ApiOperation(value = "查询菜单")
	@RequiresPermissions("sys.base.menu.read")
	@RequestMapping(value = "/read/page", method = RequestMethod.PUT)
	public Object getPage(ModelMap modelMap, @RequestBody Map<String, Object> sysMenu) {
		Page<?> list = sysMenuService.queryBeanPage(sysMenu);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "查询菜单")
	@RequiresPermissions("sys.base.menu.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object get(ModelMap modelMap, @RequestBody Map<String, Object> sysMenu) {
		List<?> list = sysMenuService.queryBean(sysMenu);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "菜单详情")
	@RequiresPermissions("sys.base.menu.read")
	@RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
	public Object detail(ModelMap modelMap, @RequestBody SysMenu param) {
		SysMenu record = sysMenuService.queryById(param.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "修改菜单")
	@RequiresPermissions("sys.base.menu.updae")
	@RequestMapping(method = RequestMethod.POST)
	public Object update(ModelMap modelMap, @RequestBody SysMenu record) {
		sysMenuService.update(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除菜单")
	@RequiresPermissions("sys.base.menu.delete")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete(ModelMap modelMap, @RequestBody SysMenu record) {
		sysMenuService.delete(record.getId());
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "获取所有权限")
	@RequiresPermissions("sys.base.menu.read")
	@RequestMapping(value = "/read/permission")
	public Object getPermissions(HttpServletRequest request, ModelMap modelMap) {
		List<Map<String, String>> permissions = sysMenuService.getPermissions();
		return setSuccessModelMap(modelMap, permissions);
	}
}
