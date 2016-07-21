/**
 * 
 */
package org.ibase4j.dao.sys;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.dao.BaseExpandMapper;

/**
 * @author ShenHuaJie
 */
public interface SysUserExpandMapper extends BaseExpandMapper {

	String queryUserIdByThirdParty(@Param("provider") String provider, @Param("openId") String openId);
}
