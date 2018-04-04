package org.ibase4j.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.SysUserThirdpartyMapper;
import org.ibase4j.model.SysDept;
import org.ibase4j.model.SysUser;
import org.ibase4j.model.SysUserThirdparty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.exception.BusinessException;
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
public class SysUserService extends BaseService<SysUser> {
    @Autowired
    private SysUserThirdpartyMapper thirdpartyMapper;
    @Autowired
    private SysDicService sysDicService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysAuthorizeService sysAuthorizeService;

    @Transactional
    public SysUser update(SysUser record) {
        if (DataUtil.isEmpty(record.getPassword())) {
            record.setPassword(null);
        } else {
            record.setPassword(SecurityUtil.encryptPassword(record.getPassword()));
        }
        if (DataUtil.isNotEmpty(record.getOldPassword())) {
            SysUser sysUser = super.queryById(record.getId());
            String encryptPassword = SecurityUtil.encryptPassword(record.getOldPassword());
            if (!sysUser.getPassword().equals(encryptPassword)) {
                throw new BusinessException("原密码错误.");
            }
        }
        return super.update(record);
    }

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

    public Page<SysUser> query(Map<String, Object> params) {
        Map<String, String> userTypeMap = sysDicService.queryDicByType("USERTYPE");
        Page<SysUser> pageInfo = super.query(params);
        for (SysUser sysUser : pageInfo.getRecords()) {
            if (sysUser.getDeptId() != null) {
                SysDept sysDept = sysDeptService.queryById(sysUser.getDeptId());
                if (sysDept != null) {
                    sysUser.setDeptName(sysDept.getDeptName());
                } else {
                    sysUser.setDeptId(null);
                }
            }
            if (sysUser.getUserType() != null) {
                sysUser.setUserTypeText(userTypeMap.get(sysUser.getUserType()));
            }
            List<String> permissions = sysAuthorizeService.queryUserPermission(sysUser.getId());
            for (String permission : permissions) {
                if (StringUtils.isBlank(sysUser.getPermission())) {
                    sysUser.setPermission(permission);
                } else {
                    sysUser.setPermission(sysUser.getPermission() + ";" + permission);
                }
            }
            sysUser.setPassword(null);
        }
        return pageInfo;
    }

    /** 查询第三方帐号用户Id */
    @Cacheable
    public Long queryUserIdByThirdParty(ThirdPartyUser param) {
        return thirdpartyMapper.queryUserIdByThirdParty(param.getProvider(), param.getOpenid());
    }

    /** 保存第三方帐号 */
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

    public void init() {
        queryList(InstanceUtil.newHashMap());
    }
}
