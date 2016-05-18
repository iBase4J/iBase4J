package org.ibase4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

@SuppressWarnings({ "serial", "unchecked" })
public class CombinationTest {

	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<>();

		list.add(new HashMap<String, Object>() {
			{
				put("text", "颜色");
				put("id", "1");
				put("val", new HashMap<String, Object>() {
					{
						put("101", "红色");
						put("102", "绿色");
					}
				});
			}
		});

		list.add(new HashMap<String, Object>() {
			{
				put("text", "尺寸");
				put("id", "2");
				put("val", new HashMap<String, Object>() {
					{
						put("201", "sl");
						put("202", "ml");
						put("203", "xml");
					}
				});
			}
		});

		list.add(new HashMap<String, Object>() {
			{
				put("text", "重量");
				put("id", "3");
				put("val", new HashMap<String, Object>() {
					{
						put("301", "1");
						put("302", "5");
						put("303", "10");
					}
				});
			}
		});

		List<String> keyList = new ArrayList<String>();
		Map<String, Object> m = (Map<String, Object>) list.get(0).get("val");
		for (Map.Entry<String, Object> map2 : m.entrySet()) {
			keyList.addAll(next(list, map2.getValue().toString(), 1));
		}
		System.out.println(JSON.toJSONString(keyList));
	}

	public static List<String> next(List<Map<String, Object>> list, String sb, Integer index) {
		List<String> keyList = new ArrayList<String>();
		Map<String, Object> m = (Map<String, Object>) list.get(index).get("val");
		for (Map.Entry<String, Object> map2 : m.entrySet()) {
			String v = new StringBuilder(sb).append("-").append(map2.getValue()).toString();
			if (list.size() > index + 1) {
				keyList.addAll(next(list, v, index + 1));
			} else {
				keyList.add(v);
			}
		}
		return keyList;
	}
}
