package org.shjr.iplat.mybatis.sys.dao;

import java.util.List;

import org.shjr.iplat.mybatis.generator.model.SysMenu;

public interface SysAuthorizeMapper {

	void deleteUserMenu(Integer userId);

	void deleteUserRole(Integer userId);

	void deleteRoleMenu(Integer roleId);

	List<SysMenu> getAuthorize(Integer userId);

}
