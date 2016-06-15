package org.ibase4j.service.sys;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.Assert;
import org.ibase4j.core.support.login.LoginHelper;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.generator.SysUser;
import org.ibase4j.model.sys.SysUserBean;
import org.ibase4j.provider.sys.SysUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:21
 */
@Service
public class SysUserService extends BaseService<SysUserProvider, SysUser> {
	@Autowired
	public void setProvider(SysUserProvider provider) {
		this.provider = provider;
	}

	/** 修改用户信息 */
	@CachePut
	public void updateUserInfo(SysUser sysUser) {
		Assert.notNull(sysUser.getId(), Resources.getMessage("USER_ID_IS_NULL"));
		Assert.isNotBlank(sysUser.getAccount(), Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.length(sysUser.getAccount(), 3, 15, Resources.getMessage("ACCOUNT_LENGTH", 3, 15));
		SysUser user = this.queryById(sysUser.getId());
		Assert.notNull(user, String.format(Resources.getMessage("USER_IS_NULL"), sysUser.getId()));
		if (StringUtils.isBlank(sysUser.getPassword())) {
			sysUser.setPassword(user.getPassword());
		}
		sysUser.setUpdateBy(WebUtil.getCurrentUser());
		provider.update(sysUser);
	}

	public PageInfo<SysUserBean> queryBeans(Map<String, Object> params) {
		return provider.queryBeans(params);
	}

	public void updatePassword(Integer id, String password) {
		Assert.notNull(id, Resources.getMessage("USER_ID_IS_NULL"));
		Assert.isNotBlank(password, Resources.getMessage("PASSWORD_IS_NULL"));
		SysUser sysUser = provider.queryById(id);
		Assert.notNull(sysUser, String.format(Resources.getMessage("USER_IS_NULL"), id));
		sysUser.setPassword(SecurityUtil.encryptSHA(password));
		sysUser.setUpdateBy(WebUtil.getCurrentUser());
		provider.update(sysUser);
	}

	public String encryptPassword(String password) {
		return provider.encryptPassword(password);
	}

	public void thirdPartyLogin(ThirdPartyUser thirdUser) {
		// 查询是否已经绑定过
		String userId = provider.queryUserIdByThirdParty(thirdUser.getOpenid(), thirdUser.getProvider());
		if (StringUtils.isBlank(userId)) {
			SysUser sysUser = insertThirdPartyUser(thirdUser);
			LoginHelper.login(sysUser.getAccount(), sysUser.getPassword());
		}
	}

	public SysUser insertThirdPartyUser(ThirdPartyUser thirdUser) {
		return provider.insertThirdPartyUser(thirdUser);
	}
}
