package org.ibase4j.mybatis.sys.dao;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.support.BaseMapper;

public interface SysPermissionExpandMapper extends BaseMapper {

	Integer getPermissionByUserId(@Param("userId") Integer userId, @Param("url") String url);
}
