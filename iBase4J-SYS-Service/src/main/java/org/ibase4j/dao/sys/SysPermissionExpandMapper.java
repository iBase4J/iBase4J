package org.ibase4j.dao.sys;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.base.BaseMapper;

public interface SysPermissionExpandMapper extends BaseMapper {

	Integer getPermissionByUserId(@Param("userId") Integer userId, @Param("url") String url);
}
