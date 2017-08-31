package org.ibase4j.core.base;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.util.CacheUtil;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.ExceptionUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 业务逻辑层基类<br/>
 * 继承基类后必须配置CacheConfig(cacheNames="")
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseService<T extends BaseModel> implements ApplicationContextAware {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired
	protected BaseMapper<T> mapper;
	protected ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	int maxThread = PropertiesUtil.getInt("db.reader.list.maxThread", 500);
	int threadSleep = PropertiesUtil.getInt("db.reader.list.threadWait", 5);
	ExecutorService executorService = Executors.newFixedThreadPool(maxThread);

	/** 分页查询 */
	public static Page<Long> getPage(Map<String, Object> params) {
		Integer current = 1;
		Integer size = 10;
		String orderBy = "id_";
		if (DataUtil.isNotEmpty(params.get("pageNum"))) {
			current = Integer.valueOf(params.get("pageNum").toString());
		}
		if (DataUtil.isNotEmpty(params.get("pageIndex"))) {
			current = Integer.valueOf(params.get("pageIndex").toString());
		}
		if (DataUtil.isNotEmpty(params.get("pageSize"))) {
			size = Integer.valueOf(params.get("pageSize").toString());
		}
		if (DataUtil.isNotEmpty(params.get("orderBy"))) {
			orderBy = (String) params.get("orderBy");
			params.remove("orderBy");
		}
		if (size == -1) {
			Page<Long> page = new Page<Long>();
			page.setOrderByField(orderBy);
			page.setAsc(false);
			return page;
		}
		Page<Long> page = new Page<Long>(current, size, orderBy);
		page.setAsc(false);
		return page;
	}

	/** 根据Id查询(默认类型T) */
	public Page<T> getPage(final Page<Long> ids) {
		if (ids != null) {
			Page<T> page = new Page<T>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			final List<T> records = InstanceUtil.newArrayList();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				records.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						try {
							records.set(index, queryById(ids.getRecords().get(index)));
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < records.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
			page.setRecords(records);
			return page;
		}
		return new Page<T>();
	}

	/** 根据Id查询(默认类型T) */
	public Page<Map<String, Object>> getPageMap(final Page<Long> ids) {
		if (ids != null) {
			Page<Map<String, Object>> page = new Page<Map<String, Object>>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			final List<Map<String, Object>> records = InstanceUtil.newArrayList();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				records.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						try {
							records.set(index, InstanceUtil.transBean2Map(queryById(ids.getRecords().get(index))));
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < records.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
			page.setRecords(records);
			return page;
		}
		return new Page<Map<String, Object>>();
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> Page<K> getPage(final Page<Long> ids, final Class<K> cls) {
		if (ids != null) {
			Page<K> page = new Page<K>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			final List<K> records = InstanceUtil.newArrayList();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				records.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						try {
							T t = queryById(ids.getRecords().get(index));
							K k = InstanceUtil.to(t, cls);
							records.set(index, k);
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < records.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
			page.setRecords(records);
			return page;
		}
		return new Page<K>();
	}

	/** 根据Id查询(默认类型T) */
	public List<T> getList(final List<Long> ids) {
		final List<T> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (int i = 0; i < ids.size(); i++) {
				list.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			for (int i = 0; i < ids.size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						try {
							list.set(index, queryById(ids.get(index)));
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < list.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
		}
		return list;
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> List<K> getList(final List<Long> ids, final Class<K> cls) {
		final List<K> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (int i = 0; i < ids.size(); i++) {
				list.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			for (int i = 0; i < ids.size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						try {
							T t = queryById(ids.get(index));
							K k = InstanceUtil.to(t, cls);
							list.set(index, k);
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < list.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
		}
		return list;
	}

	@Transactional
	public void del(Long id, Long userId) {
		try {
			T record = this.queryById(id);
			record.setEnable(0);
			record.setUpdateTime(new Date());
			record.setUpdateBy(userId);
			mapper.updateById(record);
			try {
				CacheUtil.getCache().set(getCacheKey(id), record);
			} catch (Exception e) {
				logger.error(Constants.Exception_Head, e);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			mapper.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		try {
			CacheUtil.getCache().del(getCacheKey(id));
		} catch (Exception e) {
			logger.error(Constants.Exception_Head, e);
		}
	}

	@Transactional
	public Integer deleteByMap(Map<String, Object> columnMap) {
		return mapper.deleteByMap(columnMap);
	}

	@Transactional
	public Integer deleteByEntity(T t) {
		Wrapper<T> wrapper = new EntityWrapper<T>(t);
		return mapper.delete(wrapper);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public T update(T record) {
		try {
			record.setUpdateTime(new Date());
			if (record.getId() == null) {
				record.setCreateTime(new Date());
				mapper.insert(record);
				try {
					CacheUtil.getCache().set(getCacheKey(record.getId()), record);
				} catch (Exception e) {
					logger.error(Constants.Exception_Head, e);
				}
			} else {
				String lockKey = getLockKey("U" + record.getId());
				if (CacheUtil.getLock(lockKey)) {
					try {
						T org = null;
						String key = getCacheKey(record.getId());
						try {
							org = (T) CacheUtil.getCache().get(key);
						} catch (Exception e) {
							logger.error(Constants.Exception_Head, e);
						}
						if (org == null) {
							org = mapper.selectById(record.getId());
						}

						T update = InstanceUtil.getDiff(org, record);
						update.setId(record.getId());
						mapper.updateById(update);
						record = mapper.selectById(record.getId());
						try {
							CacheUtil.getCache().set(key, record);
						} catch (Exception e) {
							logger.error(Constants.Exception_Head, e);
						}
					} finally {
						CacheUtil.unlock(lockKey);
					}
				} else {
					throw new RuntimeException("数据不一致!请刷新页面重新编辑!");
				}
			}
		} catch (DuplicateKeyException e) {
			logger.error(Constants.Exception_Head, e);
			throw new RuntimeException("已经存在相同的配置.");
		} catch (Exception e) {
			logger.error(Constants.Exception_Head, e);
			throw new RuntimeException(ExceptionUtil.getStackTraceAsString(e));
		}
		return record;
	}

	protected void sleep(int millis) {
		try {
			Thread.sleep(RandomUtils.nextLong(10, millis));
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}

	public T queryById(Long id) {
		return queryById(id, 1);
	}

	@SuppressWarnings("unchecked")
	private T queryById(Long id, int times) {
		String key = getCacheKey(id);
		T record = null;
		try {
			record = (T) CacheUtil.getCache().get(key);
		} catch (Exception e) {
			logger.error(Constants.Exception_Head, e);
		}
		if (record == null) {
			String lockKey = getLockKey(id);
			if (CacheUtil.getLock(lockKey)) {
				try {
					record = mapper.selectById(id);
					try {
						CacheUtil.getCache().set(key, record);
					} catch (Exception e) {
						logger.error(Constants.Exception_Head, e);
					}
				} finally {
					CacheUtil.unlock(lockKey);
				}
			} else {
				if (times > 3) {
					return mapper.selectById(id);
				}
				logger.debug(getClass().getSimpleName() + ":" + id + " retry queryById.");
				sleep(20);
				return queryById(id, times + 1);
			}
		}
		return record;
	}

	public Page<T> query(Map<String, Object> params) {
		Page<Long> page = getPage(params);
		page.setRecords(mapper.selectIdPage(page, params));
		return getPage(page);
	}

	public List<T> queryList(Map<String, Object> params) {
		List<Long> ids = mapper.selectIdPage(params);
		List<T> list = getList(ids);
		return list;
	}

	protected <P> Page<P> query(Map<String, Object> params, Class<P> cls) {
		Page<Long> page = getPage(params);
		page.setRecords(mapper.selectIdPage(page, params));
		return getPage(page, cls);
	}

	public Page<Map<String, Object>> queryMap(Map<String, Object> params) {
		Page<Long> page = getPage(params);
		page.setRecords(mapper.selectIdPage(page, params));
		return getPageMap(page);
	}

	public T selectOne(T entity) {
		return mapper.selectOne(entity);
	}

	public List<T> selectList(Wrapper<T> entity) {
		return mapper.selectList(entity);
	}

	/** 获取缓存键值 */
	protected String getCacheKey(Object id) {
		String cacheName = getCacheKey();
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
	}

	/** 获取缓存键值 */
	protected String getLockKey(Object id) {
		String cacheName = getCacheKey();
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":LOCK:").append(id).toString();
	}

	/**
	 * @return
	 */
	private String getCacheKey() {
		Class<?> cls = getClass();
		String cacheName = Constants.cacheKeyMap.get(cls);
		if (StringUtils.isBlank(cacheName)) {
			CacheConfig cacheConfig = cls.getAnnotation(CacheConfig.class);
			if (cacheConfig != null && ArrayUtils.isNotEmpty(cacheConfig.cacheNames())) {
				cacheName = cacheConfig.cacheNames()[0];
			} else {
				cacheName = getClass().getName();
			}
			Constants.cacheKeyMap.put(cls, cacheName);
		}
		return cacheName;
	}
}
