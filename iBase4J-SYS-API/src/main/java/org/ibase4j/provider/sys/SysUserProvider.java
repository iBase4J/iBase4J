/**
 * 
 */
package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.model.generator.SysUser;
import org.ibase4j.model.sys.SysUserBean;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:47
 */
public interface SysUserProvider extends BaseProvider<SysUser> {

	public PageInfo<SysUserBean> queryBeans(Map<String, Object> params);

	public String encryptPassword(String password);

	/** 查询第三方帐号用户Id */
	public Integer queryUserIdByThirdParty(String openId, String provider);

	/** 保存第三方帐号 */
	public SysUser insertThirdPartyUser(ThirdPartyUser thirdPartyUser);
}
