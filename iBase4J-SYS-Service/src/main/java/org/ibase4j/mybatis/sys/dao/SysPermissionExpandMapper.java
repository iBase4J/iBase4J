package org.ibase4j.mybatis.sys.dao;

import org.apache.ibatis.annotations.Param;

public interface SysPermissionExpandMapper {

	Integer getPermissionByUserId(@Param("userId") Integer userId, @Param("url") String url);
}
