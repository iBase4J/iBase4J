package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.Assert;
import org.ibase4j.dao.generator.SysSessionMapper;
import org.ibase4j.dao.sys.SysSessionExpandMapper;
import org.ibase4j.model.generator.SysSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Service
@CacheConfig(cacheNames = "sysSession")
public class SysSessionService extends BaseService<SysSession> {
    @Autowired
    private SysSessionMapper sessionMapper;
    @Autowired
    private SysSessionExpandMapper sessionExpandMapper;
    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

    /** 删除会话 */
    public void deleteByAccount(String account) {
        Assert.notNull(account, "ACCOUNT");
        List<String> sessionIds = sessionExpandMapper.querySessionIdByAccount(account);
        if (sessionIds != null) {
            for (String sessionId : sessionIds) {
                sessionRepository.delete(sessionId);
                sessionRepository.cleanupExpiredSessions();
                sessionExpandMapper.deleteBySessionId(sessionId);
            }
        }
    }

    /** 删除会话 */
    public void delete(Integer id) {
        Assert.notNull(id, "ID");
        SysSession sysSession = sessionMapper.selectByPrimaryKey(id);
        if (sysSession != null) {
            sessionRepository.delete(sysSession.getSessionId());
            sessionRepository.cleanupExpiredSessions();
            sessionMapper.deleteByPrimaryKey(id);
        }
    }

    @CacheEvict
    public void deleteBySessionId(final String sessionId) {
        sessionExpandMapper.deleteBySessionId(sessionId);
    }

    /** 更新会话 */
    public SysSession update(SysSession record) {
        if (record.getId() == null) {
            Integer id = sessionExpandMapper.queryBySessionId(record.getSessionId());
            if (id != null) {
                record.setId(id);
                sessionMapper.updateByPrimaryKey(record);
            } else {
                sessionMapper.insert(record);
            }
        } else {
            sessionMapper.updateByPrimaryKey(record);
        }
        return record;
    }

    @Cacheable
    public PageInfo<SysSession> query(Map<String, Object> params) {
        this.startPage(params);
        Page<Integer> ids = sessionExpandMapper.query(params);
        return new PageInfo<SysSession>(getList(ids));
    }
}
