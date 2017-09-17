package org.ibase4j.core.support.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ibase4j.core.util.CacheUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis缓存辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:17:22
 */
public final class RedisHelper implements CacheManager {
    private RedisTemplate<Serializable, Serializable> redisTemplate;
    private RedisConnection redisConnection;
    private RedisSerializer<String> keySerializer;
    private RedisSerializer<Object> valueSerializer;
    private final Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

    @SuppressWarnings("unchecked")
    public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisConnection = RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());
        this.keySerializer = (RedisSerializer<String>)redisTemplate.getKeySerializer();
        this.valueSerializer = (RedisSerializer<Object>)redisTemplate.getValueSerializer();
        CacheUtil.setCacheManager(this);
    }

    public final Object get(final String key) {
        expire(key, EXPIRE);
        return redisTemplate.boundValueOps(key).get();
    }

    public final Set<Object> getAll(final String pattern) {
        Set<Object> values = InstanceUtil.newHashSet();
        Set<Serializable> keys = redisTemplate.keys(pattern);
        for (Serializable key : keys) {
            expire(key.toString(), EXPIRE);
            values.add(redisTemplate.opsForValue().get(key));
        }
        return values;
    }

    public final void set(final String key, final Serializable value, int seconds) {
        redisTemplate.boundValueOps(key).set(value);
        expire(key, seconds);
    }

    public final void set(final String key, final Serializable value) {
        redisTemplate.boundValueOps(key).set(value);
        expire(key, EXPIRE);
    }

    public final Boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public final void del(final String key) {
        redisTemplate.delete(key);
    }

    public final void delAll(final String pattern) {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

    public final String type(final String key) {
        expire(key, EXPIRE);
        return redisTemplate.type(key).getClass().getName();
    }

    /**
     * 在某段时间后失效
     * 
     * @return
     */
    public final Boolean expire(final String key, final int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 在某个时间点失效
     * 
     * @param key
     * @param unixTime
     * @return
     */
    public final Boolean expireAt(final String key, final long unixTime) {
        return redisTemplate.expireAt(key, new Date(unixTime));
    }

    public final Long ttl(final String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public final void setrange(final String key, final long offset, final String value) {
        redisTemplate.boundValueOps(key).set(value, offset);
        expire(key, EXPIRE);
    }

    public final String getrange(final String key, final long startOffset, final long endOffset) {
        expire(key, EXPIRE);
        return redisTemplate.boundValueOps(key).get(startOffset, endOffset);
    }

    public final Object getSet(final String key, final Serializable value) {
        expire(key, EXPIRE);
        return redisTemplate.boundValueOps(key).getAndSet(value);
    }

    public boolean setnx(String key, Serializable value) {
        return redisConnection.setNX(keySerializer.serialize(key), valueSerializer.serialize(value));
    }

    public boolean lock(String key) {
        return redisConnection.setNX(keySerializer.serialize(key), valueSerializer.serialize("0"));
    }

    public void unlock(String key) {
        del(key);
    }

    public void hset(String key, Serializable field, Serializable value) {
        redisTemplate.boundHashOps(key).put(field, value);
    }

    public Object hget(String key, Serializable field) {
        return redisTemplate.boundHashOps(key).get(field);
    }

    public void hdel(String key, Serializable field) {
        redisTemplate.boundHashOps(key).delete(field);
    }

    public void sadd(String key, Serializable value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    public Set<?> sall(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    public boolean sdel(String key, Serializable value) {
        return redisTemplate.boundSetOps(key).remove(value) == 1;
    }

    // 未完，待续...
}
