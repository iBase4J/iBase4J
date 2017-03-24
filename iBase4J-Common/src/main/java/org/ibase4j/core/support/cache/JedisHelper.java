package org.ibase4j.core.support.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ibase4j.core.support.cache.jedis.Executor;
import org.ibase4j.core.support.cache.jedis.JedisTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

/**
 * Redis缓存辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:17:22
 */
@Configuration
public class JedisHelper implements CacheManager {
	JedisTemplate jedisTemplate;

	@Bean
	public JedisTemplate setJedisTemplate() {
		jedisTemplate = new JedisTemplate();
		return jedisTemplate;
	}

	public final Object get(final String key) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public final String get(final String key, final Integer... expire) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		}, expire);
	}

	public void set(final String key, final Serializable value) {
		jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.set(key, JSON.toJSONString(value));
			}
		});
	}

	public final void set(final String key, final Serializable value, final int seconds) {
		jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, JSON.toJSONString(value));
			}
		}, seconds, seconds);
	}

	public final Boolean exists(final String key) {
		return jedisTemplate.run((String) key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.exists((String) key);
			}
		});
	}

	public final void del(final String key) {
		jedisTemplate.run((String) key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.del((String) key);
			}
		});
	}

	public final String type(final String key) {
		return jedisTemplate.run((String) key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.type((String) key);
			}
		});
	}

	/**
	 * 在某段时间后失效
	 * 
	 * @return
	 */
	public final Boolean expire(final String key, final int seconds) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expire(key, seconds);
			}
		}, seconds, seconds) == 1;
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public final Boolean expireAt(final String key, final long unixTime) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expireAt(key, unixTime);
			}
		}) == 1;
	}

	public final Long ttl(final String key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.ttl(key);
			}
		});
	}

	public final Boolean setbit(final String key, final long offset, final boolean value) {
		return jedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.setbit(key, offset, value);
			}
		});
	}

	public final Boolean getbit(final String key, final long offset) {
		return jedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.getbit(key, offset);
			}
		});
	}

	public final Long setrange(final String key, final long offset, final String value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setrange(key, offset, value);
			}
		});
	}

	public final String getrange(final String key, final long startOffset, final long endOffset) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.getrange(key, startOffset, endOffset);
			}
		});
	}

	public final Object getSet(final String key, final Serializable value) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.getSet(key, JSON.toJSONString(value));
			}
		});
	}

	public final Long setnx(final String key, final String value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	public final String setex(final String key, final int seconds, final String value) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		}, seconds, seconds);
	}

	public final Long decrBy(final String key, final long integer) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decrBy(key, integer);
			}
		});
	}

	public final Long decr(final String key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public final Long incrBy(final String key, final long integer) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incrBy(key, integer);
			}
		});
	}

	public final Long incr(final String key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public final Long append(final String key, final String value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.append(key, value);
			}
		});
	}

	public final String substr(final String key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.substr(key, start, end);
			}
		});
	}

	public final void hset(final String key, final String field, final String value) {
		jedisTemplate.run(key, new Executor<Object>() {
			public Object execute(ShardedJedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public final String hget(final String key, final String field) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public final Long hsetnx(final String key, final String field, final String value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hsetnx(key, field, value);
			}
		});
	}

	public final String hmset(final String key, final Map<String, String> hash) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public final List<String> hmget(final String key, final String... fields) {
		return jedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public final Long hincrBy(final String key, final String field, final long value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hincrBy(key, field, value);
			}
		});
	}

	public final Boolean hexists(final String key, final String field) {
		return jedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.hexists(key, field);
			}
		});
	}

	public final void hdel(final String key, final String field) {
		jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hdel(key, field);
			}
		});
	}

	public final Long hlen(final String key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hlen(key);
			}
		});
	}

	public final Set<String> hkeys(final String key) {
		return jedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public final List<String> hvals(final String key) {
		return jedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.hvals(key);
			}
		});
	}

	public final Map<String, String> hgetAll(final String key) {
		return jedisTemplate.run(key, new Executor<Map<String, String>>() {
			public Map<String, String> execute(ShardedJedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public final Long rpush(final String key, final String string) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.rpush(key, string);
			}
		});
	}

	public final Long lpush(final String key, final String string) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lpush(key, string);
			}
		});
	}

	public final Long llen(final String key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	public final List<String> lrange(final String key, final long start, final long end) {
		return jedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public final String ltrim(final String key, final long start, final long end) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.ltrim(key, start, end);
			}
		});
	}

	public final String lindex(final String key, final long index) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lindex(key, index);
			}
		});
	}

	public final String lset(final String key, final long index, final String value) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lset(key, index, value);
			}
		});
	}

	public final Long lrem(final String key, final long count, final String value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lrem(key, count, value);
			}
		});
	}

	public final String lpop(final String key) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	public final String rpop(final String key) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	// return 1 add a not exist value ,
	// return 0 add a exist value
	public final Long sadd(final String key, final String member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.sadd(key, member);
			}
		});
	}

	public final Set<String> smembers(final String key) {
		return jedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	public final Long srem(final String key, final String member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.srem(key, member);
			}
		});
	}

	public final String spop(final String key) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.spop(key);
			}
		});
	}

	public final Long scard(final String key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.scard(key);
			}
		});
	}

	public final Boolean sismember(final String key, final String member) {
		return jedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}

	public final String srandmember(final String key) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.srandmember(key);
			}
		});
	}

	public final Long zadd(final String key, final double score, final String member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	public final Set<String> zrange(final String key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	public final Long zrem(final String key, final String member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	public final Double zincrby(final String key, final double score, final String member) {
		return jedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zincrby(key, score, member);
			}
		});
	}

	public final Long zrank(final String key, final String member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	public final Long zrevrank(final String key, final String member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	public final Set<String> zrevrange(final String key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	public final Set<Tuple> zrangeWithScores(final String key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeWithScores(key, start, end);
			}
		});
	}

	public final Set<Tuple> zrevrangeWithScores(final String key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	public final Long zcard(final String key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcard(key);
			}
		});
	}

	public final Double zscore(final String key, final String member) {
		return jedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	public final List<String> sort(final String key) {
		return jedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.sort(key);
			}
		});
	}

	public final List<String> sort(final String key, final SortingParams sortingParameters) {
		return jedisTemplate.run(key, new Executor<List<String>>() {
			public List<String> execute(ShardedJedis jedis) {
				return jedis.sort(key, sortingParameters);
			}
		});
	}

	public final Long zcount(final String key, final double min, final double max) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	public final Set<String> zrangeByScore(final String key, final double min, final double max) {
		return jedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	public final Set<String> zrevrangeByScore(final String key, final double max, final double min) {
		return jedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	public final Set<String> zrangeByScore(final String key, final double min, final double max, final int offset,
			final int count) {
		return jedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	public final Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset,
			final int count) {
		return jedisTemplate.run(key, new Executor<Set<String>>() {
			public Set<String> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	public final Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	public final Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	public final Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	public final Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min,
			final int offset, final int count) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	public final Long zremrangeByRank(final String key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByRank(key, start, end);
			}
		});
	}

	public final Long zremrangeByScore(final String key, final double start, final double end) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	public final Long linsert(final String key, final LIST_POSITION where, final String pivot, final String value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.linsert(key, where, pivot, value);
			}
		});
	}

	public final String set(final byte[] key, final byte[] value, final Integer... expire) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.set(key, value);
			}
		}, expire);
	}

	public final byte[] get(final byte[] key, final Integer... expire) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		}, expire);
	}

	public final Boolean exists(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	public final String type(final byte[] key) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.type(key);
			}
		});
	}

	public final Long expire(final byte[] key, final int seconds) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expire(key, seconds);
			}
		}, seconds, seconds);
	}

	public final Long expireAt(final byte[] key, final long unixTime) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.expireAt(key, unixTime);
			}
		});
	}

	public final Long ttl(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.ttl(key);
			}
		});
	}

	public final Long del(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.del(key);
			}
		});
	}

	public byte[] getSet(final byte[] key, final byte[] value) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.getSet(key, value);
			}
		});
	}

	public Long setnx(final byte[] key, final byte[] value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	public String setex(final byte[] key, final int seconds, final byte[] value) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		}, seconds, seconds);
	}

	public Long decrBy(final byte[] key, final long integer) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decrBy(key, integer);
			}
		});
	}

	public Long decr(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public Long incrBy(final byte[] key, final long integer) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incrBy(key, integer);
			}
		});
	}

	public Long incr(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public Long append(final byte[] key, final byte[] value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.append(key, value);
			}
		});
	}

	public byte[] substr(final byte[] key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.substr(key, start, end);
			}
		});
	}

	public Long hset(final byte[] key, final byte[] field, final byte[] value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public byte[] hget(final byte[] key, final byte[] field) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public Long hsetnx(final byte[] key, final byte[] field, final byte[] value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hsetnx(key, field, value);
			}
		});
	}

	public String hmset(final byte[] key, final Map<byte[], byte[]> hash) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public List<byte[]> hmget(final byte[] key, final byte[]... fields) {
		return jedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public Long hincrBy(final byte[] key, final byte[] field, final long value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hincrBy(key, field, value);
			}
		});
	}

	public Boolean hexists(final byte[] key, final byte[] field) {
		return jedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.hexists(key, field);
			}
		});
	}

	public Long hdel(final byte[] key, final byte[] field) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hdel(key, field);
			}
		});
	}

	public Long hlen(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.hlen(key);
			}
		});
	}

	public final Set<byte[]> hkeys(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public final Collection<byte[]> hvals(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Collection<byte[]>>() {
			public Collection<byte[]> execute(ShardedJedis jedis) {
				return jedis.hvals(key);
			}
		});
	}

	public final Map<byte[], byte[]> hgetAll(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Map<byte[], byte[]>>() {
			public Map<byte[], byte[]> execute(ShardedJedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public final Long rpush(final byte[] key, final byte[] string) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.rpush(key, string);
			}
		});
	}

	public final Long lpush(final byte[] key, final byte[] string) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lpush(key, string);
			}
		});
	}

	public final Long llen(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	public final List<byte[]> lrange(final byte[] key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public final String ltrim(final byte[] key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.ltrim(key, start, end);
			}
		});
	}

	public final byte[] lindex(final byte[] key, final int index) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.lindex(key, index);
			}
		});
	}

	public final String lset(final byte[] key, final int index, final byte[] value) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.lset(key, index, value);
			}
		});
	}

	public final Long lrem(final byte[] key, final int count, final byte[] value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.lrem(key, count, value);
			}
		});
	}

	public final byte[] lpop(final byte[] key) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	public final byte[] rpop(final byte[] key) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	public final Long sadd(final byte[] key, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.sadd(key, member);
			}
		});
	}

	public final Set<byte[]> smembers(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	public final Long srem(final byte[] key, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.srem(key, member);
			}
		});
	}

	public final byte[] spop(final byte[] key) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.spop(key);
			}
		});
	}

	public final Long scard(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.scard(key);
			}
		});
	}

	public final Boolean sismember(final byte[] key, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Boolean>() {
			public Boolean execute(ShardedJedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}

	public final byte[] srandmember(final byte[] key) {
		return jedisTemplate.run(key, new Executor<byte[]>() {
			public byte[] execute(ShardedJedis jedis) {
				return jedis.srandmember(key);
			}
		});
	}

	public final Long zadd(final byte[] key, final double score, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	public final Set<byte[]> zrange(final byte[] key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	public final Long zrem(final byte[] key, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	public final Double zincrby(final byte[] key, final double score, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zincrby(key, score, member);
			}
		});
	}

	public final Long zrank(final byte[] key, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	public final Long zrevrank(final byte[] key, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	public final Set<byte[]> zrevrange(final byte[] key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	public final Set<Tuple> zrangeWithScores(final byte[] key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeWithScores(key, start, end);
			}
		});
	}

	public final Set<Tuple> zrevrangeWithScores(final byte[] key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	public final Long zcard(final byte[] key) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcard(key);
			}
		});
	}

	public final Double zscore(final byte[] key, final byte[] member) {
		return jedisTemplate.run(key, new Executor<Double>() {
			public Double execute(ShardedJedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	public final List<byte[]> sort(final byte[] key) {
		return jedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.sort(key);
			}
		});
	}

	public final List<byte[]> sort(final byte[] key, final SortingParams sortingParameters) {
		return jedisTemplate.run(key, new Executor<List<byte[]>>() {
			public List<byte[]> execute(ShardedJedis jedis) {
				return jedis.sort(key, sortingParameters);
			}
		});
	}

	public final Long zcount(final byte[] key, final double min, final double max) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	public final Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max) {
		return jedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	public final Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count) {
		return jedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	public final Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	public final Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	public final Set<byte[]> zrevrangeByScore(final byte[] key, final double max, final double min) {
		return jedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	public final Set<byte[]> zrevrangeByScore(final byte[] key, final double max, final double min, final int offset,
			final int count) {
		return jedisTemplate.run(key, new Executor<Set<byte[]>>() {
			public Set<byte[]> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	public final Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double max, final double min) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	public final Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double max, final double min,
			final int offset, final int count) {
		return jedisTemplate.run(key, new Executor<Set<Tuple>>() {
			public Set<Tuple> execute(ShardedJedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	public final Long zremrangeByRank(final byte[] key, final int start, final int end) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByRank(key, start, end);
			}
		});
	}

	public final Long zremrangeByScore(final byte[] key, final double start, final double end) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	public final Long linsert(final byte[] key, final LIST_POSITION where, final byte[] pivot, final byte[] value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.linsert(key, where, pivot, value);
			}
		});
	}

	public final String getKeyTag(final String key) {
		return jedisTemplate.run(key, new Executor<String>() {
			public String execute(ShardedJedis jedis) {
				return jedis.getKeyTag(key);
			}
		});
	}

	public Set<Object> getAll(String pattern) {
		return null;
	}

	public void delAll(String pattern) {
	}

	public boolean setnx(final String key, final Serializable value) {
		return jedisTemplate.run(key, new Executor<Long>() {
			public Long execute(ShardedJedis jedis) {
				return jedis.setnx(key, JSON.toJSONString(value));
			}
		}) == 0;
	}

	public void unlock(String key) {
		del(key);
	}
}
