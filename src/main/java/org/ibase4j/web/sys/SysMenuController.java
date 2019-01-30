package org.ibase4j.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.service.sys.SysMenuService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.exception.BusinessException;
import top.ibase4j.core.util.WebUtil;

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
    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "/read/page")
    @RequiresPermissions("sys.base.menu.read")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "/read/list")
    @RequiresPermissions("sys.base.menu.read")
    public Object queryList(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.queryList(param);
    }

    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "/read/tree")
    @RequiresPermissions("sys.base.menu.read")
    public Object getTree(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        List<?> list = service.queryTreeList(param);
        return setSuccessModelMap(list);
    }

    @ApiOperation(value = "菜单详情")
    @GetMapping(value = "/read/detail")
    @RequiresPermissions("sys.base.menu.read")
    public Object get(SysMenu param) {
        return super.get(param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改菜单")
    @RequiresPermissions("sys.base.menu.update")
    public Object update(SysMenu param) {
        if (param.getId() != null) {
            SysMenu result = service.queryById(param.getId());
            if (result.getMenuType()) {
                throw new BusinessException("不允许修改系统内置菜单");
            }
        }
        if (param.getIsShow() == null) {
            param.setIsShow("0");
        }
        return super.update(param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除菜单")
    @RequiresPermissions("sys.base.menu.delete")
    public Object delete(SysMenu param) {
        return super.delete(param);
    }

    @ApiOperation(value = "获取所有权限")
    @RequiresPermissions("sys.base.menu.read")
    @RequestMapping(value = "/read/permission")
    public Object getPermissions() {
        List<?> list = service.getPermissions();
        return setSuccessModelMap(list);
    }
}
