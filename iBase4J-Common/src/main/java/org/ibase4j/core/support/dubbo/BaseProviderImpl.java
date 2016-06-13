package org.ibase4j.core.support.dubbo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.ibase4j.core.support.spring.data.redis.ObjectRedisSerializer;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 业务逻辑层基类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseProviderImpl<T extends Serializable> {

	@Autowired
	private KeyGenerator keyGenerator;
	@Autowired
	private StringRedisSerializer keySerializer;
	@Autowired
	private ObjectRedisSerializer valueSerializer;

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
	private BaseProviderImpl<T> getService() {
		return ContextLoader.getCurrentWebApplicationContext().getBean(getClass());
	}

	/** 根据Id查询(默认类型T) */
	public PageInfo<T> getPage(Page<Integer> ids) {
		Page<T> page = new Page<T>(ids.getPageNum(), ids.getPageSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			page.clear();
			BaseProviderImpl<T> provider = getService();
			for (Integer id : ids) {
				page.add(provider.queryById(id));
			}
		}
		return new PageInfo<T>(page);
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> PageInfo<K> getPage(Page<Integer> ids, Class<K> cls) {
		Page<K> page = new Page<K>(ids.getPageNum(), ids.getPageSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			page.clear();
			BaseProviderImpl<T> provider = getService();
			for (Integer id : ids) {
				T t = provider.queryById(id);
				K k = null;
				try {
					k = cls.newInstance();
				} catch (Exception e1) {
				}
				if (k != null) {
					try {
						PropertyUtils.copyProperties(k, t);
					} catch (Exception e) {
					}
					page.add(k);
				}
			}
		}
		return new PageInfo<K>(page);
	}

	/** 根据Id查询(默认类型T) */
	public List<T> getList(List<Integer> ids) {
		List<T> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (Integer id : ids) {
				list.add(getService().queryById(id));
			}
		}
		return list;
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> List<K> getList(List<Integer> ids, Class<K> cls) {
		List<K> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (Integer id : ids) {
				T t = getService().queryById(id);
				K k = null;
				try {
					k = cls.newInstance();
				} catch (Exception e1) {
				}
				if (k != null) {
					try {
						PropertyUtils.copyProperties(k, t);
					} catch (Exception e) {
					}
					list.add(k);
				}
			}
		}
		return list;
	}

	@Transactional
	public void delete(Integer id, Integer userId) {
		try {
			T t = queryById(id);
			t.getClass().getMethod("setEnable", Integer.class).invoke(t, 0);
			t.getClass().getMethod("setUpdateTime", Date.class).invoke(t, new Date());
			t.getClass().getMethod("setUpdateBy", Integer.class).invoke(t, userId);
			getMapper().getClass().getMethod("updateByPrimaryKey", t.getClass()).invoke(getMapper(), t);
			String key = keyGenerator.generate(this, getClass().getMethod("delete", Integer.class), id)
					.toString();
			RedisUtil.set(keySerializer.serialize(key), valueSerializer.serialize(t));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	public T update(T record) {
		try {
			record.getClass().getMethod("setEnable", Integer.class).invoke(record, 1);
			if (record.getClass().getMethod("getId").invoke(record) != null) {
				record.getClass().getMethod("setUpdateTime", Date.class).invoke(record, new Date());
				getMapper().getClass().getMethod("updateByPrimaryKey", record.getClass()).invoke(getMapper(), record);
			} else {
				record.getClass().getMethod("setCreateTime", Date.class).invoke(record, new Date());
				getMapper().getClass().getMethod("insert", record.getClass()).invoke(getMapper(), record);
			}
			String key = keyGenerator.generate(this, getClass().getMethod("update", record.getClass()), record)
					.toString();
			RedisUtil.set(keySerializer.serialize(key), valueSerializer.serialize(record));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public T queryById(Integer id) {
		try {
			String key = keyGenerator.generate(this, getClass().getMethod("queryById", Integer.class), id)
					.toString();
			byte[] value = RedisUtil.get(keySerializer.serialize(key), true);
			if (value != null) {
				return (T) valueSerializer.deserialize(value);
			}
			T t = (T) getMapper().getClass().getMethod("selectByPrimaryKey", Integer.class).invoke(getMapper(), id);
			RedisUtil.set(keySerializer.serialize(key), valueSerializer.serialize(t));
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	protected abstract Object getMapper();
}
