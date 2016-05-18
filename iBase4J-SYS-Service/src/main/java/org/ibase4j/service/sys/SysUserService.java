/**
 * 
 */
package org.ibase4j.service.sys;

import java.util.Date;
import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.facade.sys.SysUserFacade;
import org.ibase4j.mybatis.generator.dao.SysUserMapper;
import org.ibase4j.mybatis.generator.dao.SysUserThirdpartyMapper;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.mybatis.generator.model.SysUserThirdparty;
import org.ibase4j.mybatis.sys.dao.SysUserExpandMapper;
import org.ibase4j.mybatis.sys.model.ThirdPartyUser;
import org.ibase4j.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 */
@DubboService(interfaceClass = SysUserFacade.class)
@CacheConfig(cacheNames = "sysUser")
public class SysUserService extends BaseService implements SysUserFacade {
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

	/** 查询第三方帐号用户Id */
	@Cacheable
	public String queryUserIdByThirdParty(String openId, String provider) {
		return sysUserExpandMapper.queryUserIdByThirdParty(provider, openId);
	}

	// 加密
	public String encryptPassword(String password) {
		return SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password));
	}

	/** 保存第三方帐号 */
	@CachePut
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
		sysUser.setAccount(thirdparty.getProvider() + sysUser.getId());
		this.update(sysUser);
		thirdparty.setUserId(sysUser.getId());
		thirdpartyMapper.insert(thirdparty);
		return sysUser;
	}
}
