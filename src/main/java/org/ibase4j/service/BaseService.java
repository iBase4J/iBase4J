package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 业务逻辑层基类 */
@SuppressWarnings("hiding")
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
    private BaseService<T> getProvider() {
        return InstanceUtil.getBean(getClass());
    }

    /** 根据Id查询(默认类型T) */
    public PageInfo<T> getPage(Page<Integer> ids) {
        if (ids != null) {
            Page<T> page = new Page<T>(ids.getPageNum(), ids.getPageSize());
            page.setTotal(ids.getTotal());
            page.clear();
            BaseService<T> provider = getProvider();
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
            BaseService<T> provider = getProvider();
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

    /**
     * @param id
     * @return
     */
    protected abstract T queryById(Integer id);
}
