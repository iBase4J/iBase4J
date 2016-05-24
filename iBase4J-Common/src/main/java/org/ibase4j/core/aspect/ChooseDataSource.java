package org.ibase4j.core.aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 获取数据源
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:17:16
 */
public class ChooseDataSource extends AbstractRoutingDataSource {
	public static Map<String, List<String>> METHODTYPE = new HashMap<String, List<String>>();

	// 获取数据源名称
	protected Object determineCurrentLookupKey() {
		return HandleDataSource.getDataSource();
	}

	// 设置方法名前缀对应的数据源
	public void setMethodType(Map<String, String> map) {
		for (String key : map.keySet()) {
			List<String> v = new ArrayList<String>();
			String[] types = map.get(key).split(",");
			for (String type : types) {
				if (StringUtils.isNotBlank(type)) {
					v.add(type);
				}
			}
			METHODTYPE.put(key, v);
		}
	}
}
