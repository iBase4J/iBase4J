/**
 * 
 */
package org.shjr.iplat.mybatis.sys.dao;

import java.util.List;
import java.util.Map;

/**
 * @author ShenHuaJie
 */
public interface SysUserExpandMapper {

	List<Map<String, Object>> query(Map<String, Object> params);

}
