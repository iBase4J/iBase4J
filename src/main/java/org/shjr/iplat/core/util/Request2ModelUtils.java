package org.shjr.iplat.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jonson.xu on 10/30/14.
 */
public class Request2ModelUtils {
    public static <K> K covert(Class<K> T, HttpServletRequest request) {
        try {
            K obj = T.newInstance();
            //获取类的方法集合
            Set<Method> methodSet = get_methods(T);
            Iterator<Method> methodIterator = methodSet.iterator();
            while (methodIterator.hasNext()) {
                Method method = methodIterator.next();
                String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                String value = request.getParameter(key);
                Class<?>[] type = method.getParameterTypes();
                Object[] param_value = convert_param_type(type, value);
                method.invoke(obj, param_value);
            }
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * 取全部Set方法
     *
     * @param T
     * @return
     */
    public static Set<Method> get_methods(Class<?> T) {
        Method[] methods = T.getMethods();
        Set<Method> methodSet = new HashSet<Method>();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                methodSet.add(method);
            }
        }
        return methodSet;
    }

    /**
     * 取自定义Set方法
     *
     * @param T
     * @return
     */
    public static Set<Method> get_declared_methods(Class<?> T) {
        Method[] methods = T.getMethods();
        Set<Method> methodSet = new HashSet<Method>();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                methodSet.add(method);
            }
        }
        return methodSet;
    }

    /**
     * 取自定义get方法
     * @param T
     * @return
     */
    public static Set<Method> get_getDeclared_methods(Class<?> T) {
        Method[] methods = T.getDeclaredMethods();
        Set<Method> methodSet = new HashSet<Method>();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
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
                objects[index] = Integer.parseInt(value.toString().trim());
            } else if (c.getName().equals("byte") || c.getName().equals(Byte.class.getName())) {
                objects[index] = Byte.parseByte(value.toString().trim());
            } else if (c.getName().equals(Float.class.getName()) || c.getName().equals("float")) {
                objects[index] = Float.parseFloat(value.toString().trim());
            } else if (c.getName().equals("short") || c.getName().equals(Short.class.getName())) {
                objects[index] = Short.parseShort(value.toString().trim());
            } else if (c.getName().equals("double") || c.getName().equals(Double.class.getName())) {
                objects[index] = Double.parseDouble(value.toString().trim());
            } else if (c.getName().equals(String.class.getName())) {
                objects[index] = value.toString().trim();
            } else if (c.getName().equals(Date.class.getName())) {
                String[] date_format = date_format_string();
                for (String date_format_str : date_format) {
                    DateFormat format1 = new SimpleDateFormat(date_format_str);
                    try {
                        objects[index] = format1.parse(value.toString().trim());
                        break;
                    } catch (Exception ex) {
                        //ex.printStackTrace();
                    }
                }
            } else if (c.getName().equals(BigDecimal.class.getCanonicalName())) {
                objects[index] = new BigDecimal(value.toString().trim());
            }
            else {
//                new Throwable("发现未定义的类型！类型名：" + c.getName()).printStackTrace();
            }
            index++;
        }
        return objects;
    }

    /*
        字符串日期格式集合
     */
    private static String[] date_format_string() {
        String[] date_format = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd"};
        return date_format;
    }

    /**
     * 根据传递的参数修改数据
     * @param o
     * @param parameterMap
     */
    public static void covertObj(Object o, Map<String, String[]> parameterMap) {
        Class<?> clazz = o.getClass();
        Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            String key = entry.getKey().trim();
            String value = entry.getValue()[0].trim();
            try {
                Method method = setMethod(key, clazz);
                if (method!=null) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (method != null) {
                        Object[] param_value = convert_param_type(parameterTypes, value);
                        method.invoke(o, param_value);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据传递的参数修改数据
     * @param o
     * @param parameterMap map参数
     */
    public static void covertObjWithMap(Object o, Map<String, String> parameterMap) {
        Class<?> clazz = o.getClass();
        Iterator<Map.Entry<String, String>> iterator = parameterMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey().trim();
            String value = entry.getValue().trim();
            try {
                Method method = setMethod(key, clazz);
                if (method!=null) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (method != null) {
                        Object[] param_value = convert_param_type(parameterTypes, value);
                        method.invoke(o, param_value);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据传递的参数修改数据
     * @param o
     * @param paramObj model参数
     */
    public static void covertObj(Object o, Object paramObj) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                Field field = fields[i];
                Method getMethod = getMethod(field.getName(), paramObj.getClass());
                if (getMethod != null) {
                    Object value = getMethod.invoke(paramObj);
                    Method setMethod = setMethod(field.getName(), o.getClass());
                    if (setMethod != null) {
                        if (value!=null && !value.toString().equals("")) {
                            setMethod.invoke(o, value);
                        }
                    }
                }
            }catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param obj
     * @param obiExtend
     * @return
     */
    public static Object init(Object obj, Object obiExtend) {
        Class<?> clazz = obj.getClass();
        Set<Method> getMethods = Request2ModelUtils.get_getDeclared_methods(clazz);
        Iterator<Method> ite = getMethods.iterator();
        while (ite.hasNext()) {
            try {
                Method method = ite.next();
                String name = method.getName();//getPosition
                String fileName = name.substring(3,4).toLowerCase()+name.substring(4,name.length());
                Object o = method.invoke(obj);//get
                Method setMethod = Request2ModelUtils.setMethod(fileName, clazz);
                setMethod.invoke(obiExtend, o);//set
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obiExtend;
    }

    public static Method setMethod(String fieldName, Class<?> clazz){
        try {
            Class<?>[] parameterTypes = new Class[1];
            Field field = clazz.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = clazz.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Method getMethod(String fieldName, Class<?> clazz){
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return clazz.getMethod(sb.toString());
        } catch (Exception e) {

        }
        return null;
    }
}
