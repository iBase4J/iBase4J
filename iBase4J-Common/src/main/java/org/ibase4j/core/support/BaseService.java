package org.ibase4j.core.support;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.context.ContextLoader;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/** 业务逻辑层基类 */
public abstract class BaseService<T> {

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
	private BaseService<T> getService() {
		return ContextLoader.getCurrentWebApplicationContext().getBean(this.getClass());
	}

	/** 根据Id查询(默认类型T) */
	public Page<T> getPage(Page<Integer> ids) {
		Page<T> page = new Page<T>();
		try {
			PropertyUtils.copyProperties(page, ids);
		} catch (Exception e) {
		}
		if (ids != null) {
			for (Integer id : ids) {
				page.add(getService().queryById(id));
			}
		}
		return page;
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> Page<K> getPage(Page<Integer> ids, Class<K> cls) {
		Page<K> page = new Page<K>();
		try {
			PropertyUtils.copyProperties(page, ids);
		} catch (Exception e) {
		}
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
					page.add(k);
				}
			}
		}
		return page;
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

	@Cacheable
	public abstract T queryById(Integer id);
}
