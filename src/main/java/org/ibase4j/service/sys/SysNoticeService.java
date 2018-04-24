package org.ibase4j.service.sys;

import org.ibase4j.mapper.sys.SysNoticeMapper;
import org.ibase4j.model.sys.SysNotice;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 *
 */
@Service
@CacheConfig(cacheNames = "sysNotice")
public class SysNoticeService extends BaseService<SysNotice, SysNoticeMapper> {

}
