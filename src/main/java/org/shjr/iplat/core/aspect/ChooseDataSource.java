package org.shjr.iplat.core.aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ChooseDataSource extends AbstractRoutingDataSource {
	public static Map<String, List<String>> METHODTYPE = new HashMap<String, List<String>>();

	@Override
	protected Object determineCurrentLookupKey() {
		return HandleDataSource.getDataSource();
	}

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
