package org.ibase4j.provider.sys;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.core.util.CacheUtil;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.dao.sys.SysUserMapper;
import org.ibase4j.dao.sys.SysUserMenuMapper;
import org.ibase4j.dao.sys.SysUserThirdpartyMapper;
import org.ibase4j.model.sys.SysUser;
import org.ibase4j.model.sys.SysUserThirdparty;
import org.ibase4j.model.sys.ext.SysUserBean;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * SysUser服务实现类
 * 
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
	private SysUserMenuMapper sysUserMenuMapper;

	public Page<SysUserBean> queryBeans(Map<String, Object> params) {
		Page<Long> idPage = getPage(params);
		idPage.setRecords(mapper.selectIdPage(idPage, params));
		Map<String, String> userTypeMap = sysDicProvider.queryDicByDicIndexKey("USERTYPE");
		Page<SysUserBean> pageInfo = getPage(idPage, SysUserBean.class);
		for (SysUserBean userBean : pageInfo.getRecords()) {
			if (userBean.getUserType() != null) {
				userBean.setUserTypeText(userTypeMap.get(userBean.getUserType().toString()));
			}
			if (userBean.getDeptId() != null) {
				userBean.setDeptName(sysDeptProvider.queryById(userBean.getDeptId()).getDeptName());
			}
			List<String> permissions = sysUserMenuMapper.queryPermission(userBean.getId());
			for (String permission : permissions) {
				if (StringUtils.isBlank(userBean.getPermission())) {
					userBean.setPermission(permission);
				} else {
					userBean.setPermission(userBean.getPermission() + ";"  + permission);
				}
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
		sysUser.setAccount(thirdparty.getProvider() + sysUser.getId());
		this.update(sysUser);
		thirdparty.setUserId(sysUser.getId());
		thirdpartyMapper.insert(thirdparty);
		return sysUser;
	}

	public void init() {
		List<Long> list = ((SysUserMapper) mapper).selectIdPage(Collections.<String, Object>emptyMap());
		for (Long id : list) {
			CacheUtil.getCache().set(getCacheKey(id), mapper.selectById(id));
		}
	}
}
