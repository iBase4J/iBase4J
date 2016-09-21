package org.ibase4j.provider.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.sys.SysSessionMapper;
import org.ibase4j.model.sys.SysSession;
import org.ibase4j.provider.sys.ISysSessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysSession")
@DubboService(interfaceClass = ISysSessionProvider.class)
public class SysSessionProviderImpl extends BaseProviderImpl<SysSession> implements ISysSessionProvider {
    @Autowired
    private SysSessionMapper sessionMapper;

    @CachePut
    @Transactional
    public SysSession update(SysSession record) {
        if (record.getId() == null) {
            record.setUpdateBy(record.getAccount());
            record.setUpdateTime(new Date());
            String id = sessionMapper.queryBySessionId(record.getSessionId());
            if (StringUtils.isNotBlank(id)) {
                record.setId(id);
                mapper.updateById(record);
            } else {
                record.setId(createId("SysSession"));
                record.setCreateBy(record.getAccount());
                record.setCreateTime(new Date());
                mapper.insert(record);
            }
        } else {
            mapper.updateById(record);
        }
        return record;
    }

    @CacheEvict
    public void delete(final String id) {
        mapper.deleteById(id);
    }

    // 系统触发,由系统自动管理缓存
    public void deleteBySessionId(final String sessionId) {
        sessionMapper.deleteBySessionId(sessionId);
    }

    public Page<SysSession> query(Map<String, Object> params) {
        Page<String> page = this.getPage(params);
        page.setRecords(mapper.selectIdByMap(page, params));
        return getPage(page);
    }

    public List<String> querySessionIdByAccount(String account) {
        return sessionMapper.querySessionIdByAccount(account);
    }
}
