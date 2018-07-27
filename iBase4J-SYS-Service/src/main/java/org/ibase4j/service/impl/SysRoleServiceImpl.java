package org.ibase4j.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.SysRoleMapper;
import org.ibase4j.model.SysDept;
import org.ibase4j.model.SysRole;
import org.ibase4j.service.SysAuthorizeService;
import org.ibase4j.service.SysDeptService;
import org.ibase4j.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseServiceImpl;
import top.ibase4j.core.support.Pagination;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysRole")
@Service(interfaceClass = SysRoleService.class)
@MotanService(interfaceClass = SysRoleService.class)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleMapper> implements SysRoleService {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysAuthorizeService sysAuthorizeService;

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
