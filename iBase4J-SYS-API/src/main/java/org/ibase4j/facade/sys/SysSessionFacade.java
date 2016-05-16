/**
 * 
 */
package org.ibase4j.facade.sys;

import java.util.Map;

import org.ibase4j.mybatis.generator.model.SysSession;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:21
 */
public interface SysSessionFacade {

	public void update(SysSession record);

	public void delete(Integer id);

	public void deleteBySessionId(final String sessionId);

	public PageInfo<SysSession> query(Map<String, Object> params);
}
