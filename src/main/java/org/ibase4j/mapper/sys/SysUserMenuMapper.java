package org.ibase4j.mapper.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.model.sys.SysUserMenu;

import top.ibase4j.core.base.BaseMapper;

public interface SysUserMenuMapper extends BaseMapper<SysUserMenu> {
	List<Long> queryMenuIdsByUserId(@Param("userId") Long userId);

	List<Map<String, Object>> queryPermissions(@Param("userId") Long userId);

	List<String> queryPermission(@Param("userId") Long id);
}