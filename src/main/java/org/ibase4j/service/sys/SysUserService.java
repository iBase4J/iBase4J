/**
 * 
 */
package org.ibase4j.service.sys;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.mybatis.generator.dao.SysUserMapper;
import org.ibase4j.mybatis.generator.dao.SysUserThirdpartyMapper;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.mybatis.generator.model.SysUserThirdparty;
import org.ibase4j.mybatis.sys.dao.SysUserExpandMapper;
import org.ibase4j.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 */
@Service
@CacheConfig(cacheNames = "sysUser")
public class SysUserService extends BaseService {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserExpandMapper sysUserExpandMapper;
	@Autowired
	private SysUserThirdpartyMapper thirdpartyMapper;

	@CachePut
	@Transactional
	public void update(SysUser record) {
		if (record.getId() == null) {
			record.setUsable(1);
			record.setCreateTime(new Date());
			sysUserMapper.insert(record);
		} else {
			sysUserMapper.updateByPrimaryKey(record);
		}
	}

	@CacheEvict
	@Transactional
	public void delete(Integer id) {
		SysUser record = queryById(id);
		Assert.notNull(record, String.format(Resources.getMessage("USER_IS_NULL"), id));
		record.setUsable(0);
		update(record);
	}

	@Cacheable
	public SysUser queryById(Integer id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Cacheable
	public PageInfo<SysUser> query(Map<String, Object> params) {
		this.startPage(params);
		Page<SysUser> list = sysUserExpandMapper.query(params);
		return new PageInfo<SysUser>(list);
	}

	/** 用户登录 */
	public Boolean login(String account, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return subject.isAuthenticated();
		} catch (LockedAccountException e) {
			throw new IllegalArgumentException(Resources.getMessage("ACCOUNT_LOCKED", token.getPrincipal()));
		} catch (DisabledAccountException e) {
			throw new IllegalArgumentException(Resources.getMessage("ACCOUNT_DISABLED", token.getPrincipal()));
		} catch (ExpiredCredentialsException e) {
			throw new IllegalArgumentException(Resources.getMessage("ACCOUNT_EXPIRED", token.getPrincipal()));
		} catch (Exception e) {
			throw new IllegalArgumentException(Resources.getMessage("LOGIN_FAIL"), e);
		}
	}

	/** 查询第三方帐号用户Id */
	public String queryUserIdByThirdParty(String openId, String provider) {
		return sysUserExpandMapper.queryUserIdByThirdParty(provider, openId);
	}

	/** 保存第三方帐号 */
	public void insertThirdPartyUser(ThirdPartyUser thirdPartyUser) {
		SysUser sysUser = new SysUser();
		sysUser.setSex(0);
		sysUser.setUserType(1);
		sysUser.setPassword(WebUtil.encryptPassword("123456"));
		sysUser.setUserName(thirdPartyUser.getUserName());
		sysUser.setAvatar(thirdPartyUser.getAvatarUrl());
		this.update(sysUser);
		sysUser.setAccount(thirdPartyUser.getProvider() + sysUser.getId());
		this.update(sysUser);

		SysUserThirdparty thirdparty = new SysUserThirdparty();
		thirdparty.setUserId(sysUser.getId());
		thirdparty.setProvider(thirdPartyUser.getProvider());
		thirdparty.setOpenId(thirdPartyUser.getOpenid());
		thirdparty.setCreateTime(new Date());
		thirdpartyMapper.insert(thirdparty);

		this.login(sysUser.getAccount(), sysUser.getPassword());
	}
}
