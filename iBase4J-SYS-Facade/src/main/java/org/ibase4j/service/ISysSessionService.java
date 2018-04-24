/**
 * 
 */
package org.ibase4j.service;

import java.util.List;

import org.ibase4j.model.SysSession;

import top.ibase4j.core.base.IBaseService;

/**
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:21
 */
public interface ISysSessionService extends IBaseService<SysSession> {
    public void deleteBySessionId(final SysSession sysSession);

    public List<String> querySessionIdByAccount(SysSession sysSession);

	public void cleanExpiredSessions();
}
