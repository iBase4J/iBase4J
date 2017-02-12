package org.ibase4j.service.sys;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.util.CacheUtil;
import org.springframework.stereotype.Service;

@Service
public class SysCacheService {
	Logger logger = LogManager.getLogger();

	// 清缓存
	public void flush() {
		logger.info("清缓存开始......");
		CacheUtil.getCache().delAll("*:sysDics:*");
		CacheUtil.getCache().delAll("*:sysDicMap:*");
		CacheUtil.getCache().delAll("*:getAuthorize:*");
		CacheUtil.getCache().delAll("*:sysPermission:*");
		logger.info("清缓存结束!");
	}
}