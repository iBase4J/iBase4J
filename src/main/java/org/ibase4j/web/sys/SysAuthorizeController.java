package org.ibase4j.web.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;
import org.ibase4j.service.sys.SysAuthorizeService;
import org.ibase4j.service.sys.SysCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.AbstractController;
import top.ibase4j.core.exception.IllegalParameterException;

/**
 * 权限管理
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:05
 */
@RestController
@Api(value = "权限管理", description = "权限管理")
public class SysAuthorizeController extends AbstractController {
    @Resource
    private SysAuthorizeService authorizeService;
    @Resource
    private SysCacheService sysCacheService;

    @ApiOperation(value = "获取用户菜单编号")
    @GetMapping(value = "user/read/menu")
    @RequiresPermissions("sys.permisson.userMenu.read")
    public Object getUserMenu(SysUserMenu param) {
        List<?> menus = authorizeService.queryMenuIdsByUserId(param.getUserId());
        return setSuccessModelMap(menus);
    }

    @ApiOperation(value = "修改用户菜单")
    @PostMapping(value = "/user/update/menu")
    @RequiresPermissions("sys.permisson.userMenu.update")
    public Object userMenu(@RequestBody List<SysUserMenu> list) {
        Long userId = null;
        Long currentUserId = getCurrUser().getId();
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
        authorizeService.updateUserMenu(list);
        return setSuccessModelMap();
    }

    @ApiOperation(value = "获取用户角色")
    @GetMapping(value = "user/read/role")
    @RequiresPermissions("sys.permisson.userRole.read")
    public Object getUserRole(SysUserRole param) {
        List<?> menus = authorizeService.getRolesByUserId(param.getUserId());
        return setSuccessModelMap(menus);
    }

    @ApiOperation(value = "修改用户角色")
    @PostMapping(value = "/user/update/role")
    @RequiresPermissions("sys.permisson.userRole.update")
    public Object userRole(@RequestBody List<SysUserRole> list) {
        Long userId = null;
        Long currentUserId = getCurrUser().getId();
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
        authorizeService.updateUserRole(list);
        return setSuccessModelMap();
    }

    @ApiOperation(value = "获取角色菜单编号")
    @GetMapping(value = "role/read/menu")
    @RequiresPermissions("sys.permisson.roleMenu.read")
    public Object getRoleMenu(SysRoleMenu param) {
        List<?> menus = authorizeService.queryMenuIdsByRoleId(param.getRoleId());
        return setSuccessModelMap(menus);
    }

    @ApiOperation(value = "修改角色菜单")
    @PostMapping(value = "/role/update/menu")
    @RequiresPermissions("sys.permisson.roleMenu.update")
    public Object roleMenu(@RequestBody List<SysRoleMenu> list) {
        Long roleId = null;
        Long userId = getCurrUser().getId();
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
        authorizeService.updateRoleMenu(list);
        return setSuccessModelMap();
    }

    @ApiOperation(value = "获取人员操作权限")
    @GetMapping(value = "user/read/permission")
    @RequiresPermissions("sys.permisson.user.read")
    public Object queryUserPermissions(SysUserMenu record) {
        List<?> menuIds = authorizeService.queryUserPermissions(record);
        return setSuccessModelMap(menuIds);
    }

    @ApiOperation(value = "修改用户操作权限")
    @PostMapping(value = "/user/update/permission")
    @RequiresPermissions("sys.permisson.user.update")
    public Object updateUserPermission(@RequestBody List<SysUserMenu> list) {
        Long userId = null;
        Long currentUserId = getCurrUser().getId();
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
        authorizeService.updateUserPermission(list);
        return setSuccessModelMap();
    }

    @ApiOperation(value = "获取角色操作权限")
    @GetMapping(value = "role/read/permission")
    @RequiresPermissions("sys.permisson.role.read")
    public Object queryRolePermissions(SysRoleMenu record) {
        List<?> menuIds = authorizeService.queryRolePermissions(record);
        return setSuccessModelMap(menuIds);
    }

    @ApiOperation(value = "修改角色操作权限")
    @PostMapping(value = "/role/update/permission")
    @RequiresPermissions("sys.permisson.role.update")
    public Object updateRolePermission(@RequestBody List<SysRoleMenu> list) {
        Long roleId = null;
        Long userId = getCurrUser().getId();
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
        authorizeService.updateRolePermission(list);
        return setSuccessModelMap();
    }

    @ApiOperation(value = "清理缓存")
    @RequiresPermissions("sys.cache.update")
    @RequestMapping(value = "/cache/update", method = RequestMethod.POST)
    public Object flush(Map<String, String> param) {
        sysCacheService.flush(param);
        return setSuccessModelMap();
    }
}
