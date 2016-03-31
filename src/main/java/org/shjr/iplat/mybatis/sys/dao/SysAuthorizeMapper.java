package org.shjr.iplat.mybatis.sys.dao;

import java.util.List;

import org.shjr.iplat.mybatis.generator.model.SysMenu;

public interface SysAuthorizeMapper {

	void deleteUserMenu(Integer user_id);

	void deleteUserRole(Integer user_id);

	void deleteRoleMenu(Integer role_id);

	List<SysMenu> getAuthorize(Integer user_id);

}
