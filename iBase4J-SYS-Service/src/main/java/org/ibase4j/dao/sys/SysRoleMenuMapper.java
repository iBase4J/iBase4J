package org.ibase4j.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.model.sys.SysRoleMenu;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    List<String> queryPermission(@Param("roleId") Long id);

    List<String> getPermissions(@Param("roleId") Long id);
}
