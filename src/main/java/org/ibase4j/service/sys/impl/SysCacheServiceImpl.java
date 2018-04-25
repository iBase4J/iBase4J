package org.ibase4j.service.sys.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.sys.ISysCacheService;
import org.springframework.stereotype.Service;

import top.ibase4j.core.Constants;
import top.ibase4j.core.util.CacheUtil;

@Service
public class SysCacheServiceImpl implements ISysCacheService {
    Logger logger = LogManager.getLogger();

    // 清缓存
    @Override
    public void flush() {
        logger.info("清缓存开始......");
        CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*");
        logger.info("清缓存结束!");
    }

    // 清缓存
    @Override
    public void flush(Map<String, String> param) {
        String key = param.get("key");
        logger.info("清缓存[{}]开始......", key);
        CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*" + key + "*");
        if (key.contains("Permission")) {
            CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*shiro_redis_cache*");
        }
        logger.info("清缓存[{}]结束!", key);
    }
}
