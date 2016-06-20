package org.ibase4j.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysAuthorizeMapper {

	void deleteUserMenu(Integer userId);

	void deleteUserRole(Integer userId);

	void deleteRoleMenu(Integer roleId);

	List<Integer> getAuthorize(Integer userId);

	List<String> queryPermissionByUserId(@Param("userId") Integer userId);

}
