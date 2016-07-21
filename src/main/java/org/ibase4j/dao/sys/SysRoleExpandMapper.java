package org.ibase4j.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.dao.BaseExpandMapper;

public interface SysRoleExpandMapper extends BaseExpandMapper {

	List<String> queryPermission(@Param("roleId") Integer id);

}
