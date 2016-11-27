/**
 * 
 */
package org.ibase4j.provider.sys;

import java.util.List;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.model.sys.SysSession;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:21
 */
public interface ISysSessionProvider extends BaseProvider<SysSession> {
	@Transactional
	public void deleteBySessionId(final String sessionId);

	public List<String> querySessionIdByAccount(String account);

	public void delete(Long id);
	
	public void cleanExpiredSessions();
}
