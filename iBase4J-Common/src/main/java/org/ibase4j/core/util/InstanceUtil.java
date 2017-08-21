package org.ibase4j.core.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.exception.DataParseException;
import org.ibase4j.core.exception.InstanceException;

import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * 实例辅助类
 * 
 * @author ShenHuaJie
 * @since 2012-07-18
 */
public final class InstanceUtil {
	private InstanceUtil() {
	}

	/** 实例化并复制属性 */
	public static final <T> T to(Object orig, Class<T> clazz) {
		T bean = null;
		try {
			bean = clazz.newInstance();
			PropertyUtils.copyProperties(bean, orig);
		} catch (Exception e) {
		}
		return bean;
	}

	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	public static void transMap2Bean(Map<String, Object> map, Object obj) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}
		return;
	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {
		Map<String, Object> map = newHashMap();
		if (obj == null) {
			return map;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}

	/**
	 * @param oldBean
	 * @param newBean
	 * @return
	 */
	public static <T> T getDiff(T oldBean, T newBean) {
		if (oldBean == null && newBean != null) {
			return newBean;
		} else if (newBean == null) {
			return null;
		} else {
			Class<?> cls1 = oldBean.getClass();
			try {
				@SuppressWarnings("unchecked")
				T object = (T) cls1.newInstance();
				BeanInfo beanInfo = Introspector.getBeanInfo(cls1);
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					String key = property.getName();
					// 过滤class属性
					if (!key.equals("class")) {
						// 得到property对应的getter方法
						Method getter = property.getReadMethod();
						// 得到property对应的setter方法
						Method setter = property.getWriteMethod();
						Object oldValue = getter.invoke(oldBean);
						Object newValue = getter.invoke(newBean);
						if (newValue != null) {
							if (oldValue == null) {
								setter.invoke(object, newValue);
							} else if (oldValue != null && !newValue.equals(oldValue)) {
								setter.invoke(object, newValue);
							}
						}
					}
				}
				return object;
			} catch (Exception e) {
				throw new DataParseException(e);
			}
		}
	}

	/**
	 * Return the specified class. Checks the ThreadContext classloader first,
	 * then uses the System classloader. Should replace all calls to
	 * <code>Class.forName( claz )</code> (which only calls the System class
	 * loader) when the class might be in a different classloader (e.g. in a
	 * webapp).
	 * 
	 * @param clazz
	 *            the name of the class to instantiate
	 * @return the requested Class object
	 */
	public static final Class<?> getClass(String clazz) {
		/**
		 * Use the Thread context classloader if possible
		 */
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			if (loader != null) {
				return Class.forName(clazz, true, loader);
			}
			/**
			 * Thread context classloader isn't working out, so use system
			 * loader.
			 */
			return Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			throw new InstanceException(e);
		}
	}

	/**
	 * 封装实体
	 * 
	 * @param cls
	 *            实体类
	 * @param list
	 *            实体Map集合
	 * @return
	 */
	public static final <E> List<E> getInstanceList(Class<E> cls, List<?> list) {
		List<E> resultList = newArrayList();
		E object = null;
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Map<?, ?> map = (Map<?, ?>) iterator.next();
			object = newInstance(cls, map);
			resultList.add(object);
		}
		return resultList;
	}

	/**
	 * 封装实体
	 * 
	 * @param cls
	 *            实体类
	 * @param list
	 *            数据查询结果集
	 * @return
	 */
	public static final <E> List<E> getInstanceList(Class<E> cls, ResultSet rs) {
		List<E> resultList = newArrayList();
		try {
			E object = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			while (rs.next()) {
				object = cls.newInstance();
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i].getName();
					PropertyUtils.setProperty(object, fieldName, rs.getObject(fieldName));
				}
				resultList.add(object);
			}
		} catch (Exception e) {
			throw new InstanceException(e);
		}
		return resultList;
	}

	/**
	 * 新建实例
	 * 
	 * @param cls
	 *            实体类
	 * @param list
	 *            实体属性Map
	 * @return
	 */
	public static final <E> E newInstance(Class<E> cls, Map<String, ?> map) {
		E object = null;
		try {
			object = cls.newInstance();
			BeanUtils.populate(object, map);
		} catch (Exception e) {
			throw new InstanceException(e);
		}
		return object;
	}

	/**
	 * Return a new instance of the given class. Checks the ThreadContext
	 * classloader first, then uses the System classloader. Should replace all
	 * calls to <code>Class.forName( claz ).newInstance()</code> (which only
	 * calls the System class loader) when the class might be in a different
	 * classloader (e.g. in a webapp).
	 * 
	 * @param clazz
	 *            the name of the class to instantiate
	 * @return an instance of the specified class
	 */
	public static final Object newInstance(String clazz) {
		try {
			return getClass(clazz).newInstance();
		} catch (Exception e) {
			throw new InstanceException(e);
		}
	}

	public static final <K> K newInstance(Class<K> cls, Object... args) {
		try {
			Class<?>[] argsClass = null;
			if (args != null) {
				argsClass = new Class[args.length];
				for (int i = 0, j = args.length; i < j; i++) {
					argsClass[i] = args[i].getClass();
				}
			}
			Constructor<K> cons = cls.getConstructor(argsClass);
			return cons.newInstance(args);
		} catch (Exception e) {
			throw new InstanceException(e);
		}
	}

	public static Map<String, Class<?>> clazzMap = new HashMap<String, Class<?>>();

	/**
	 * 新建实例
	 * 
	 * @param className
	 *            类名
	 * @param args
	 *            构造函数的参数
	 * @return 新建的实例
	 */
	public static final Object newInstance(String className, Object... args) {
		try {
			Class<?> newoneClass = clazzMap.get(className);
			if (newoneClass == null) {
				newoneClass = Class.forName(className);
				clazzMap.put(className, newoneClass); // 缓存class对象
			}
			return newInstance(newoneClass, args);
		} catch (Exception e) {
			throw new InstanceException(e);
		}
	}

	public static Map<String, MethodAccess> methodMap = new HashMap<String, MethodAccess>();

	/**
	 * 执行某对象方法
	 * 
	 * @param owner
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数
	 * @return 方法返回值
	 */
	public static final Object invokeMethod(Object owner, String methodName, Object... args) {
		Class<?> ownerClass = owner.getClass();
		String key = null;
		if (args != null) {
			Class<?>[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; i++) {
				if (args[i] != null) {
					argsClass[i] = args[i].getClass();
				}
			}
			key = ownerClass + "_" + methodName + "_" + StringUtils.join(argsClass, ","); // 用于区分重载的方法
		} else {
			key = ownerClass + "_" + methodName; // 用于区分重载的方法
		}
		MethodAccess methodAccess = methodMap.get(key);
		if (methodAccess == null) {
			methodAccess = MethodAccess.get(ownerClass);
			methodMap.put(key, methodAccess); // 缓存Method对象
		}
		return methodAccess.invoke(owner, methodName, args);
	}

	/**
	 * Constructs an empty ArrayList.
	 */
	public static final <E> ArrayList<E> newArrayList() {
		return new ArrayList<E>();
	}

	/**
	 * Constructs an empty ArrayList.
	 */
	@SuppressWarnings("unchecked")
	public static final <E> ArrayList<E> newArrayList(E... e) {
		ArrayList<E> list = new ArrayList<E>();
		Collections.addAll(list, e);
		return list;
	}

	/**
	 * Constructs an empty HashMap.
	 */
	public static final <k, v> HashMap<k, v> newHashMap() {
		return new HashMap<k, v>();
	}

	/**
	 * Constructs an empty HashSet.
	 */
	public static final <E> HashSet<E> newHashSet() {
		return new HashSet<E>();
	}

	/**
	 * Constructs an empty Hashtable.
	 */
	public static final <k, v> Hashtable<k, v> newHashtable() {
		return new Hashtable<k, v>();
	}

	/**
	 * Constructs an empty LinkedHashMap.
	 */
	public static final <k, v> LinkedHashMap<k, v> newLinkedHashMap() {
		return new LinkedHashMap<k, v>();
	}

	/**
	 * Constructs an empty LinkedHashSet.
	 */
	public static final <E> LinkedHashSet<E> newLinkedHashSet() {
		return new LinkedHashSet<E>();
	}

	/**
	 * Constructs an empty LinkedList.
	 */
	public static final <E> LinkedList<E> newLinkedList() {
		return new LinkedList<E>();
	}

	/**
	 * Constructs an empty TreeMap.
	 */
	public static final <k, v> TreeMap<k, v> newTreeMap() {
		return new TreeMap<k, v>();
	}

	/**
	 * Constructs an empty TreeSet.
	 */
	public static final <E> TreeSet<E> newTreeSet() {
		return new TreeSet<E>();
	}

	/**
	 * Constructs an empty Vector.
	 */
	public static final <E> Vector<E> newVector() {
		return new Vector<E>();
	}

	/**
	 * Constructs an empty WeakHashMap.
	 */
	public static final <k, v> WeakHashMap<k, v> newWeakHashMap() {
		return new WeakHashMap<k, v>();
	}

	/**
	 * Constructs an HashMap.
	 */
	public static final <k, v> HashMap<k, v> newHashMap(k key, v value) {
		HashMap<k, v> map = newHashMap();
		map.put(key, value);
		return map;
	}

	/**
	 * Constructs an LinkedHashMap.
	 */
	public static final <k, v> LinkedHashMap<k, v> newLinkedHashMap(k key, v value) {
		LinkedHashMap<k, v> map = newLinkedHashMap();
		map.put(key, value);
		return map;
	}

	/**
	 * Constructs an empty ConcurrentHashMap.
	 */
	public static final <k, v> ConcurrentHashMap<k, v> newConcurrentHashMap() {
		return new ConcurrentHashMap<k, v>();
	}
}
