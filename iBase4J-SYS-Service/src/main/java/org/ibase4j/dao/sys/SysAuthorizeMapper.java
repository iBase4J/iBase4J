package org.ibase4j.dao.sys;

import java.util.List;

public interface SysAuthorizeMapper {

	void deleteUserMenu(Integer userId);

	void deleteUserRole(Integer userId);

	void deleteRoleMenu(Integer roleId);

	List<Integer> getAuthorize(Integer userId);

}
