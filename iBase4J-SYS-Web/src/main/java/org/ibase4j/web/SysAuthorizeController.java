package org.ibase4j.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysRoleMenu;
import org.ibase4j.model.SysUserMenu;
import org.ibase4j.model.SysUserRole;
import org.ibase4j.provider.ISysProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.provider.BaseController;
import top.ibase4j.core.base.provider.Parameter;
import top.ibase4j.core.exception.IllegalParameterException;

/**
 * 权限管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:05
 */
@RestController
@Api(value = "权限管理", description = "权限管理")
public class SysAuthorizeController extends BaseController<ISysProvider> {

    public String getService() {
        return "sysAuthorizeService";
    }

    @ApiOperation(value = "获取用户菜单编号")
    @PutMapping(value = "user/read/menu")
    @RequiresPermissions("sys.permisson.userMenu.read")
    public Object getUserMenu(ModelMap modelMap, @RequestBody SysUserMenu param) {
        Parameter parameter = new Parameter(getService(), "queryMenuIdsByUserId", param.getUserId());
        logger.info("{} execute queryMenuIdsByUserId start...", parameter.getNo());
        List<?> menus = provider.execute(parameter).getResultList();
        logger.info("{} execute queryMenuIdsByUserId end.", parameter.getNo());
        return setSuccessModelMap(modelMap, menus);
    }

