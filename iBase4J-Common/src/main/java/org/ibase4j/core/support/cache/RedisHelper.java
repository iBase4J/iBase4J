package org.ibase4j.core.support.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis缓存辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:17:22
 */
public final class RedisHelper implements CacheManager, ApplicationContextAware {

    private RedisTemplate<Serializable, Serializable> redisTemplate = null;
    private Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

    protected ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // 获取连接
    private RedisTemplate<Serializable, Serializable> getRedis() {
        if (redisTemplate == null) {
            synchronized (RedisHelper.class) {
                if (redisTemplate == null) {
                    JedisConnectionFactory connectionFactory = applicationContext.getBean(JedisConnectionFactory.class);
                    redisTemplate = new RedisTemplate<Serializable, Serializable>();
                    redisTemplate.setConnectionFactory(connectionFactory);
                }
            }
        }
        return redisTemplate;
    }

    public final Object get(final String key) {
        expire(key, EXPIRE);
        return getRedis().boundValueOps(key).get();
    }

    public final Set<Object> getAll(final String pattern) {
        Set<Object> values = InstanceUtil.newHashSet();
        Set<Serializable> keys = getRedis().keys(pattern);
        for (Serializable key : keys) {
            expire(key.toString(), EXPIRE);
            values.add(getRedis().opsForValue().get(key));
        }
        return values;
    }

    public final void set(final String key, final Serializable value, int seconds) {
        getRedis().boundValueOps(key).set(value);
        expire(key, seconds);
    }

    public final void set(final String key, final Serializable value) {
        getRedis().boundValueOps(key).set(value);
        expire(key, EXPIRE);
    }

    public final Boolean exists(final String key) {
        return getRedis().hasKey(key);
    }

    public final void del(final String key) {
        getRedis().delete(key);
    }

    public final void delAll(final String pattern) {
        getRedis().delete(getRedis().keys(pattern));
    }

    public final String type(final String key) {
        expire(key, EXPIRE);
        return getRedis().type(key).getClass().getName();
    }

    /**
     * 在某段时间后失效
     * 
     * @return
     */
    public final Boolean expire(final String key, final int seconds) {
        return getRedis().expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 在某个时间点失效
     * 
     * @param key
     * @param unixTime
     * @return
     */
    public final Boolean expireAt(final String key, final long unixTime) {
        return getRedis().expireAt(key, new Date(unixTime));
    }

    public final Long ttl(final String key) {
        return getRedis().getExpire(key, TimeUnit.SECONDS);
    }

    public final void setrange(final String key, final long offset, final String value) {
        getRedis().boundValueOps(key).set(value, offset);
        expire(key, EXPIRE);
    }

    public final String getrange(final String key, final long startOffset, final long endOffset) {
        expire(key, EXPIRE);
        return getRedis().boundValueOps(key).get(startOffset, endOffset);
    }

    public final Object getSet(final String key, final Serializable value) {
        expire(key, EXPIRE);
        return getRedis().boundValueOps(key).getAndSet(value);
    }

    public boolean setnx(String key, Serializable value) {
        getRedis().boundValueOps(key).setIfAbsent(value);
        return false;
    }

    public void unlock(String key) {
        del(key);
    }

    public void hset(String key, String field, String value) {
        getRedis().boundHashOps(key).put(field, value);
    }

    public Object hget(String key, String field) {
        return getRedis().boundHashOps(key).get(field);
    }

    public void hdel(String key, String field) {
        getRedis().boundHashOps(key).delete(field);
    }

    // 未完，待续...
}
