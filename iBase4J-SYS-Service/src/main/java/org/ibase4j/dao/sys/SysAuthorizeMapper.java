package org.ibase4j.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysAuthorizeMapper {

	void deleteUserMenu(Long userId);

	void deleteUserRole(Long userId);

	void deleteRoleMenu(Long roleId);

	List<Long> getAuthorize(Long userId);

	List<String> queryPermissionByUserId(@Param("userId") Long userId);

}
