package org.ibase4j.service.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.service.ISysCacheService;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.Constants;
import top.ibase4j.core.util.CacheUtil;

@Service(interfaceClass = ISysCacheService.class)
@MotanService(interfaceClass = ISysCacheService.class)
public class SysCacheServiceImpl implements ISysCacheService {
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
        if (key.contains("Permission")) {
            CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*shiro_redis_cache*");
        }
        logger.info("清缓存[{}]结束!", key);
    }
}
