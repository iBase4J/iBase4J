package org.ibase4j.provider.sys;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.core.util.RedisUtil;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.dao.sys.SysUserMapper;
import org.ibase4j.dao.sys.SysUserThirdpartyMapper;
import org.ibase4j.model.sys.SysUser;
import org.ibase4j.model.sys.SysUserThirdparty;
import org.ibase4j.model.sys.ext.SysUserBean;
import org.ibase4j.provider.sys.ISysDeptProvider;
import org.ibase4j.provider.sys.ISysDicProvider;
import org.ibase4j.provider.sys.ISysUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * SysUser服务实现类
 * @author ShenHuaJie
 * @version 2016-08-27 22:39:42
 */
@CacheConfig(cacheNames = "SysUser")
@DubboService(interfaceClass = ISysUserProvider.class)
public class SysUserProviderImpl extends BaseProviderImpl<SysUser> implements ISysUserProvider {
    @Autowired
    private SysUserThirdpartyMapper thirdpartyMapper;
    @Autowired
    private ISysDicProvider sysDicProvider;
    @Autowired
    private ISysDeptProvider sysDeptProvider;
    @Autowired
    private SysUserMapper sysUserMapper;

    public Page<SysUser> query(Map<String, Object> params) {
        Page<Long> page = this.getPage(params);
        page.setRecords(mapper.selectIdByMap(page, params));
        return getPage(page);
    }

    public Page<SysUserBean> queryBeans(Map<String, Object> params) {
        Page<Long> idPage = this.getPage(params);
        idPage.setRecords(mapper.selectIdByMap(idPage, params));
        Map<String, String> userTypeMap = sysDicProvider.queryDicByDicIndexKey("USERTYPE");
        Page<SysUserBean> pageInfo = getPage(idPage, SysUserBean.class);
        for (SysUserBean userBean : pageInfo.getRecords()) {
            if (userBean.getUserType() != null) {
                userBean.setUserTypeText(userTypeMap.get(userBean.getUserType().toString()));
            }
            if (userBean.getDeptId() != null) {
                userBean.setDeptName(sysDeptProvider.queryById(userBean.getDeptId()).getDeptName());
            }
        }
        return pageInfo;
    }

    /** 查询第三方帐号用户Id */
    @Cacheable
    public Long queryUserIdByThirdParty(String openId, String provider) {
        return thirdpartyMapper.queryUserIdByThirdParty(provider, openId);
    }

    // 加密
    public String encryptPassword(String password) {
        return SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password));
    }

    /** 保存第三方帐号 */
    @Transactional
    public SysUser insertThirdPartyUser(ThirdPartyUser thirdPartyUser) {
        SysUser sysUser = new SysUser();
        sysUser.setSex(0);
        sysUser.setUserType(1);
        sysUser.setPassword(this.encryptPassword("123456"));
        sysUser.setUserName(thirdPartyUser.getUserName());
        sysUser.setAvatar(thirdPartyUser.getAvatarUrl());
        // 初始化第三方信息
        SysUserThirdparty thirdparty = new SysUserThirdparty();
        thirdparty.setProvider(thirdPartyUser.getProvider());
        thirdparty.setOpenId(thirdPartyUser.getOpenid());
        thirdparty.setCreateTime(new Date());

        this.update(sysUser);
        sysUser.setAccount(thirdparty.getProvider() + sysUser.getId_());
        this.update(sysUser);
        thirdparty.setUserId(sysUser.getId_());
        thirdpartyMapper.insert(thirdparty);
        return sysUser;
    }

    public void init() {
        List<Long> list = sysUserMapper.selectIdByMap(Collections.<String, Object>emptyMap());
        for (Long id : list) {
            RedisUtil.set(getCacheKey(id), mapper.selectById(id));
        }
    }
}
