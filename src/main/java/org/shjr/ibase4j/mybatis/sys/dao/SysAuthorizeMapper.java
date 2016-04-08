package org.shjr.ibase4j.mybatis.sys.dao;

import java.util.List;

import org.shjr.ibase4j.mybatis.generator.model.SysMenu;

public interface SysAuthorizeMapper {

	void deleteUserMenu(Integer userId);

	void deleteUserRole(Integer userId);

	void deleteRoleMenu(Integer roleId);

	List<SysMenu> getAuthorize(Integer userId);

}
