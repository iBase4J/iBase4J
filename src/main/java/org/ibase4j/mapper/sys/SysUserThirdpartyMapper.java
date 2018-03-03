package org.ibase4j.mapper.sys;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.model.sys.SysUserThirdparty;

import top.ibase4j.core.base.BaseMapper;

/**
 * @author ShenHuaJie
 * @since 2018年3月3日 下午7:25:14
 */
public interface SysUserThirdpartyMapper extends BaseMapper<SysUserThirdparty> {
	Long queryUserIdByThirdParty(@Param("provider") String provider, @Param("openId") String openId);
}