package org.ibase4j.mapper;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.model.SysUserThirdparty;

import top.ibase4j.core.base.BaseMapper;

public interface SysUserThirdpartyMapper extends BaseMapper<SysUserThirdparty> {
	Long queryUserIdByThirdParty(@Param("provider") String provider, @Param("openId") String openId);
}