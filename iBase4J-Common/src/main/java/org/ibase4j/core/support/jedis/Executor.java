package org.ibase4j.core.support.jedis;

import redis.clients.jedis.ShardedJedis;

public interface Executor<K> {
	public K execute(ShardedJedis jedis);
}
