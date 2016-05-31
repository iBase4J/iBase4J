/**
 * 
 */
package org.ibase4j.mybatis.sys.dao;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.support.BaseMapper;

/**
 * @author ShenHuaJie
 */
public interface SysUserExpandMapper extends BaseMapper {

	String queryUserIdByThirdParty(@Param("provider") String provider, @Param("openId") String openId);
}
