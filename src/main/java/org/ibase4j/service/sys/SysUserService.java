/**
 * 
 */
package org.ibase4j.service.sys;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.Assert;
import org.ibase4j.core.support.login.LoginHelper;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.dao.generator.SysUserThirdpartyMapper;
import org.ibase4j.dao.sys.SysUserExpandMapper;
import org.ibase4j.model.generator.SysUser;
import org.ibase4j.model.generator.SysUserThirdparty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

/**
 * @author ShenHuaJie
 */
@Service
@CacheConfig(cacheNames = "sysUser")
public class SysUserService extends BaseService<SysUser> {
    @Autowired
    private SysUserExpandMapper sysUserExpandMapper;
    @Autowired
    private SysUserThirdpartyMapper thirdpartyMapper;

    @Cacheable
    public PageInfo<SysUser> query(Map<String, Object> params) {
        this.startPage(params);
        Page<Integer> ids = sysUserExpandMapper.query(params);
        return new PageInfo<SysUser>(getList(ids));
    }

    /** 查询第三方帐号用户Id */
    public Integer queryUserIdByThirdParty(String openId, String provider) {
        return sysUserExpandMapper.queryUserIdByThirdParty(provider, openId);
    }

    /** 保存第三方帐号 
     * @return */
    public SysUser insertThirdPartyUser(ThirdPartyUser thirdPartyUser) {
        SysUser sysUser = new SysUser();
        sysUser.setSex(0);
        sysUser.setUserType(1);
        sysUser.setPassword(WebUtil.encryptPassword("123456"));
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

    /**
     * @param thirdUser
     */
    public void thirdPartyLogin(ThirdPartyUser thirdUser) {
        SysUser sysUser = null;
        // 查询是否已经绑定过
        Integer userId = queryUserIdByThirdParty(thirdUser.getOpenid(), thirdUser.getProvider());
        if (userId == null) {
            sysUser = insertThirdPartyUser(thirdUser);
        } else {
            sysUser = queryById(userId);
        }
        LoginHelper.login(sysUser.getAccount(), sysUser.getPassword());
    }

    /**
     * @param sysUser
     */
    public void updateUserInfo(SysUser sysUser) {
        Assert.notNull(sysUser.getId(), "USER_ID");
        Assert.isNotBlank(sysUser.getAccount(), "ACCOUNT");
        Assert.length(sysUser.getAccount(), 3, 15, "ACCOUNT");
        SysUser user = this.queryById(sysUser.getId());
        Assert.notNull(user, "USER", sysUser.getId());
        if (StringUtils.isBlank(sysUser.getPassword())) {
            sysUser.setPassword(user.getPassword());
        }
        if (StringUtil.isEmpty(sysUser.getAvatar())) {
            sysUser.setAvatar(user.getAvatar());
        }
        update(sysUser);
    }

    /**
     * @param id
     * @param password
     */
    public void updatePassword(Integer id, String password) {
        Assert.notNull(id, "USER_ID");
        Assert.isNotBlank(password, "PASSWORD");
        SysUser sysUser = queryById(id);
        Assert.notNull(sysUser, "USER", id);
        Integer userId = WebUtil.getCurrentUser();
        if (!id.equals(userId)) {
            SysUser user = queryById(userId);
            if (user.getUserType() == 1) {
                throw new UnauthorizedException();
            }
        }
        sysUser.setPassword(WebUtil.encryptPassword(password));
        update(sysUser);
    }
}
