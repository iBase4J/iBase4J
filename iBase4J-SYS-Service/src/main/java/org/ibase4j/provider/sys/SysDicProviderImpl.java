package org.ibase4j.provider.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.dao.generator.SysDicIndexMapper;
import org.ibase4j.dao.generator.SysDicMapper;
import org.ibase4j.dao.sys.SysDicExpandMapper;
import org.ibase4j.model.generator.SysDic;
import org.ibase4j.model.generator.SysDicIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@DubboService(interfaceClass = SysDicProvider.class)
public class SysDicProviderImpl extends BaseProviderImpl<SysDic> implements SysDicProvider {
	@Autowired
	private SysDicMapper dicMapper;
	@Autowired
	private SysDicIndexMapper dicIndexMapper;
	@Autowired
	private SysDicExpandMapper dicExpandMapper;

	@Transactional
	@CachePut(value = "sysDicIndex")
	public void updateDicIndex(SysDicIndex record) {
		if (record.getId() == null) {
			dicIndexMapper.insert(record);
		} else {
			dicIndexMapper.updateByPrimaryKey(record);
		}
	}

	@Transactional
	@CachePut(value = "sysDic")
	public void updateDic(SysDic record) {
		if (record.getId() == null) {
			dicMapper.insert(record);
		} else {
			dicMapper.updateByPrimaryKey(record);
		}
	}

	@Transactional
	@CacheEvict(value = "sysDic")
	public void deleteDic(Integer id) {
		dicMapper.deleteByPrimaryKey(id);
	}

	@Cacheable(value = "sysDicIndex")
	public SysDicIndex queryDicIndexById(Integer id) {
		return dicIndexMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value = "sysDic")
	public SysDic queryDicById(Integer id) {
		return dicMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value = "sysDics")
	public Map<String, Map<String, String>> getAllDic() {
		List<SysDicIndex> sysDicIndexs = dicIndexMapper.selectAll();
		Map<Integer, String> dicIndexMap = InstanceUtil.newHashMap();
		for (SysDicIndex sysDicIndex : sysDicIndexs) {
			dicIndexMap.put(sysDicIndex.getId(), sysDicIndex.getKey());
		}
		List<SysDic> sysDics = dicMapper.selectAll();
		Map<String, Map<String, String>> resultMap = InstanceUtil.newHashMap();
		for (SysDic sysDic : sysDics) {
			String key = dicIndexMap.get(sysDic.getIndexId());
			if (resultMap.get(key) == null) {
				Map<String, String> dicMap = InstanceUtil.newHashMap();
				resultMap.put(key, dicMap);
			}
			resultMap.get(key).put(sysDic.getCode(), sysDic.getCodeText());
		}
		return resultMap;
	}

	@Cacheable(value = "sysDicMap")
	public Map<String, String> queryDicByDicIndexKey(String key) {
		return InstanceUtil.getBean(SysDicProvider.class).getAllDic().get(key);
	}

	public PageInfo<SysDicIndex> queryDicIndex(Map<String, Object> params) {
		startPage(params);
		Page<Integer> ids = dicExpandMapper.queryDicIndex(params);
		Page<SysDicIndex> page = new Page<SysDicIndex>(ids.getPageNum(), ids.getPageSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			page.clear();
			SysDicProvider provider = InstanceUtil.getBean(getClass());
			for (Integer id : ids) {
				page.add(provider.queryDicIndexById(id));
			}
		}
		return new PageInfo<SysDicIndex>(page);
	}

	public PageInfo<SysDic> queryDic(Map<String, Object> params) {
		startPage(params);
		return getPage(dicExpandMapper.queryDic(params));
	}

	public void deleteDicIndex(Integer id) {
		Map<String, Object> params = InstanceUtil.newHashMap();
		params.put("index_id", id);
		List<Integer> ids = dicExpandMapper.queryDic(params);
		if (ids.size() > 0) {
			throw new BusinessException();
		}
		dicIndexMapper.deleteByPrimaryKey(id);
	}
}
