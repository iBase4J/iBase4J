package org.ibase4j.service;

import java.util.Map;

/**
 * @author ShenHuaJie
 * @version 2016年6月27日 下午3:16:11
 */
public interface SysCacheService {
    /** 清除缓存 */
    void flush();

    /** 清除缓存 */
    void flush(Map<String, String> param);
}