    @ApiOperation(value = "修改用户菜单")
    @PostMapping(value = "/user/update/menu")
    @RequiresPermissions("sys.permisson.userMenu.update")
    public Object userMenu(ModelMap modelMap, @RequestBody List<SysUserMenu> list) {
        Long userId = null;
        Long currentUserId = getCurrUser();
        for (SysUserMenu sysUserMenu : list) {
            if (sysUserMenu.getUserId() != null) {
                if (userId != null && sysUserMenu.getUserId() != null
                    && userId.longValue() != sysUserMenu.getUserId()) {
                    throw new IllegalParameterException("参数错误.");
                }
                userId = sysUserMenu.getUserId();
            }
            sysUserMenu.setCreateBy(currentUserId);
            sysUserMenu.setUpdateBy(currentUserId);
            sysUserMenu.setCreateTime(new Date());
            sysUserMenu.setUpdateTime(new Date());
        }
        Parameter parameter = new Parameter(getService(), "updateUserMenu", list);
        logger.info("{} execute updateUserMenu start...", parameter.getNo());
        provider.execute(parameter);
        logger.info("{} execute updateUserMenu end.", parameter.getNo());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取用户角色")
    @PutMapping(value = "user/read/role")
    @RequiresPermissions("sys.permisson.userRole.read")
    public Object getUserRole(ModelMap modelMap, @RequestBody SysUserRole param) {
        Parameter parameter = new Parameter(getService(), "getRolesByUserId", param.getUserId());
        logger.info("{} execute getRolesByUserId start...", parameter.getNo());
        List<?> menus = provider.execute(parameter).getResultList();
        logger.info("{} execute getRolesByUserId end.", parameter.getNo());
        return setSuccessModelMap(modelMap, menus);
    }

    @ApiOperation(value = "修改用户角色")
    @PostMapping(value = "/user/update/role")
    @RequiresPermissions("sys.permisson.userRole.update")
    public Object userRole(ModelMap modelMap, @RequestBody List<SysUserRole> list) {
        Long userId = null;
        Long currentUserId = getCurrUser();
        for (SysUserRole sysUserRole : list) {
            if (sysUserRole.getUserId() != null) {
                if (userId != null && sysUserRole.getUserId() != null
                    && userId.longValue() != sysUserRole.getUserId()) {
                    throw new IllegalParameterException("参数错误.");
                }
                userId = sysUserRole.getUserId();
            }
            sysUserRole.setCreateBy(currentUserId);
            sysUserRole.setUpdateBy(currentUserId);
            sysUserRole.setCreateTime(new Date());
            sysUserRole.setUpdateTime(new Date());
        }
        Parameter parameter = new Parameter(getService(), "updateUserRole", list);
        logger.info("{} execute updateUserRole start...", parameter.getNo());
        provider.execute(parameter);
        logger.info("{} execute updateUserRole end.", parameter.getNo());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取角色菜单编号")
    @PutMapping(value = "role/read/menu")
    @RequiresPermissions("sys.permisson.roleMenu.read")
    public Object getRoleMenu(ModelMap modelMap, @RequestBody SysRoleMenu param) {
        Parameter parameter = new Parameter(getService(), "queryMenuIdsByRoleId", param.getRoleId());
        logger.info("{} execute queryMenuIdsByRoleId start...", parameter.getNo());
        List<?> menus = provider.execute(parameter).getResultList();
        logger.info("{} execute queryMenuIdsByRoleId end.", parameter.getNo());
        return setSuccessModelMap(modelMap, menus);
    }

    @ApiOperation(value = "修改角色菜单")
    @PostMapping(value = "/role/update/menu")
    @RequiresPermissions("sys.permisson.roleMenu.update")
    public Object roleMenu(ModelMap modelMap, @RequestBody List<SysRoleMenu> list) {
        Long roleId = null;
        Long userId = getCurrUser();
        for (SysRoleMenu sysRoleMenu : list) {
            if (sysRoleMenu.getRoleId() != null) {
                if (roleId != null && sysRoleMenu.getRoleId() != null
                    && roleId.longValue() != sysRoleMenu.getRoleId()) {
                    throw new IllegalParameterException("参数错误.");
                }
                roleId = sysRoleMenu.getRoleId();
            }
            sysRoleMenu.setCreateBy(userId);
            sysRoleMenu.setUpdateBy(userId);
            sysRoleMenu.setCreateTime(new Date());
            sysRoleMenu.setUpdateTime(new Date());
        }
        Parameter parameter = new Parameter(getService(), "updateRoleMenu", list);
        logger.info("{} execute updateRoleMenu start...", parameter.getNo());
        provider.execute(parameter);
        logger.info("{} execute updateRoleMenu end.", parameter.getNo());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取人员操作权限")
    @PutMapping(value = "user/read/permission")
    @RequiresPermissions("sys.permisson.user.read")
    public Object queryUserPermissions(ModelMap modelMap, @RequestBody SysUserMenu record) {
        Parameter parameter = new Parameter(getService(), "queryUserPermissions", record);
        logger.info("{} execute queryUserPermissions start...", parameter.getNo());
        List<?> menuIds = provider.execute(parameter).getResultList();
        logger.info("{} execute queryUserPermissions end.", parameter.getNo());
        return setSuccessModelMap(modelMap, menuIds);
    }

    @ApiOperation(value = "修改用户操作权限")
    @PostMapping(value = "/user/update/permission")
    @RequiresPermissions("sys.permisson.user.update")
    public Object updateUserPermission(ModelMap modelMap, @RequestBody List<SysUserMenu> list) {
        Long userId = null;
        Long currentUserId = getCurrUser();
        for (SysUserMenu sysUserMenu : list) {
            if (sysUserMenu.getUserId() != null) {
                if (userId != null && sysUserMenu.getUserId() != null
                    && userId.longValue() != sysUserMenu.getUserId()) {
                    throw new IllegalParameterException("参数错误.");
                }
                userId = sysUserMenu.getUserId();
            }
            sysUserMenu.setCreateBy(currentUserId);
            sysUserMenu.setUpdateBy(currentUserId);
            sysUserMenu.setCreateTime(new Date());
            sysUserMenu.setUpdateTime(new Date());
        }
        Parameter parameter = new Parameter(getService(), "updateUserPermission", list);
        logger.info("{} execute updateUserPermission start...", parameter.getNo());
        provider.execute(parameter);
        logger.info("{} execute updateUserPermission end.", parameter.getNo());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取角色操作权限")
    @PutMapping(value = "role/read/permission")
    @RequiresPermissions("sys.permisson.role.read")
    public Object queryRolePermissions(ModelMap modelMap, @RequestBody SysRoleMenu record) {
        Parameter parameter = new Parameter(getService(), "queryRolePermissions", record);
        logger.info("{} execute queryRolePermissions start...", parameter.getNo());
        List<?> menuIds = provider.execute(parameter).getResultList();
        logger.info("{} execute queryRolePermissions end.", parameter.getNo());
        return setSuccessModelMap(modelMap, menuIds);
    }

    @ApiOperation(value = "修改角色操作权限")
    @PostMapping(value = "/role/update/permission")
    @RequiresPermissions("sys.permisson.role.update")
    public Object updateRolePermission(ModelMap modelMap, @RequestBody List<SysRoleMenu> list) {
        Long roleId = null;
        Long userId = getCurrUser();
        for (SysRoleMenu sysRoleMenu : list) {
            if (sysRoleMenu.getRoleId() != null) {
                if (roleId != null && sysRoleMenu.getRoleId() != null
                    && roleId.longValue() != sysRoleMenu.getRoleId()) {
                    throw new IllegalParameterException("参数错误.");
                }
                roleId = sysRoleMenu.getRoleId();
            }
            sysRoleMenu.setCreateBy(userId);
            sysRoleMenu.setUpdateBy(userId);
            sysRoleMenu.setCreateTime(new Date());
            sysRoleMenu.setUpdateTime(new Date());
        }
        Parameter parameter = new Parameter(getService(), "updateRolePermission", list);
        logger.info("{} execute updateRolePermission start...", parameter.getNo());
        provider.execute(parameter);
        logger.info("{} execute updateRolePermission end.", parameter.getNo());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "清理缓存")
    @RequiresPermissions("sys.cache.update")
    @RequestMapping(value = "/cache/update", method = RequestMethod.POST)
    public Object flush(ModelMap modelMap, @RequestBody Map<String, String> param) {
        Parameter parameter = new Parameter("sysCacheService", "flush", param);
        logger.info("{} execute sysCacheService.flush start...", parameter.getNo());
        provider.execute(parameter);
        logger.info("{} execute sysCacheService.flush end.", parameter.getNo());
        return setSuccessModelMap(modelMap);
    }
}
