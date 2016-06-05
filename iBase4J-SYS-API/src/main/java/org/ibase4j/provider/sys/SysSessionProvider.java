/**
 * 
 */
package org.ibase4j.provider.sys;

import org.ibase4j.core.support.dubbo.BaseProvider;
import org.ibase4j.mybatis.generator.model.SysSession;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:21
 */
public interface SysSessionProvider extends BaseProvider<SysSession> {
	@Transactional
	public void deleteBySessionId(final String sessionId);
}
