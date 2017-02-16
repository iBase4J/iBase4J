package org.ibase4j.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.model.sys.SysRoleMenu;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	List<Long> queryMenuIdsByRoleId(@Param("roleId") Long roleId);

	List<Long> queryPermissions(@Param("roleId") Long roleId, @Param("permission") String permission);

	List<String> queryPermission(@Param("roleId") Long id);
}
