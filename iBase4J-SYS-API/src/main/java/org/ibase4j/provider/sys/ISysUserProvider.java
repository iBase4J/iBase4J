package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.model.sys.SysUser;
import org.ibase4j.model.sys.ext.SysUserBean;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * SysUser服务接口
 * @author ShenHuaJie
 * @version 2016-08-27 22:39:42
 */
public interface ISysUserProvider extends BaseProvider<SysUser> {

    public Page<SysUserBean> queryBeans(Map<String, Object> params);

    public String encryptPassword(String password);

    /** 查询第三方帐号用户Id */
    public String queryUserIdByThirdParty(String openId, String provider);

    /** 保存第三方帐号 */
    public SysUser insertThirdPartyUser(ThirdPartyUser thirdPartyUser);

    /**
     * 加载所有用户信息
     */
    public void init();
}
