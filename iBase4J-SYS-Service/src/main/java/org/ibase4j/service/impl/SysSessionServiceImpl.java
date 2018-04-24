package org.ibase4j.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ibase4j.mapper.SysSessionMapper;
import org.ibase4j.model.SysSession;
import org.ibase4j.service.ISysSessionService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.Constants;
import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.util.CacheUtil;
import top.ibase4j.core.util.InstanceUtil;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysSession")
@Service(interfaceClass = ISysSessionService.class)
@MotanService(interfaceClass = ISysSessionService.class)
public class SysSessionServiceImpl extends BaseService<SysSession, SysSessionMapper> implements ISysSessionService {

    @CachePut
    @Transactional
    public SysSession update(SysSession record) {
        if (record != null && record.getId() == null) {
            record.setUpdateTime(new Date());
            Long id = ((SysSessionMapper)mapper).queryBySessionId(record.getSessionId());
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

    // 系统触发,由系统自动管理缓存
    public void deleteBySessionId(final SysSession sysSession) {
        if (sysSession != null) {
            ((SysSessionMapper)mapper).deleteBySessionId(sysSession.getSessionId());
        }
    }

    public List<String> querySessionIdByAccount(SysSession sysSession) {
        if (sysSession != null) {
            return ((SysSessionMapper)mapper).querySessionIdByAccount(sysSession.getAccount());
        }
        return null;
    }

    //
    public void cleanExpiredSessions() {
        Map<String, Object> columnMap = InstanceUtil.newHashMap();
        List<SysSession> sessions = queryList(columnMap);
        for (SysSession sysSession : sessions) {
            if (sysSession != null) {
                logger.info("检查SESSION : {}", sysSession.getSessionId());
                if (!CacheUtil.getCache().exists(Constants.REDIS_SHIRO_SESSION + sysSession.getSessionId())) {
                    logger.info("移除SESSION : {}", sysSession.getSessionId());
                    delete(sysSession.getId());
                }
            }
        }
    }
}
