package org.ibase4j.dao.sys;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.base.BaseExpandMapper;

public interface SysPermissionExpandMapper extends BaseExpandMapper {

	Integer getPermissionByUserId(@Param("userId") Integer userId, @Param("url") String url);
}
