package org.shjr.iplat.core.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonson.xu on 10/30/14.
 */
public class Request2ListUtils {

	private static Integer paramSize(Set<Method> methodSet, Map<String, String[]> stringMap) {
		Integer size = 0;
		for (Method method : methodSet) {
			String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
			Integer tempSize = 0;
			if (stringMap.containsKey(key)) {
				tempSize = stringMap.get(key).length;
			}
			if (tempSize > size)
				size = tempSize;
		}
		return size;
	}

	public static <K> List<K> covert(Class<K> T, HttpServletRequest request) {
		try {

			List<K> objectList = new LinkedList<K>();
			// Object obj = T.newInstance();
			// 获取类的方法集合
			Set<Method> methodSet = get_declared_methods(T);
			Map<String, String[]> stringMap = request.getParameterMap();
			Integer valueSize = paramSize(methodSet, stringMap);
			System.out.println(T.getName() + " Max Length:" + valueSize);
			for (int i = 0; i < valueSize; i++) {
				K object = T.newInstance();
				for (Method method : methodSet) {
					String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
					String[] value = stringMap.get(key);
					if (value != null && i < value.length) {
						Class<?>[] type = method.getParameterTypes();
						Object[] param_value = convert_param_type(type, value[i]);
						method.invoke(object, param_value);
					}
				}
				objectList.add(object);
			}
			return objectList;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 取自定义Set方法
	 *
	 * @param T
	 * @return
	 */
	private static <T> Set<Method> get_declared_methods(Class<T> T) {
		Method[] methods = T.getDeclaredMethods();
		Set<Method> methodSet = new HashSet<Method>();
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				methodSet.add(method);
			}
		}
		return methodSet;
	}

	/**
	 * @param type
	 * @param value
	 * @return 转换参数类型
	 */
	private static Object[] convert_param_type(Class<?>[] type, Object value) {
		Object[] objects = new Object[type.length];
		int index = 0;
		for (Class<?> c : type) {
			if (value == null || value.toString().equals("")) {
				objects[index] = null;
				continue;
			}
			if (c.getName().equals("int") || c.getName().equals(Integer.class.getName())) {
				objects[index] = Integer.parseInt(value.toString());
			} else if (c.getName().equals(String.class.getName())) {
				objects[index] = value.toString();
			} else if (c.getName().equals("double") || c.getName().equals(Double.class.getName())) {
				objects[index] = Double.parseDouble(value.toString());
			} else if (c.getName().equals(Date.class.getName())) {
				String[] date_format = date_format_string();
				for (String date_format_str : date_format) {
					DateFormat format1 = new SimpleDateFormat(date_format_str);
					try {
						objects[index] = format1.parse(value.toString());
						break;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} else if (c.getName().equals(Float.class.getName()) || c.getName().equals("float")) {
				objects[index] = Float.parseFloat(value.toString());
			} else if (c.getName().equals(BigDecimal.class.getCanonicalName())) {
				objects[index] = new BigDecimal(value.toString());
			} else {
				// new Throwable("发现未定义的类型！类型名：" +
				// c.getName()).printStackTrace();
			}
			index++;
		}
		return objects;
	}

	/*
	 * 字符串日期格式集合
	 */
	private static String[] date_format_string() {
		String[] date_format = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
		return date_format;
	}
}
