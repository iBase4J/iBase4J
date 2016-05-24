package org.ibase4j.mybatis.sys.dao;

import java.util.List;

public interface SysAuthorizeMapper {

	void deleteUserMenu(Integer userId);

	void deleteUserRole(Integer userId);

	void deleteRoleMenu(Integer roleId);

	List<Integer> getAuthorize(Integer userId);

}
