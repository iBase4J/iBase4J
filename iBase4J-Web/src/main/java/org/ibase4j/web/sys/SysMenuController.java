package org.ibase4j.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.util.Request2ModelUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.service.sys.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

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
@RequestMapping(value = "menu", method = RequestMethod.POST)
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    // 查询菜单
    @ApiOperation(value = "查询菜单")
    @RequiresPermissions("sys.menu.read")
    @RequestMapping(value = "/read/list")
    public Object get(HttpServletRequest request, ModelMap modelMap) {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        Page<?> list = sysMenuService.query(params);
        return setSuccessModelMap(modelMap, list);
    }

    // 详细信息
    @ApiOperation(value = "菜单详情")
    @RequiresPermissions("sys.menu.read")
    @RequestMapping(value = "/read/detail")
    public Object detail(ModelMap modelMap, @RequestParam(value = "id", required = false) String id) {
        SysMenu record = sysMenuService.queryById(id);
        return setSuccessModelMap(modelMap, record);
    }

    // 新增菜单
    @ApiOperation(value = "添加菜单")
    @RequiresPermissions("sys.menu.add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request, ModelMap modelMap) {
        SysMenu record = Request2ModelUtil.covert(SysMenu.class, request);
        sysMenuService.add(record);
        return setSuccessModelMap(modelMap);
    }

    // 修改菜单
    @ApiOperation(value = "修改菜单")
    @RequiresPermissions("sys.menu.updae")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest request, ModelMap modelMap) {
        SysMenu record = Request2ModelUtil.covert(SysMenu.class, request);
        sysMenuService.update(record);
        return setSuccessModelMap(modelMap);
    }

    // 删除菜单
    @ApiOperation(value = "删除菜单")
    @RequiresPermissions("sys.menu.delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(HttpServletRequest request, ModelMap modelMap,
        @RequestParam(value = "id", required = false) String id) {
        sysMenuService.delete(id);
        return setSuccessModelMap(modelMap);
    }

    // 获取所有权限
    @ApiOperation(value = "获取所有权限")
    @RequiresPermissions("sys.menu.read")
    @RequestMapping(value = "/read/permission")
    public Object getPermissions(HttpServletRequest request, ModelMap modelMap) {
        List<Map<String, String>> permissions = sysMenuService.getPermissions();
        return setSuccessModelMap(modelMap, permissions);
    }
}
