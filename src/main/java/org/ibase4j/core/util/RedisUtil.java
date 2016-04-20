package org.ibase4j.core.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ibase4j.core.support.jedis.Executor;
import org.ibase4j.core.support.jedis.JedisTemplate;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

/**
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:17:22
 */
public class RedisUtil {
	private RedisUtil() {
	}

	public static String get(String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public static String set(String key, Integer seconds, Object value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, value.toString());
			}
		}, true);
	}

	public Boolean exists(String key) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	public static Long del(String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.del(key);
			}
		});
	}

	public static String type(String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.type(key);
			}
		});
	}

	/**
	 * 在某段时间后失效
	 * 
	 * @return
	 */
	public static Long expire(String key, int seconds) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expire(key, seconds);
			}
		}, true);
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public static Long expireAt(String key, long unixTime) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expireAt(key, unixTime);
			}
		});
	}

	public static Long ttl(String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.ttl(key);
			}
		});
	}

	public static Boolean setbit(String key, long offset, boolean value) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.setbit(key, offset, value);
			}
		});
	}

	public static Boolean getbit(String key, long offset) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.getbit(key, offset);
			}
		});
	}

	public static Long setrange(String key, long offset, String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setrange(key, offset, value);
			}
		});
	}

	public static String getrange(String key, long startOffset, long endOffset) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.getrange(key, startOffset, endOffset);
			}
		});
	}

	public static String getSet(String key, String value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.getSet(key, value);
			}
		});
	}

	public static Long setnx(String key, String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	public String setex(String key, int seconds, String value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		}, true);
	}

	public Long decrBy(String key, long integer) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decrBy(key, integer);
			}
		});
	}

	public Long decr(String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public Long incrBy(String key, long integer) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incrBy(key, integer);
			}
		});
	}

	public Long incr(String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public Long append(String key, String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.append(key, value);
			}
		});
	}

	public String substr(String key, int start, int end) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.substr(key, start, end);
			}
		});
	}

	public Long hset(String key, String field, String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public String hget(String key, String field) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public Long hsetnx(String key, String field, String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hsetnx(key, field, value);
			}
		});
	}

	public String hmset(String key, Map<String, String> hash) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public List<String> hmget(String key, String... fields) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public Long hincrBy(String key, String field, long value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hincrBy(key, field, value);
			}
		});
	}

	public Boolean hexists(String key, String field) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.hexists(key, field);
			}
		});
	}

	public Long hdel(String key, String field) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hdel(key, field);
			}
		});
	}

	public Long hlen(String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hlen(key);
			}
		});
	}

	public Set<String> hkeys(String key) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public List<String> hvals(String key) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.hvals(key);
			}
		});
	}

	public Map<String, String> hgetAll(String key) {
		return JedisTemplate.run(key, new Executor<Map<String, String>>() {
			public Map<String, String> execute(ShardedJedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	// ================list ====== l表示 list或 left, r表示right====================
	public Long rpush(String key, String string) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.rpush(key, string);
			}
		});
	}

	public Long lpush(String key, String string) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lpush(key, string);
			}
		});
	}

	public Long llen(String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	public List<String> lrange(String key, long start, long end) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public String ltrim(String key, long start, long end) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.ltrim(key, start, end);
			}
		});
	}

	public String lindex(String key, long index) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lindex(key, index);
			}
		});
	}

	public String lset(String key, long index, String value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lset(key, index, value);
			}
		});
	}

	public Long lrem(String key, long count, String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lrem(key, count, value);
			}
		});
	}

	public String lpop(String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	public String rpop(String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	// return 1 add a not exist value ,
	// return 0 add a exist value
	public Long sadd(String key, String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.sadd(key, member);
			}
		});
	}

	public Set<String> smembers(String key) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	public Long srem(String key, String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.srem(key, member);
			}
		});
	}

	public String spop(String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.spop(key);
			}
		});
	}

	public Long scard(String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.scard(key);
			}
		});
	}

	public Boolean sismember(String key, String member) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}

	public String srandmember(String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.srandmember(key);
			}
		});
	}

	public Long zadd(String key, double score, String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	public Set<String> zrange(String key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	public Long zrem(String key, String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	public Double zincrby(String key, double score, String member) {
		return JedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zincrby(key, score, member);
			}
		});
	}

	public Long zrank(String key, String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	public Long zrevrank(String key, String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	public Set<String> zrevrange(String key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	public Set<Tuple> zrangeWithScores(String key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeWithScores(key, start, end);
			}
		});
	}

	public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	public Long zcard(String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcard(key);
			}
		});
	}

	public Double zscore(String key, String member) {
		return JedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	public List<String> sort(String key) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.sort(key);
			}
		});
	}

	public List<String> sort(String key, SortingParams sortingParameters) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.sort(key, sortingParameters);
			}
		});
	}

	public Long zcount(String key, double min, double max) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	public Set<String> zrangeByScore(String key, double min, double max) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	public Set<String> zrevrangeByScore(String key, double max, double min) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	public Long zremrangeByRank(String key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByRank(key, start, end);
			}
		});
	}

	public Long zremrangeByScore(String key, double start, double end) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.linsert(key, where, pivot, value);
			}
		});
	}

	public String set(byte[] key, byte[] value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	public byte[] get(byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public Boolean exists(byte[] key) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	public String type(byte[] key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.type(key);
			}
		});
	}

	public Long expire(byte[] key, int seconds) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expire(key, seconds);
			}
		}, true);
	}

	public Long expireAt(byte[] key, long unixTime) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expireAt(key, unixTime);
			}
		});
	}

	public Long ttl(byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.ttl(key);
			}
		});
	}

	public byte[] getSet(byte[] key, byte[] value) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.getSet(key, value);
			}
		});
	}

	public Long setnx(byte[] key, byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	public String setex(byte[] key, int seconds, byte[] value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		}, true);
	}

	public Long decrBy(byte[] key, long integer) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decrBy(key, integer);
			}
		});
	}

	public Long decr(byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public Long incrBy(byte[] key, long integer) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incrBy(key, integer);
			}
		});
	}

	public Long incr(byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public Long append(byte[] key, byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.append(key, value);
			}
		});
	}

	public byte[] substr(byte[] key, int start, int end) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.substr(key, start, end);
			}
		});
	}

	public Long hset(byte[] key, byte[] field, byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public byte[] hget(byte[] key, byte[] field) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public Long hsetnx(byte[] key, byte[] field, byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hsetnx(key, field, value);
			}
		});
	}

	public String hmset(byte[] key, Map<byte[], byte[]> hash) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public List<byte[]> hmget(byte[] key, byte[]... fields) {
		return JedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public Long hincrBy(byte[] key, byte[] field, long value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hincrBy(key, field, value);
			}
		});
	}

	public Boolean hexists(byte[] key, byte[] field) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.hexists(key, field);
			}
		});
	}

	public Long hdel(byte[] key, byte[] field) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hdel(key, field);
			}
		});
	}

	public Long hlen(byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hlen(key);
			}
		});
	}

	public Set<byte[]> hkeys(byte[] key) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public Collection<byte[]> hvals(byte[] key) {
		return JedisTemplate.run(key, new Executor<Collection<byte[]>>() {
			public Collection<byte[]> execute(ShardedJedis jedis) {
				return jedis.hvals(key);
			}
		});
	}

	public Map<byte[], byte[]> hgetAll(byte[] key) {
		return JedisTemplate.run(key, new Executor<Map<byte[], byte[]>>() {
			public Map<byte[], byte[]> execute(ShardedJedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public Long rpush(byte[] key, byte[] string) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.rpush(key, string);
			}
		});
	}

	public Long lpush(byte[] key, byte[] string) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lpush(key, string);
			}
		});
	}

	public Long llen(byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	public List<byte[]> lrange(byte[] key, int start, int end) {
		return JedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public String ltrim(byte[] key, int start, int end) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.ltrim(key, start, end);
			}
		});
	}

	public byte[] lindex(byte[] key, int index) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.lindex(key, index);
			}
		});
	}

	public String lset(byte[] key, int index, byte[] value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lset(key, index, value);
			}
		});
	}

	public Long lrem(byte[] key, int count, byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lrem(key, count, value);
			}
		});
	}

	public byte[] lpop(byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	public byte[] rpop(byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	public Long sadd(byte[] key, byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.sadd(key, member);
			}
		});
	}

	public Set<byte[]> smembers(byte[] key) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	public Long srem(byte[] key, byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.srem(key, member);
			}
		});
	}

	public byte[] spop(byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.spop(key);
			}
		});
	}

	public Long scard(byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.scard(key);
			}
		});
	}

	public Boolean sismember(byte[] key, byte[] member) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}

	public byte[] srandmember(byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.srandmember(key);
			}
		});
	}

	public Long zadd(byte[] key, double score, byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	public Set<byte[]> zrange(byte[] key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	public Long zrem(byte[] key, byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	public Double zincrby(byte[] key, double score, byte[] member) {
		return JedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zincrby(key, score, member);
			}
		});
	}

	public Long zrank(byte[] key, byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	public Long zrevrank(byte[] key, byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	public Set<byte[]> zrevrange(byte[] key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	public Set<Tuple> zrangeWithScores(byte[] key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeWithScores(key, start, end);
			}
		});
	}

	public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	public Long zcard(byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcard(key);
			}
		});
	}

	public Double zscore(byte[] key, byte[] member) {
		return JedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	public List<byte[]> sort(byte[] key) {
		return JedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.sort(key);
			}
		});
	}

	public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
		return JedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.sort(key, sortingParameters);
			}
		});
	}

	public Long zcount(byte[] key, double min, double max) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	public Long zremrangeByRank(byte[] key, int start, int end) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByRank(key, start, end);
			}
		});
	}

	public Long zremrangeByScore(byte[] key, double start, double end) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	public Long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.linsert(key, where, pivot, value);
			}
		});
	}

	public Jedis getShard(byte[] key) {
		return JedisTemplate.run(key, new Executor<Jedis>() {
			public Jedis execute(ShardedJedis jedis) {
				return jedis.getShard(key);
			}
		});
	}

	public Jedis getShard(String key) {
		return JedisTemplate.run(key, new Executor<Jedis>() {
			public Jedis execute(ShardedJedis jedis) {
				return jedis.getShard(key);
			}
		});
	}

	public JedisShardInfo getShardInfo(byte[] key) {
		return JedisTemplate.run(key, new Executor<JedisShardInfo>() {
			public JedisShardInfo execute(ShardedJedis jedis) {
				return jedis.getShardInfo(key);
			}
		});
	}

	public JedisShardInfo getShardInfo(String key) {
		return JedisTemplate.run(key, new Executor<JedisShardInfo>() {
			public JedisShardInfo execute(ShardedJedis jedis) {
				return jedis.getShardInfo(key);
			}
		});
	}

	public String getKeyTag(String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.getKeyTag(key);
			}
		});
	}

	public Collection<JedisShardInfo> getAllShardInfo() {
		return JedisTemplate.run("", new Executor<Collection<JedisShardInfo>>() {
			public Collection<JedisShardInfo> execute(ShardedJedis jedis) {
				return jedis.getAllShardInfo();
			}
		});
	}

	public Collection<Jedis> getAllShards() {
		return JedisTemplate.run("", new Executor<Collection<Jedis>>() {
			public Collection<Jedis> execute(ShardedJedis jedis) {
				return jedis.getAllShards();
			}
		});
	}
}
