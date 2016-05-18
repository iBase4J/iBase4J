package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.facade.sys.SysDicFacade;
import org.ibase4j.mybatis.generator.dao.SysDicIndexMapper;
import org.ibase4j.mybatis.generator.dao.SysDicMapper;
import org.ibase4j.mybatis.generator.model.SysDic;
import org.ibase4j.mybatis.generator.model.SysDicIndex;
import org.ibase4j.mybatis.sys.dao.SysDicExpandMapper;
import org.ibase4j.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

@DubboService(interfaceClass = SysDicFacade.class)
public class SysDicService extends BaseService implements SysDicFacade {
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

	public void query() {
		dicExpandMapper.queryDicByDicIndexKey(null);
	}

	@CacheEvict(value = "sysDics", allEntries = true)
	public void clearCache() {
	}

	@Cacheable(value = "sysDics")
	public Map<String, Map<String, String>> queryAllDic() {
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
		return queryAllDic().get(key);
	}
}
