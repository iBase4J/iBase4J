package org.ibase4j.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.sys.SysRoleMapper;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.model.sys.SysRole;
import org.ibase4j.service.sys.ISysAuthorizeService;
import org.ibase4j.service.sys.ISysDeptService;
import org.ibase4j.service.sys.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.support.Pagination;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@Service
@CacheConfig(cacheNames = "sysRole")
public class SysRoleServiceImpl extends BaseService<SysRole, SysRoleMapper> implements ISysRoleService {
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysAuthorizeService sysAuthorizeService;

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
}
