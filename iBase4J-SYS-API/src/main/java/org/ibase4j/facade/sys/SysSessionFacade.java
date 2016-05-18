/**
 * 
 */
package org.ibase4j.facade.sys;

import org.ibase4j.core.support.dubbo.BaseFacade;
import org.ibase4j.mybatis.generator.model.SysSession;

/**
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:21
 */
public interface SysSessionFacade extends BaseFacade<SysSession> {

	public void deleteBySessionId(final String sessionId);
}
