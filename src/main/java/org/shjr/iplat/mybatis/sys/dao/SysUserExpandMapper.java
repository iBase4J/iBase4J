/**
 * 
 */
package org.shjr.iplat.mybatis.sys.dao;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * @author ShenHuaJie
 */
public interface SysUserExpandMapper {

	Page<Map<String, Object>> query(Map<String, Object> params);

}
