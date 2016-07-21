package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.dao.generator.SysDicIndexMapper;
import org.ibase4j.dao.generator.SysDicMapper;
import org.ibase4j.model.generator.SysDic;
import org.ibase4j.model.generator.SysDicIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysDicService {
    @Autowired
    private SysDicMapper dicMapper;
    @Autowired
    private SysDicIndexMapper dicIndexMapper;

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
    @CachePut(cacheNames = "sysDic")
    public void updateDic(SysDic record) {
        if (record.getId() == null) {
            dicMapper.insert(record);
        } else {
            dicMapper.updateByPrimaryKey(record);
        }
    }

    @Transactional
    @CacheEvict(cacheNames = "sysDic")
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

    /**
     * @param string
     * @return
     */
    public Map<String, String> queryDicByDicIndexKey(String key) {
        return InstanceUtil.getBean(SysDicService.class).getAllDic().get(key);
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
}
