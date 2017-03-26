package org.ibase4j.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.util.CacheUtil;
import org.springframework.stereotype.Service;

@Service
public class SysCacheService {
    Logger logger = LogManager.getLogger();

    // 清缓存
    public void flush() {
        logger.info("清缓存开始......");
        CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*");
        logger.info("清缓存结束!");
    }

    // 清缓存
    public void flush(Map<String, String> param) {
        String key = param.get("key");
        logger.info("清缓存[{}]开始......", key);
        CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*" + key + "*");
        logger.info("清缓存[{}]结束!", key);
    }
}
