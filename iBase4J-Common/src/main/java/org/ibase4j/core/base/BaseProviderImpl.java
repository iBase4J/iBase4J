package org.ibase4j.core.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.Constants;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.DateUtil;
import org.ibase4j.core.util.DateUtil.DATE_PATTERN;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 业务逻辑层基类<br/>
 * 继承基类后必须配置CacheConfig(cacheNames="")
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseProviderImpl<T extends BaseModel> implements BaseProvider<T> {
    @Autowired
    protected BaseMapper<T> mapper;

    /** 分页查询  */
    protected Page<String> getPage(Map<String, Object> params) {
        Integer current = 1;
        Integer size = 10;
        String orderBy = "";
        if (DataUtil.isNotEmpty(params.get("pageNum"))) {
            current = Integer.valueOf((String)params.get("pageNum"));
        }
        if (DataUtil.isNotEmpty(params.get("pageSize"))) {
            size = Integer.valueOf((String)params.get("pageSize"));
        }
        if (DataUtil.isNotEmpty(params.get("orderBy"))) {
            orderBy = (String)params.get("orderBy");
        }
        Page<String> page = new Page<String>(current, size, orderBy);
        page.setAsc(false);
        return page;
    }

    @SuppressWarnings("unchecked")
    private BaseProviderImpl<T> getProvider() {
        return InstanceUtil.getBean(getClass());
    }

    /** 生成主键策略 */
    public String createId(String key) {
        String redisKey = "REDIS_TBL_" + key;
        String dateTime = DateUtil.getDateTime(DATE_PATTERN.YYYYMMDDHHMMSSSSS);
        return dateTime + RedisUtil.incr(redisKey);
    }

    /** 根据Id查询(默认类型T) */
    public Page<T> getPage(Page<String> ids) {
        if (ids != null) {
            Page<T> page = new Page<T>(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            BaseProviderImpl<T> provider = getProvider();
            List<T> records = InstanceUtil.newArrayList();
            for (String id : ids.getRecords()) {
                records.add(provider.queryById(id));
            }
            page.setRecords(records);
            return page;
        }
        return new Page<T>();
    }

    /** 根据Id查询(cls返回类型Class) */
    public <K> Page<K> getPage(Page<String> ids, Class<K> cls) {
        if (ids != null) {
            Page<K> page = new Page<K>(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            BaseProviderImpl<T> provider = getProvider();
            List<K> records = InstanceUtil.newArrayList();
            for (String id : ids.getRecords()) {
                T t = provider.queryById(id);
                K k = InstanceUtil.to(t, cls);
                records.add(k);
            }
            page.setRecords(records);
            return page;
        }
        return new Page<K>();
    }

    /** 根据Id查询(默认类型T) */
    public List<T> getList(List<String> ids) {
        List<T> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (String id : ids) {
                list.add(getProvider().queryById(id));
            }
        }
        return list;
    }

    /** 根据Id查询(cls返回类型Class) */
    public <K> List<K> getList(List<String> ids, Class<K> cls) {
        List<K> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (String id : ids) {
                T t = getProvider().queryById(id);
                K k = InstanceUtil.to(t, cls);
                list.add(k);
            }
        }
        return list;
    }

    @Transactional
    public void delete(String id, String userId) {
        try {
            T record = queryById(id);
            record.setEnable(false);
            record.setUpdateTime(new Date());
            record.setUpdateBy(userId);
            mapper.updateById(record);
            RedisUtil.set(getCacheKey(id), record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Transactional
    public T update(T record) {
        try {
            record.setEnable(true);
            record.setUpdateTime(new Date());
            if (StringUtils.isBlank(record.getId())) {
                String key = record.getClass().getSimpleName();
                record.setId(createId(key));
                record.setCreateTime(new Date());
                mapper.insert(record);
            } else {
                mapper.updateById(record);
            }
            RedisUtil.set(getCacheKey(record.getId()), record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return record;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public T queryById(String id) {
        try {
            String key = getCacheKey(id);
            T record = (T)RedisUtil.get(key);
            if (record == null) {
                record = mapper.selectById(id);
                RedisUtil.set(key, record);
            }
            return record;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /** 获取缓存键值 */
    protected String getCacheKey(Object id) {
        String cacheName = null;
        CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
        if (cacheConfig == null || cacheConfig.cacheNames() == null || cacheConfig.cacheNames().length < 1) {
            cacheName = getClass().getName();
        } else {
            cacheName = cacheConfig.cacheNames()[0];
        }
        return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
    }

    public Page<T> query(Map<String, Object> params) {
        return null;
    }
}
