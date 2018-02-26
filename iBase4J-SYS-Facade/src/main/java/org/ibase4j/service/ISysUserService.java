package org.ibase4j.service;

import org.ibase4j.model.SysUser;

import top.ibase4j.core.base.IBaseService;
import top.ibase4j.core.support.login.ThirdPartyUser;

/**
 * SysUser服务接口
 * 
 * @author ShenHuaJie
 * @version 2016-08-27 22:39:42
 */
public interface ISysUserService extends IBaseService<SysUser> {

	public String encryptPassword(String password);

	/** 查询第三方帐号用户Id */
	public Long queryUserIdByThirdParty(ThirdPartyUser thirdPartyUser);

	/** 保存第三方帐号 */
	public SysUser insertThirdPartyUser(ThirdPartyUser thirdPartyUser);

	/**
	 * 加载所有用户信息
	 */
	public void init();
}
