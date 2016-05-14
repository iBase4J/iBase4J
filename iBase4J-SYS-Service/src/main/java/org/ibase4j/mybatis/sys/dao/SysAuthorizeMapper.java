package org.ibase4j.mybatis.sys.dao;

import java.util.List;

import org.ibase4j.mybatis.sys.model.SysMenuBean;

public interface SysAuthorizeMapper {

	void deleteUserMenu(Integer userId);

	void deleteUserRole(Integer userId);

	void deleteRoleMenu(Integer roleId);

	List<SysMenuBean> getAuthorize(Integer userId);

}
