package org.ibase4j.mybatis.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysPermissionExpandMapper {
	List<String> getPermissionTypesByUserId(Integer userId);

	Integer getPermissionByUserId(@Param("userId") Integer userId, @Param("url") String url);
}
