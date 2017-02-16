package org.ibase4j.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.model.SysMenu;

public interface SysAuthorizeMapper {

	void deleteUserMenu(@Param("userId") Long userId, @Param("permission") String permission);

	void deleteUserRole(@Param("userId") Long userId);

	void deleteRoleMenu(@Param("roleId") Long roleId, @Param("permission") String permission);

	List<Long> getAuthorize(@Param("userId") Long userId);

	List<String> queryPermissionByUserId(@Param("userId") Long userId);

	List<SysMenu> queryMenusPermission();
}
