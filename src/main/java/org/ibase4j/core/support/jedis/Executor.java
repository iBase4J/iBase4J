package org.ibase4j.core.support.jedis;

import redis.clients.jedis.ShardedJedis;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public interface Executor<K> {
	public K execute(ShardedJedis jedis);
}
