package org.ibase4j.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysAuthorizeMapper {

	void deleteUserMenu(String userId);

	void deleteUserRole(String userId);

	void deleteRoleMenu(String roleId);

	List<String> getAuthorize(String userId);

	List<String> queryPermissionByUserId(@Param("userId") String userId);

}
