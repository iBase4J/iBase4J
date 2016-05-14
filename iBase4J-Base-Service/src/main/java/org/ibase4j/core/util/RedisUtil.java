package org.ibase4j.core.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ibase4j.core.support.jedis.Executor;
import org.ibase4j.core.support.jedis.JedisTemplate;

import com.alibaba.fastjson.JSON;

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

	public static String get(final String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public static String set(final String key, final Integer seconds, final Object value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, JSON.toJSONString(value));
			}
		}, true);
	}

	public static Boolean exists(final String key) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	public static Long del(final String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.del(key);
			}
		});
	}

	public static String type(final String key) {
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
	public static Long expire(final String key, final int seconds) {
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
	public static Long expireAt(final String key, final long unixTime) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expireAt(key, unixTime);
			}
		});
	}

	public static Long ttl(final String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.ttl(key);
			}
		});
	}

	public static Boolean setbit(final String key, final long offset, final boolean value) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.setbit(key, offset, value);
			}
		});
	}

	public static Boolean getbit(final String key, final long offset) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.getbit(key, offset);
			}
		});
	}

	public static Long setrange(final String key, final long offset, final String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setrange(key, offset, value);
			}
		});
	}

	public static String getrange(final String key, final long startOffset, final long endOffset) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.getrange(key, startOffset, endOffset);
			}
		});
	}

	public static String getSet(final String key, final String value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.getSet(key, value);
			}
		});
	}

	public static Long setnx(final String key, final String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	public static String setex(final String key, final int seconds, final String value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		}, true);
	}

	public static Long decrBy(final String key, final long integer) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decrBy(key, integer);
			}
		});
	}

	public static Long decr(final String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public static Long incrBy(final String key, final long integer) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incrBy(key, integer);
			}
		});
	}

	public static Long incr(final String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public static Long append(final String key, final String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.append(key, value);
			}
		});
	}

	public static String substr(final String key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.substr(key, start, end);
			}
		});
	}

	public static Long hset(final String key, final String field, final String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public static String hget(final String key, final String field) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public static Long hsetnx(final String key, final String field, final String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hsetnx(key, field, value);
			}
		});
	}

	public static String hmset(final String key, final Map<String, String> hash) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public static List<String> hmget(final String key, final String... fields) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public static Long hincrBy(final String key, final String field, final long value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hincrBy(key, field, value);
			}
		});
	}

	public static Boolean hexists(final String key, final String field) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.hexists(key, field);
			}
		});
	}

	public static Long hdel(final String key, final String field) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hdel(key, field);
			}
		});
	}

	public static Long hlen(final String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hlen(key);
			}
		});
	}

	public static Set<String> hkeys(final String key) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public static List<String> hvals(final String key) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.hvals(key);
			}
		});
	}

	public static Map<String, String> hgetAll(final String key) {
		return JedisTemplate.run(key, new Executor<Map<String, String>>() {
			public Map<String, String> execute(ShardedJedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public static Long rpush(final String key, final String string) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.rpush(key, string);
			}
		});
	}

	public static Long lpush(final String key, final String string) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lpush(key, string);
			}
		});
	}

	public static Long llen(final String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	public static List<String> lrange(final String key, final long start, final long end) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public static String ltrim(final String key, final long start, final long end) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.ltrim(key, start, end);
			}
		});
	}

	public static String lindex(final String key, final long index) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lindex(key, index);
			}
		});
	}

	public static String lset(final String key, final long index, final String value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lset(key, index, value);
			}
		});
	}

	public static Long lrem(final String key, final long count, final String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lrem(key, count, value);
			}
		});
	}

	public static String lpop(final String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	public static String rpop(final String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	// return 1 add a not exist value ,
	// return 0 add a exist value
	public static Long sadd(final String key, final String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.sadd(key, member);
			}
		});
	}

	public static Set<String> smembers(final String key) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	public static Long srem(final String key, final String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.srem(key, member);
			}
		});
	}

	public static String spop(final String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.spop(key);
			}
		});
	}

	public static Long scard(final String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.scard(key);
			}
		});
	}

	public static Boolean sismember(final String key, final String member) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}

	public static String srandmember(final String key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.srandmember(key);
			}
		});
	}

	public static Long zadd(final String key, final double score, final String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	public static Set<String> zrange(final String key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	public static Long zrem(final String key, final String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	public static Double zincrby(final String key, final double score, final String member) {
		return JedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zincrby(key, score, member);
			}
		});
	}

	public static Long zrank(final String key, final String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	public static Long zrevrank(final String key, final String member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	public static Set<String> zrevrange(final String key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	public static Set<Tuple> zrangeWithScores(final String key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeWithScores(key, start, end);
			}
		});
	}

	public static Set<Tuple> zrevrangeWithScores(final String key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	public static Long zcard(final String key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcard(key);
			}
		});
	}

	public static Double zscore(final String key, final String member) {
		return JedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	public static List<String> sort(final String key) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.sort(key);
			}
		});
	}

	public static List<String> sort(final String key, final SortingParams sortingParameters) {
		return JedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.sort(key, sortingParameters);
			}
		});
	}

	public static Long zcount(final String key, final double min, final double max) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	public static Set<String> zrangeByScore(final String key, final double min, final double max) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	public static Set<String> zrevrangeByScore(final String key, final double max, final double min) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	public static Set<String> zrangeByScore(final String key, final double min, final double max, final int offset,
			final int count) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	public static Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset,
			final int count) {
		return JedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	public static Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	public static Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	public static Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	public static Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min,
			final int offset, final int count) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	public static Long zremrangeByRank(final String key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByRank(key, start, end);
			}
		});
	}

	public static Long zremrangeByScore(final String key, final double start, final double end) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	public static Long linsert(final String key, final LIST_POSITION where, final String pivot, final String value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.linsert(key, where, pivot, value);
			}
		});
	}

	public static String set(final byte[] key, final byte[] value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	public static byte[] get(final byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public static Boolean exists(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	public static String type(final byte[] key) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.type(key);
			}
		});
	}

	public static Long expire(final byte[] key, final int seconds) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expire(key, seconds);
			}
		}, true);
	}

	public static Long expireAt(final byte[] key, final long unixTime) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expireAt(key, unixTime);
			}
		});
	}

	public static Long ttl(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.ttl(key);
			}
		});
	}

	public static Long del(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.del(key);
			}
		});
	}

	public byte[] getSet(final byte[] key, final byte[] value) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.getSet(key, value);
			}
		});
	}

	public Long setnx(final byte[] key, final byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	public String setex(final byte[] key, final int seconds, final byte[] value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		}, true);
	}

	public Long decrBy(final byte[] key, final long integer) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decrBy(key, integer);
			}
		});
	}

	public Long decr(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public Long incrBy(final byte[] key, final long integer) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incrBy(key, integer);
			}
		});
	}

	public Long incr(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public Long append(final byte[] key, final byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.append(key, value);
			}
		});
	}

	public byte[] substr(final byte[] key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.substr(key, start, end);
			}
		});
	}

	public Long hset(final byte[] key, final byte[] field, final byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public byte[] hget(final byte[] key, final byte[] field) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public Long hsetnx(final byte[] key, final byte[] field, final byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hsetnx(key, field, value);
			}
		});
	}

	public String hmset(final byte[] key, final Map<byte[], byte[]> hash) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public List<byte[]> hmget(final byte[] key, final byte[]... fields) {
		return JedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public Long hincrBy(final byte[] key, final byte[] field, final long value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hincrBy(key, field, value);
			}
		});
	}

	public Boolean hexists(final byte[] key, final byte[] field) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.hexists(key, field);
			}
		});
	}

	public Long hdel(final byte[] key, final byte[] field) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hdel(key, field);
			}
		});
	}

	public Long hlen(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hlen(key);
			}
		});
	}

	public static Set<byte[]> hkeys(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public Collection<byte[]> hvals(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Collection<byte[]>>() {
			public Collection<byte[]> execute(ShardedJedis jedis) {
				return jedis.hvals(key);
			}
		});
	}

	public Map<byte[], byte[]> hgetAll(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Map<byte[], byte[]>>() {
			public Map<byte[], byte[]> execute(ShardedJedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public Long rpush(final byte[] key, final byte[] string) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.rpush(key, string);
			}
		});
	}

	public Long lpush(final byte[] key, final byte[] string) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lpush(key, string);
			}
		});
	}

	public Long llen(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	public List<byte[]> lrange(final byte[] key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public String ltrim(final byte[] key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.ltrim(key, start, end);
			}
		});
	}

	public byte[] lindex(final byte[] key, final int index) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.lindex(key, index);
			}
		});
	}

	public String lset(final byte[] key, final int index, final byte[] value) {
		return JedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lset(key, index, value);
			}
		});
	}

	public Long lrem(final byte[] key, final int count, final byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lrem(key, count, value);
			}
		});
	}

	public byte[] lpop(final byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	public byte[] rpop(final byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	public Long sadd(final byte[] key, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.sadd(key, member);
			}
		});
	}

	public Set<byte[]> smembers(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	public Long srem(final byte[] key, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.srem(key, member);
			}
		});
	}

	public byte[] spop(final byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.spop(key);
			}
		});
	}

	public Long scard(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.scard(key);
			}
		});
	}

	public Boolean sismember(final byte[] key, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}

	public byte[] srandmember(final byte[] key) {
		return JedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.srandmember(key);
			}
		});
	}

	public Long zadd(final byte[] key, final double score, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	public Set<byte[]> zrange(final byte[] key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	public Long zrem(final byte[] key, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	public Double zincrby(final byte[] key, final double score, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zincrby(key, score, member);
			}
		});
	}

	public Long zrank(final byte[] key, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	public Long zrevrank(final byte[] key, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	public Set<byte[]> zrevrange(final byte[] key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	public Set<Tuple> zrangeWithScores(final byte[] key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeWithScores(key, start, end);
			}
		});
	}

	public Set<Tuple> zrevrangeWithScores(final byte[] key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	public Long zcard(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcard(key);
			}
		});
	}

	public Double zscore(final byte[] key, final byte[] member) {
		return JedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	public List<byte[]> sort(final byte[] key) {
		return JedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.sort(key);
			}
		});
	}

	public List<byte[]> sort(final byte[] key, final SortingParams sortingParameters) {
		return JedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.sort(key, sortingParameters);
			}
		});
	}

	public Long zcount(final byte[] key, final double min, final double max) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	public Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	public Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	public Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	public Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	public Set<byte[]> zrevrangeByScore(final byte[] key, final double max, final double min) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	public Set<byte[]> zrevrangeByScore(final byte[] key, final double max, final double min, final int offset,
			final int count) {
		return JedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	public Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double max, final double min) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	public Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double max, final double min, final int offset,
			final int count) {
		return JedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	public Long zremrangeByRank(final byte[] key, final int start, final int end) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByRank(key, start, end);
			}
		});
	}

	public Long zremrangeByScore(final byte[] key, final double start, final double end) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	public Long linsert(final byte[] key, final LIST_POSITION where, final byte[] pivot, final byte[] value) {
		return JedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.linsert(key, where, pivot, value);
			}
		});
	}

	public Jedis getShard(final byte[] key) {
		return JedisTemplate.run(key, new Executor<Jedis>() {
			public Jedis execute(ShardedJedis jedis) {
				return jedis.getShard(key);
			}
		});
	}

	public Jedis getShard(final String key) {
		return JedisTemplate.run(key, new Executor<Jedis>() {
			public Jedis execute(ShardedJedis jedis) {
				return jedis.getShard(key);
			}
		});
	}

	public JedisShardInfo getShardInfo(final byte[] key) {
		return JedisTemplate.run(key, new Executor<JedisShardInfo>() {
			public JedisShardInfo execute(ShardedJedis jedis) {
				return jedis.getShardInfo(key);
			}
		});
	}

	public JedisShardInfo getShardInfo(final String key) {
		return JedisTemplate.run(key, new Executor<JedisShardInfo>() {
			public JedisShardInfo execute(ShardedJedis jedis) {
				return jedis.getShardInfo(key);
			}
		});
	}

	public String getKeyTag(final String key) {
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
