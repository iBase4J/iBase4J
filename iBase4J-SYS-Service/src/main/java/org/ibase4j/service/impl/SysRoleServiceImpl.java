package org.ibase4j.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.SysRoleMapper;
import org.ibase4j.mapper.SysRoleMenuMapper;
import org.ibase4j.mapper.SysUserRoleMapper;
import org.ibase4j.model.SysDept;
import org.ibase4j.model.SysRole;
import org.ibase4j.model.SysRoleMenu;
import org.ibase4j.model.SysUserRole;
import org.ibase4j.service.SysAuthorizeService;
import org.ibase4j.service.SysDeptService;
import org.ibase4j.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import top.ibase4j.core.base.BaseServiceImpl;
import top.ibase4j.core.support.Pagination;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysRole")
@Service(interfaceClass = SysRoleService.class)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleMapper> implements SysRoleService {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysAuthorizeService sysAuthorizeService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public SysRole queryById(Long id) {
        SysRole sysRole = super.queryById(id);
        if (sysRole != null) {
            if (sysRole.getDeptId() != null && sysRole.getDeptId() != 0) {
                SysDept sysDept = sysDeptService.queryById(sysRole.getDeptId());
                if (sysDept != null) {
                    sysRole.setDeptName(sysDept.getDeptName());
                } else {
                    sysRole.setDeptId(null);
                }
            }
        }
        return sysRole;
    }

    @Override
    public Pagination<SysRole> query(Map<String, Object> params) {
        Pagination<SysRole> pageInfo = super.query(params);
        // 权限信息
        for (SysRole bean : pageInfo.getRecords()) {
            if (bean.getDeptId() != null && bean.getDeptId() != 0) {
                SysDept sysDept = sysDeptService.queryById(bean.getDeptId());
                if (sysDept != null) {
                    bean.setDeptName(sysDept.getDeptName());
                }
            }
            List<String> permissions = sysAuthorizeService.queryRolePermission(bean.getId());
            for (String permission : permissions) {
                if (StringUtils.isBlank(bean.getPermission())) {
                    bean.setPermission(permission);
                } else {
                    bean.setPermission(bean.getPermission() + ";" + permission);
                }
            }
        }
        return pageInfo;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        super.delete(id);
        sysUserRoleMapper.delete(new UpdateWrapper<SysUserRole>(new SysUserRole().setRoleId(id)));
        sysRoleMenuMapper.delete(new UpdateWrapper<SysRoleMenu>(new SysRoleMenu().setMenuId(id)));
    }
}
