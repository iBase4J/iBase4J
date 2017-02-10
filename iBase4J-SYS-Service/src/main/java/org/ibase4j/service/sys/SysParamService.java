package org.ibase4j.service.sys;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.sys.SysParam;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@Service
@CacheConfig(cacheNames = "sysParam")
public class SysParamService extends BaseService<SysParam> {

}
