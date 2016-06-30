package org.ibase4j.core.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ibase4j.core.Constants;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 业务逻辑层基类<br/>
 * 继承基类后必须配置CacheConfig(cacheNames="")
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseProviderImpl<T extends BaseModel> implements BaseProvider<T> {
	@Autowired
	BaseMapper<T> mapper;

	/** 启动分页查询 */
	protected void startPage(Map<String, Object> params) {
		if (DataUtil.isEmpty(params.get("pageNum"))) {
			params.put("pageNum", 1);
		}
		if (DataUtil.isEmpty(params.get("pageSize"))) {
			params.put("pageSize", 10);
		}
		if (DataUtil.isEmpty(params.get("orderBy"))) {
			params.put("orderBy", "id_ desc");
		}
		PageHelper.startPage(params);
	}

	@SuppressWarnings("unchecked")
	private BaseProviderImpl<T> getProvider() {
		return InstanceUtil.getBean(getClass());
	}

	/** 根据Id查询(默认类型T) */
	public PageInfo<T> getPage(Page<Integer> ids) {
		if (ids != null) {
			Page<T> page = new Page<T>(ids.getPageNum(), ids.getPageSize());
			page.setTotal(ids.getTotal());
			page.clear();
			BaseProviderImpl<T> provider = getProvider();
			for (Integer id : ids) {
				page.add(provider.queryById(id));
			}
			return new PageInfo<T>(page);
		}
		return new PageInfo<T>();
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> PageInfo<K> getPage(Page<Integer> ids, Class<K> cls) {
		if (ids != null) {
			Page<K> page = new Page<K>(ids.getPageNum(), ids.getPageSize());
			page.setTotal(ids.getTotal());
			page.clear();
			BaseProviderImpl<T> provider = getProvider();
			for (Integer id : ids) {
				T t = provider.queryById(id);
				K k = InstanceUtil.to(t, cls);
				page.add(k);
			}
			return new PageInfo<K>(page);
		}
		return new PageInfo<K>();
	}

	/** 根据Id查询(默认类型T) */
	public List<T> getList(List<Integer> ids) {
		List<T> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (Integer id : ids) {
				list.add(getProvider().queryById(id));
			}
		}
		return list;
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> List<K> getList(List<Integer> ids, Class<K> cls) {
		List<K> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (Integer id : ids) {
				T t = getProvider().queryById(id);
				K k = InstanceUtil.to(t, cls);
				list.add(k);
			}
		}
		return list;
	}

	@Transactional
	public void delete(Integer id, Integer userId) {
		try {
			T record = queryById(id);
			record.setEnable(false);
			record.setUpdateTime(new Date());
			record.setUpdateBy(userId);
			mapper.updateByPrimaryKey(record);
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
			if (record.getId() == null) {
				record.setCreateTime(new Date());
				mapper.insert(record);
			} else {
				mapper.updateByPrimaryKey(record);
			}
			RedisUtil.set(getCacheKey(record.getId()), record);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public T queryById(Integer id) {
		try {
			String key = getCacheKey(id);
			T record = (T) RedisUtil.get(key);
			if (record == null) {
				record = mapper.selectByPrimaryKey(id);
				RedisUtil.set(key, record);
			}
			return record;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void init() {
		List<T> list = mapper.selectAll();
		for (T record : list) {
			RedisUtil.set(getCacheKey(record.getId()), record);
		}
	}

	/** 获取缓存键值 */
	private String getCacheKey(Object id) {
		String cacheName = null;
		CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
		if (cacheConfig == null || cacheConfig.cacheNames() == null || cacheConfig.cacheNames().length < 1) {
			cacheName = getClass().getName();
		} else {
			cacheName = cacheConfig.cacheNames()[0];
		}
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
	}

	public PageInfo<T> query(Map<String, Object> params) {
		return null;
	}
}
