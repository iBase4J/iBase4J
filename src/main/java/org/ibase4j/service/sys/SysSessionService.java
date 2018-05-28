/**
 *
 */
package org.ibase4j.service.sys;

import java.util.List;

import org.ibase4j.model.sys.SysSession;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:21
 */
public interface SysSessionService extends BaseService<SysSession> {
    void deleteBySessionId(final SysSession sysSession);

    List<String> querySessionIdByAccount(SysSession sysSession);

    void cleanExpiredSessions();
}
