package org.ibase4j.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysMenu;
import org.ibase4j.provider.ISysProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.provider.BaseController;
import top.ibase4j.core.base.provider.Parameter;

/**
 * 菜单管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:54
 */
@RestController
@Api(value = "菜单管理", description = "菜单管理")
@RequestMapping(value = "menu")
public class SysMenuController extends BaseController<ISysProvider> {
    public String getService() {
        return "sysMenuService";
    }

    @ApiOperation(value = "查询菜单")
    @PutMapping(value = "/read/page")
    @RequiresPermissions("sys.base.menu.read")
    public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "查询菜单")
    @PutMapping(value = "/read/list")
    @RequiresPermissions("sys.base.menu.read")
    public Object get(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        return super.queryList(modelMap, param);
    }

    @ApiOperation(value = "查询菜单")
    @PutMapping(value = "/read/tree")
    @RequiresPermissions("sys.base.menu.read")
    public Object getTree(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        Parameter parameter = new Parameter(getService(), "queryTreeList", param);
        List<?> list = provider.execute(parameter).getResultList();
        return setSuccessModelMap(modelMap, list);
    }

    @ApiOperation(value = "菜单详情")
    @PutMapping(value = "/read/detail")
    @RequiresPermissions("sys.base.menu.read")
    public Object get(ModelMap modelMap, @RequestBody SysMenu param) {
        return super.get(modelMap, param);
    }

    @PostMapping
    @ApiOperation(value = "修改菜单")
    @RequiresPermissions("sys.base.menu.update")
    public Object update(ModelMap modelMap, @RequestBody SysMenu param) {
        if (param.getIsShow() == null) {
            param.setIsShow("0");
        }
        return super.update(modelMap, param);
    }

    @DeleteMapping
    @ApiOperation(value = "删除菜单")
    @RequiresPermissions("sys.base.menu.delete")
    public Object delete(ModelMap modelMap, @RequestBody SysMenu param) {
        return super.delete(modelMap, param);
    }

    @ApiOperation(value = "获取所有权限")
    @RequiresPermissions("sys.base.menu.read")
    @RequestMapping(value = "/read/permission")
    public Object getPermissions(ModelMap modelMap) {
        Parameter parameter = new Parameter(getService(), "getPermissions", new SysMenu());
        List<?> list = provider.execute(parameter).getResultList();
        return setSuccessModelMap(modelMap, list);
    }
}
