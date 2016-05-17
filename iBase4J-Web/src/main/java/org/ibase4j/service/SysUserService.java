package org.ibase4j.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.shiro.LoginHelper;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.facade.sys.SysUserFacade;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.mybatis.sys.model.ThirdPartyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;

@Service
public class SysUserService {
	@Autowired
	private SysUserFacade sysUserFacade;

	/** 修改用户信息 */
	public void updateUserInfo(SysUser sysUser) {
		Assert.notNull(sysUser.getId(), Resources.getMessage("USER_ID_IS_NULL"));
		Assert.notNull(sysUser.getAccount(), Resources.getMessage("ACCOUNT_IS_NULL"));
		SysUser user = sysUserFacade.queryById(sysUser.getId());
		Assert.notNull(user, String.format(Resources.getMessage("USER_IS_NULL"), sysUser.getId()));
		sysUser.setPassword(user.getPassword());
		sysUserFacade.update(sysUser);
	}

	public PageInfo<SysUser> query(Map<String, Object> params) {
		return sysUserFacade.query(params);
	}

	public SysUser queryById(Integer id) {
		Assert.notNull(id, Resources.getMessage("USER_ID_IS_NULL"));
		SysUser sysUser = sysUserFacade.queryById(id);
		sysUser.setPassword(null);
		return sysUser;
	}

	public void updatePassword(Integer id, String password) {
		Assert.notNull(id, Resources.getMessage("USER_ID_IS_NULL"));
		Assert.notNull(password, Resources.getMessage("PASSWORD_IS_NULL"));
		SysUser sysUser = sysUserFacade.queryById(id);
		Assert.notNull(sysUser, String.format(Resources.getMessage("USER_IS_NULL"), id));
		sysUser.setPassword(SecurityUtil.encryptSHA(password));
		sysUserFacade.update(sysUser);
	}

	public String encryptPassword(String password) {
		return sysUserFacade.encryptPassword(password);
	}

	public void addUser(SysUser sysUser) {
		Assert.notNull(sysUser.getAccount(), Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.notNull(sysUser.getPassword(), Resources.getMessage("PASSWORD_IS_NULL"));
		sysUser.setPassword(sysUserFacade.encryptPassword(sysUser.getPassword()));
		sysUserFacade.update(sysUser);
	}

	public void thirdPartyLogin(ThirdPartyUser thirdUser) {
		// 查询是否已经绑定过
		String userId = sysUserFacade.queryUserIdByThirdParty(thirdUser.getOpenid(), thirdUser.getProvider());
		if (StringUtils.isBlank(userId)) {
			SysUser sysUser = insertThirdPartyUser(thirdUser);
			LoginHelper.login(sysUser.getAccount(), sysUser.getPassword());
		}
	}

	public SysUser insertThirdPartyUser(ThirdPartyUser thirdUser) {
		return sysUserFacade.insertThirdPartyUser(thirdUser);
	}
}
