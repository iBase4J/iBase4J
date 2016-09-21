package org.ibase4j.dao.sys;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.model.sys.SysUserThirdparty;

public interface SysUserThirdpartyMapper extends BaseMapper<SysUserThirdparty> {
    String queryUserIdByThirdParty(@Param("provider") String provider, @Param("openId") String openId);
}