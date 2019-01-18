package org.ibase4j.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysMenu;
import org.ibase4j.service.SysMenuService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.exception.BusinessException;

/**
 * 菜单管理
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:54
 */
@RestController
@Api(value = "菜单管理", description = "菜单管理")
@RequestMapping(value = "menu")
public class SysMenuController extends BaseController<SysMenu, SysMenuService> {
    @Override
    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "/read/page")
    @RequiresPermissions("sys.base.menu.read")
    public Object query(ModelMap modelMap,  Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "/read/list")
    @RequiresPermissions("sys.base.menu.read")
    public Object get(ModelMap modelMap,  Map<String, Object> param) {
        return super.queryList(modelMap, param);
    }

    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "/read/tree")
    @RequiresPermissions("sys.base.menu.read")
    public Object getTree(ModelMap modelMap,  Map<String, Object> param) {
        List<?> list = service.queryTreeList(param);
        return setSuccessModelMap(modelMap, list);
    }

    @ApiOperation(value = "菜单详情")
    @GetMapping(value = "/read/detail")
    @RequiresPermissions("sys.base.menu.read")
    public Object get(ModelMap modelMap,  SysMenu param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改菜单")
    @RequiresPermissions("sys.base.menu.update")
    public Object update(ModelMap modelMap,  SysMenu param) {
        if (param.getId() != null) {
            SysMenu result = service.queryById(param.getId());
            if ("1".equals(result.getMenuType())) {
                throw new BusinessException("不允许修改系统内置菜单");
            }
        }
        if (param.getIsShow() == null) {
            param.setIsShow("0");
        }
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除菜单")
    @RequiresPermissions("sys.base.menu.delete")
    public Object delete(ModelMap modelMap,  SysMenu param) {
        return super.delete(modelMap, param);
    }

    @ApiOperation(value = "获取所有权限")
    @RequiresPermissions("sys.base.menu.read")
    @RequestMapping(value = "/read/permission")
    public Object getPermissions(ModelMap modelMap) {
        List<?> list = service.getPermissions();
        return setSuccessModelMap(modelMap, list);
    }
}
