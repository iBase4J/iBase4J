/**
 * 
 */
package org.ibase4j.mybatis.sys.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.mybatis.generator.model.SysUser;

import com.github.pagehelper.Page;

/**
 * @author ShenHuaJie
 */
public interface SysUserExpandMapper {

	Page<SysUser> query(Map<String, Object> params);

	String queryUserIdByThirdParty(@Param("provider") String provider, @Param("openId") String openId);
}
