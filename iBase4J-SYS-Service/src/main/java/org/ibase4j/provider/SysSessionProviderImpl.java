package org.ibase4j.provider;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.transaction.annotation.Transactional;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.util.CacheUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.ibase4j.mapper.SysSessionMapper;
import org.ibase4j.model.SysSession;
import org.ibase4j.provider.ISysSessionProvider;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysSession")
@DubboService(interfaceClass = ISysSessionProvider.class)
public class SysSessionProviderImpl extends BaseProviderImpl<SysSession> implements ISysSessionProvider {

	@CachePut
	@Transactional
	public SysSession update(SysSession record) {
		if (record.getId() == null) {
			record.setUpdateTime(new Date());
			Long id = ((SysSessionMapper) mapper).queryBySessionId(record.getSessionId());
			if (id != null) {
				mapper.updateById(record);
			} else {
				record.setCreateTime(new Date());
				mapper.insert(record);
			}
		} else {
			mapper.updateById(record);
		}
		return record;
	}

	@CacheEvict
	public void delete(final Long id) {
		mapper.deleteById(id);
	}

	// 系统触发,由系统自动管理缓存
	public void deleteBySessionId(final String sessionId) {
		((SysSessionMapper) mapper).deleteBySessionId(sessionId);
	}

	public List<String> querySessionIdByAccount(String account) {
		return ((SysSessionMapper) mapper).querySessionIdByAccount(account);
	}

	//
	public void cleanExpiredSessions() {
		String key = "spring:session:" + PropertiesUtil.getString("session.redis.namespace") + ":sessions:expires:";
		Map<String, Object> columnMap = InstanceUtil.newHashMap();
		List<Long> ids = mapper.selectIdPage(new RowBounds(), columnMap);
		List<SysSession> sessions = getList(ids);
		for (SysSession sysSession : sessions) {
			logger.info("检查SESSION : {}", sysSession.getSessionId());
			if (!CacheUtil.getCache().exists(key + sysSession.getSessionId())) {
				logger.info("移除SESSION : {}", sysSession.getSessionId());
				mapper.deleteById(sysSession.getId());
			}
		}
	}
}