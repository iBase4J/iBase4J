package org.ibase4j.service.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.sys.SysUserMapper;
import org.ibase4j.mapper.sys.SysUserThirdpartyMapper;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.model.sys.SysUser;
import org.ibase4j.model.sys.SysUserThirdparty;
import org.ibase4j.service.sys.SysAuthorizeService;
import org.ibase4j.service.sys.SysDeptService;
import org.ibase4j.service.sys.SysDicService;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.ibase4j.core.base.BaseServiceImpl;
import top.ibase4j.core.exception.BusinessException;
import top.ibase4j.core.support.Pagination;
import top.ibase4j.core.support.login.ThirdPartyUser;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.SecurityUtil;

/**
 * SysUser服务实现类
 *
 * @author ShenHuaJie
 * @version 2016-08-27 22:39:42
 */
@Service
@CacheConfig(cacheNames = "sysUser")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserMapper> implements SysUserService {
    @Autowired
    private SysUserThirdpartyMapper thirdpartyMapper;
    @Autowired
    private SysDicService sysDicService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysAuthorizeService sysAuthorizeService;

    @Override
    @Transactional
    public SysUser update(SysUser record) {
        if (DataUtil.isNotEmpty(record.getOldPassword())) {
            SysUser sysUser = super.queryById(record.getId());
            String encryptPassword = SecurityUtil.encryptPassword(record.getOldPassword());
            if (!sysUser.getPassword().equals(encryptPassword)) {
                throw new BusinessException("原密码错误.");
            }
        }
        if (DataUtil.isEmpty(record.getPassword())) {
            record.setPassword(null);
        } else {
            record.setPassword(SecurityUtil.encryptPassword(record.getPassword()));
        }
        return super.update(record);
    }

    @Override
    public SysUser queryById(Long id) {
        SysUser record = super.queryById(id);
        if (record.getDeptId() != null) {
            SysDept sysDept = sysDeptService.queryById(record.getDeptId());
            if (sysDept != null) {
                record.setDeptName(sysDept.getDeptName());
            } else {
                record.setDeptId(null);
            }
        }
        return record;
    }

    @Override
    public Pagination<SysUser> query(Map<String, Object> params) {
        Map<String, String> userTypeMap = sysDicService.queryDicByType("USERTYPE");
        Pagination<SysUser> pageInfo = super.query(params);
        for (SysUser userBean : pageInfo.getRecords()) {
            if (userBean.getDeptId() != null) {
                SysDept sysDept = sysDeptService.queryById(userBean.getDeptId());
                if (sysDept != null) {
                    userBean.setDeptName(sysDept.getDeptName());
                } else {
                    userBean.setDeptId(null);
                }
            }
            if (userBean.getUserType() != null) {
                userBean.setUserTypeText(userTypeMap.get(userBean.getUserType()));
            }
            List<String> permissions = sysAuthorizeService.queryUserPermission(userBean.getId());
            for (String permission : permissions) {
                if (StringUtils.isBlank(userBean.getPermission())) {
                    userBean.setPermission(permission);
                } else {
                    userBean.setPermission(userBean.getPermission() + ";" + permission);
                }
            }
        }
        return pageInfo;
    }

    /** 查询第三方帐号用户Id */
    @Override
    @Cacheable
    public Long queryUserIdByThirdParty(ThirdPartyUser param) {
        return thirdpartyMapper.queryUserIdByThirdParty(param.getProvider(), param.getOpenid());
    }

    /** 保存第三方帐号 */
    @Override
    @Transactional
    public SysUser insertThirdPartyUser(ThirdPartyUser thirdPartyUser) {
        SysUser sysUser = new SysUser();
        sysUser.setSex(0);
        sysUser.setUserType("1");
        sysUser.setPassword(SecurityUtil.encryptPassword("123456"));
        sysUser.setUserName(thirdPartyUser.getUserName());
        sysUser.setAvatar(thirdPartyUser.getAvatarUrl());
        // 初始化第三方信息
        SysUserThirdparty thirdparty = new SysUserThirdparty();
        thirdparty.setProvider(thirdPartyUser.getProvider());
        thirdparty.setOpenId(thirdPartyUser.getOpenid());
        thirdparty.setCreateTime(new Date());

        this.update(sysUser);
        sysUser.setAccount(thirdparty.getProvider() + sysUser.getId());
        this.update(sysUser);
        thirdparty.setUserId(sysUser.getId());
        thirdpartyMapper.insert(thirdparty);
        return sysUser;
    }

    @Override
    public void init() {
        queryList(InstanceUtil.newHashMap());
    }
}
