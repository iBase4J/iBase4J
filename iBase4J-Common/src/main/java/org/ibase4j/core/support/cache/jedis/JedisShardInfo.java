/**
 * 
 */
package org.ibase4j.core.support.cache.jedis;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author ShenHuaJie
 * @version 2017年3月27日 下午12:47:53
 */
public class JedisShardInfo extends redis.clients.jedis.JedisShardInfo {

    public JedisShardInfo(String host, int port) {
        super(host, port);
    }
    
    public void setPassword(String password) {
        if (StringUtils.isNotBlank(password)) {
            super.setPassword(password);
        }
    }
}
