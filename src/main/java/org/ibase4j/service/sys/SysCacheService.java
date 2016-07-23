package org.ibase4j.service.sys;

import org.ibase4j.core.util.RedisUtil;
import org.springframework.stereotype.Service;

@Service
public class SysCacheService {

    // 清缓存
    public void flushCache() {
        RedisUtil.delAll("*:sysDics:*");
        RedisUtil.delAll("*:sysDicMap:*");
        RedisUtil.delAll("*:getAuthorize:*");
        RedisUtil.delAll("*:sysPermission:*");
    }
}
