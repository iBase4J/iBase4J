package org.ibase4j.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ibase4j.mapper.SysMenuMapper;
import org.ibase4j.model.SysDic;
import org.ibase4j.model.SysMenu;
import org.ibase4j.service.SysDicService;
import org.ibase4j.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;
import top.ibase4j.core.util.InstanceUtil;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "sysMenu")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuMapper> implements SysMenuService {
    @Autowired
    private SysDicService sysDicService;

    @Override
    public SysMenu queryById(Long id) {
        SysMenu sysMenu = super.queryById(id);
        if (sysMenu != null) {
            if (sysMenu.getParentId() != null && sysMenu.getParentId() != 0) {
                SysMenu parent = super.queryById(sysMenu.getParentId());
                if (parent != null) {
                    sysMenu.setParentName(parent.getMenuName());
                } else {
                    sysMenu.setParentId(null);
                }
            }
        }
        return sysMenu;
    }

    @Override
    public List<SysMenu> queryList(Map<String, Object> params) {
        params.put("orderBy", "parent_id,sort_no");
        List<SysMenu> pageInfo = super.queryList(params);
        Map<String, String> menuTypeMap = sysDicService.queryDicByType("MENUTYPE");
        Map<Long, Integer> leafMap = InstanceUtil.newHashMap();
        List<SysMenu> resultList = InstanceUtil.newArrayList();
        Map<Long, List<SysMenu>> map = InstanceUtil.newHashMap();
        for (SysMenu sysMenu : pageInfo) {
            if (sysMenu != null) {
                if (sysMenu.getMenuType() != null) {
                    sysMenu.setTypeName(menuTypeMap.get(sysMenu.getMenuType().toString()));
                }
                if (leafMap.get(sysMenu.getParentId()) == null) {
                    leafMap.put(sysMenu.getParentId(), 0);
                }
                leafMap.put(sysMenu.getParentId(), leafMap.get(sysMenu.getParentId()) + 1);
                if (map.get(sysMenu.getParentId()) == null) {
                    map.put(sysMenu.getParentId(), new ArrayList<SysMenu>());
                }
                map.get(sysMenu.getParentId()).add(sysMenu);
                if (sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
                    resultList.add(sysMenu);
                }
            }
        }
        for (SysMenu sysMenu : pageInfo) {
            if (sysMenu != null) {
                if (leafMap.get(sysMenu.getId()) != null && leafMap.get(sysMenu.getId()) > 0) {
                    sysMenu.setLeaf(0);
                }
                if (map.get(sysMenu.getId()) != null) {
                    resultList.addAll(resultList.indexOf(sysMenu) + 1, map.get(sysMenu.getId()));
                }
            }
        }

        return resultList;
    }

    @Override
    public List<Object> queryTreeList(Map<String, Object> params) {
        params.put("orderBy", "parent_id,sort_no");
        List<SysMenu> pageInfo = super.queryList(params);
        Map<String, String> menuTypeMap = sysDicService.queryDicByType("MENUTYPE");
        Map<String, Object> dicParam = InstanceUtil.newHashMap();
        dicParam.put("type", "CRUD");
        List<SysDic> sysDics = sysDicService.queryList(dicParam);
        Map<Long, Integer> leafMap = InstanceUtil.newHashMap();
        List<Object> resultList = InstanceUtil.newArrayList();
        Map<Long, List<SysMenu>> map = InstanceUtil.newHashMap();
        for (SysMenu sysMenu : pageInfo) {
            if (sysMenu != null) {
                if (sysMenu.getMenuType() != null) {
                    sysMenu.setTypeName(menuTypeMap.get(sysMenu.getMenuType().toString()));
                }
                if (leafMap.get(sysMenu.getParentId()) == null) {
                    leafMap.put(sysMenu.getParentId(), 0);
                }
                leafMap.put(sysMenu.getParentId(), leafMap.get(sysMenu.getParentId()) + 1);
                if (map.get(sysMenu.getParentId()) == null) {
                    map.put(sysMenu.getParentId(), new ArrayList<SysMenu>());
                }
                map.get(sysMenu.getParentId()).add(sysMenu);
                if (sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
                    resultList.add(sysMenu);
                }
            }
        }
        for (SysMenu sysMenu : pageInfo) {
            if (sysMenu != null) {
                if (map.get(sysMenu.getId()) != null) {
                    resultList.addAll(resultList.indexOf(sysMenu) + 1, map.get(sysMenu.getId()));
                }
            }
        }
        for (SysMenu sysMenu : pageInfo) {
            if (sysMenu != null) {
                if (leafMap.get(sysMenu.getId()) != null && leafMap.get(sysMenu.getId()) > 0) {
                    sysMenu.setLeaf(0);
                } else {
                    List<Map<String, Object>> dicMaps = InstanceUtil.newArrayList();
                    for (SysDic sysDic : sysDics) {
                        if (!"read".equals(sysDic.getCode())) {
                            Map<String, Object> dicMap = InstanceUtil.transBean2Map(sysDic);
                            dicMap.put("id", "D" + sysDic.getId());
                            dicMap.put("menuName", sysDic.getCodeText());
                            dicMap.put("parentId", sysMenu.getId().toString());
                            dicMaps.add(dicMap);
                        }
                    }
                    resultList.addAll(resultList.indexOf(sysMenu) + 1, dicMaps);
                }
            }
        }
        return resultList;
    }

    @Override
    public List<Map<String, String>> getPermissions() {
        return mapper.getPermissions();
    }
}
