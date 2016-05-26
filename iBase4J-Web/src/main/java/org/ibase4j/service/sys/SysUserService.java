package org.ibase4j.service.sys;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.login.LoginHelper;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.mybatis.sys.model.SysUserBean;
import org.ibase4j.provider.sys.SysUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:21
 */
@Service
public class SysUserService {
	@Autowired
	private SysUserProvider sysUserProvider;

	/** 修改用户信息 */
	public void updateUserInfo(SysUser sysUser) {
		Assert.notNull(sysUser.getId(), Resources.getMessage("USER_ID_IS_NULL"));
		Assert.notNull(sysUser.getAccount(), Resources.getMessage("ACCOUNT_IS_NULL"));
		SysUser user = sysUserProvider.queryById(sysUser.getId());
		Assert.notNull(user, String.format(Resources.getMessage("USER_IS_NULL"), sysUser.getId()));
		sysUser.setPassword(user.getPassword());
		sysUserProvider.update(sysUser);
	}

	public PageInfo<SysUserBean> queryBeans(Map<String, Object> params) {
		return sysUserProvider.queryBeans(params);
	}

	public SysUser queryById(Integer id) {
		Assert.notNull(id, Resources.getMessage("USER_ID_IS_NULL"));
		SysUser sysUser = sysUserProvider.queryById(id);
		if (sysUser != null) {
			sysUser.setPassword(null);
		}
		return sysUser;
	}

	public void updatePassword(Integer id, String password) {
		Assert.notNull(id, Resources.getMessage("USER_ID_IS_NULL"));
		Assert.notNull(password, Resources.getMessage("PASSWORD_IS_NULL"));
		SysUser sysUser = sysUserProvider.queryById(id);
		Assert.notNull(sysUser, String.format(Resources.getMessage("USER_IS_NULL"), id));
		sysUser.setPassword(SecurityUtil.encryptSHA(password));
		sysUserProvider.update(sysUser);
	}

	public String encryptPassword(String password) {
		return sysUserProvider.encryptPassword(password);
	}

	public void addUser(SysUser sysUser) {
		Assert.notNull(sysUser.getAccount(), Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.notNull(sysUser.getPassword(), Resources.getMessage("PASSWORD_IS_NULL"));
		sysUser.setPassword(sysUserProvider.encryptPassword(sysUser.getPassword()));
		sysUserProvider.update(sysUser);
	}

	public void thirdPartyLogin(ThirdPartyUser thirdUser) {
		// 查询是否已经绑定过
		String userId = sysUserProvider.queryUserIdByThirdParty(thirdUser.getOpenid(), thirdUser.getProvider());
		if (StringUtils.isBlank(userId)) {
			SysUser sysUser = insertThirdPartyUser(thirdUser);
			LoginHelper.login(sysUser.getAccount(), sysUser.getPassword());
		}
	}

	public SysUser insertThirdPartyUser(ThirdPartyUser thirdUser) {
		return sysUserProvider.insertThirdPartyUser(thirdUser);
	}
}
