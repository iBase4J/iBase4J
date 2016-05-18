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
	@CachePut(cacheNames = "sysDicIndex")
	public void updateDicIndex(SysDicIndex record) {
		if (record.getId() == null) {
			dicIndexMapper.insert(record);
		} else {
			dicIndexMapper.updateByPrimaryKey(record);
		}
	}

	@Transactional
	@CachePut(cacheNames = { "sysDic", "sysDics" })
	public void updateDic(SysDic record) {
		if (record.getId() == null) {
			dicMapper.insert(record);
		} else {
			dicMapper.updateByPrimaryKey(record);
		}
	}

	@Transactional
	@CacheEvict(cacheNames = { "sysDic", "sysDics" })
	public void deleteDic(Integer id) {
		dicMapper.deleteByPrimaryKey(id);
	}

	@Cacheable("sysDicIndex")
	public SysDicIndex queryDicIndexById(Integer id) {
		return dicIndexMapper.selectByPrimaryKey(id);
	}

	@Cacheable("sysDic")
	public SysDic queryDicById(Integer id) {
		return dicMapper.selectByPrimaryKey(id);
	}

	@Cacheable("sysDics")
	public Map<String, String> queryDicByDicIndexKey(String key) {
		List<SysDic> sysDics = dicExpandMapper.queryDicByDicIndexKey(key);
		Map<String, String> dicMap = InstanceUtil.newHashMap();
		for (SysDic sysDic : sysDics) {
			dicMap.put(sysDic.getCode(), sysDic.getCodeText());
		}
		return dicMap;
	}
}
