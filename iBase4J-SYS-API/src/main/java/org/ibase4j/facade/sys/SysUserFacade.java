/**
 * 
 */
package org.ibase4j.facade.sys;

import java.util.Map;

import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.mybatis.sys.model.ThirdPartyUser;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:47
 */
public interface SysUserFacade {
	public void update(SysUser record);

	public void delete(Integer id);

	public SysUser queryById(Integer id);

	public PageInfo<SysUser> query(Map<String, Object> params);

	public String encryptPassword(String password);

	/** 查询第三方帐号用户Id */
	public String queryUserIdByThirdParty(String openId, String provider);

	/** 保存第三方帐号 */
	public SysUser insertThirdPartyUser(ThirdPartyUser thirdPartyUser);
}
