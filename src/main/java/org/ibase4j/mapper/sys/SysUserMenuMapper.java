package org.ibase4j.mapper.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.model.sys.SysUserMenu;

import top.ibase4j.core.base.BaseMapper;

/**
 * @author ShenHuaJie
 * @since 2018年3月3日 下午7:25:00
 */
public interface SysUserMenuMapper extends BaseMapper<SysUserMenu> {
	List<Long> queryMenuIdsByUserId(@Param("userId") Long userId);

	List<Map<String, Object>> queryPermissions(@Param("userId") Long userId);

	List<String> queryPermission(@Param("userId") Long id);
}