/**
 * 
 */
package org.ibase4j.provider.sys;

import java.util.Date;
import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.dao.generator.SysUserThirdpartyMapper;
import org.ibase4j.dao.sys.SysUserExpandMapper;
import org.ibase4j.model.generator.SysUser;
import org.ibase4j.model.generator.SysUserThirdparty;
import org.ibase4j.model.sys.SysUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@DubboService(interfaceClass = SysUserProvider.class)
@CacheConfig(cacheNames = "sysUser")
public class SysUserProviderImpl extends BaseProviderImpl<SysUser> implements SysUserProvider {
	@Autowired
	private SysUserExpandMapper sysUserExpandMapper;
	@Autowired
	private SysUserThirdpartyMapper thirdpartyMapper;
	@Autowired
	private SysDicProvider sysDicProvider;
	@Autowired
	private SysDeptProvider sysDeptProvider;

	public PageInfo<SysUser> query(Map<String, Object> params) {
		this.startPage(params);
		return getPage(sysUserExpandMapper.query(params));
	}

	public PageInfo<SysUserBean> queryBeans(Map<String, Object> params) {
		this.startPage(params);
		Page<String> userIds = sysUserExpandMapper.query(params);
		Map<String, String> userTypeMap = sysDicProvider.queryDicByDicIndexKey("USERTYPE");
		PageInfo<SysUserBean> pageInfo = getPage(userIds, SysUserBean.class);
		for (SysUserBean userBean : pageInfo.getList()) {
			if (userBean.getUserType() != null) {
				userBean.setUserTypeText(userTypeMap.get(userBean.getUserType().toString()));
			}
			if (userBean.getDeptId() != null) {
				userBean.setDeptName(sysDeptProvider.queryById(userBean.getDeptId()).getDeptName());
			}
		}
		return pageInfo;
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
        thirdparty.setId(createId("SysUserThirdparty"));
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
