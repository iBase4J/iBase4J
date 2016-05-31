package org.ibase4j.core.support.dubbo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.springframework.cache.annotation.Cacheable;
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
		Page<T> page = new Page<T>();
		try {
			PropertyUtils.copyProperties(page, ids);
		} catch (Exception e) {
		}
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
		Page<K> page = new Page<K>();
		try {
			PropertyUtils.copyProperties(page, ids);
		} catch (Exception e) {
		}
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

	@Cacheable
	public abstract T queryById(Integer id);
}
