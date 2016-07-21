package org.ibase4j.core.util;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ShenHueJie
 * @version 2016年5月20日 下午3:28:02
 */
public final class Request2ListUtil {
	private Request2ListUtil() {
	}

	private static final Integer paramSize(Set<Method> methodSet, Map<String, String[]> stringMap) {
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

	public static final <K> List<K> covert(Class<K> T, HttpServletRequest request) {
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
						Object[] param_value = new Object[] { TypeParseUtil.convert(value[i], type[0], null) };
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
	private static final <T> Set<Method> get_declared_methods(Class<T> T) {
		Method[] methods = T.getDeclaredMethods();
		Set<Method> methodSet = new HashSet<Method>();
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				methodSet.add(method);
			}
		}
		return methodSet;
	}
}
