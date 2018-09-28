package com.github.sd4324530.fastweixin.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by cl on 2018/4/25.
 */
public class ObjectUtil {

    private final static Log logger = LogFactory.getLog(ObjectUtil.class);

    /* 忽略的类型：不需要转换成Map的属性 */
    private final static Class<?>[] IGNORE_TYPES = new Class<?>[]{String.class, Date.class, Timestamp.class, BigDecimal.class};

    /* 优化性能，将类的属性全部缓存起来 */
    private static Map<Class<?>, List<Field>> fieldsMap = new HashMap<Class<?>, List<Field>>();

    private ObjectUtil() {
    }

    /**
     * 获取对象的属性Field列表
     */
    public static List<Field> getFields(Object obj) {
        Class<?> clazz = obj.getClass();
        return getFields(clazz);
    }

    /**
     * 获取类的属性Field列表
     */
    public static List<Field> getFields(Class<?> clazz) {
        List<Field> list = fieldsMap.get(clazz);
        if (null == list) {
            synchronized (fieldsMap) {
                list = fieldsMap.get(clazz);
                if (null == list) {
                    list = new ArrayList<Field>();
                    while (null != clazz) {
                        for (Field f : clazz.getDeclaredFields()) {
                            if ((!Modifier.isStatic(f.getModifiers())) && (!Modifier.isFinal(f.getModifiers()))) {
                                list.add(f);
                            }
                        }
                        clazz = clazz.getSuperclass();
                    }
                    fieldsMap.put(clazz, list);
                }
            }
        }

        return list;
    }

    /**
     * 获取对象的属性
     */
    public static Object getProperty(Object obj, String propertyName) {
        Class<?> clazz = obj.getClass();
        String methodName = "get" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
        try {
            Method method = clazz.getMethod(methodName);
            return method.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 判断是否为包装类型
     */
    public static boolean isWrapClass(Class<?> type) {
        try {
            return ((Class<?>) type.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否为基本类型
     */
    public static boolean isPrimitive(Class<?> type) {
        try {
            return type.isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Map转对象，只支持简单的Java Bean
     */
    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();

            for (Field field : getFields(clazz)) {
                String name = field.getName();
                Class<?> type = field.getType();
                Object value = map.get(name);

                if (null != value) {
                    if (!type.isAssignableFrom(value.getClass()) &&
                            isBasicType(type) && TypeConvertUtil.isSupported(type)) {
                        value = TypeConvertUtil.convert(value.toString(), type);
                    }
                    field.setAccessible(true);
                    field.set(target, value);
                }
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }

        return target;
    }

    /**
     * 对象转Map
     */
    public static Map<String, Object> object2Map(Object obj) {
        return object2Map(obj, null);
    }

    /**
     * 对象转Map
     */
    public static Map<String, Object> object2Map(Object obj, Object2MapHandler handler) {
        return (Map<String, Object>) convertToMap(obj, handler);
    }

    /**
     * 对象转Map
     */
    private static Object convertToMap(Object obj, Object2MapHandler handler) {
        if (null == obj) {
            return null;
        } else if (obj instanceof Collection || obj.getClass().isArray()) {
            List<Object> targetList = new ArrayList<Object>();

            Collection<?> list = (Collection<?>) obj;
            for (Object o : list) {
                Object target = convertToMap(o, handler);
                targetList.add(target);
            }

            return targetList;
        } else if (obj instanceof Map) {
            Map<Object, Object> targetMap = new LinkedHashMap<Object, Object>();

            Map<?, ?> map = (Map<?, ?>) obj;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                Object o = convertToMap(value, handler);
                targetMap.put(key, o);
            }

            return targetMap;
        } else if (!isBasicType(obj.getClass())) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();

            for (Field field : getFields(obj.getClass())) {
                try {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    value = convertToMap(value, handler);
                    map.put(name, value);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }

            return map;
        } else {
            if (null != handler) {
                obj = handler.handle(obj);
            }
            return obj;
        }
    }

    /**
     * 是否为基本的数据类型，例如：基本类型及其包装类型和String、Date等
     */
    private static boolean isBasicType(Class<?> type) {
        // 基本类型和包装类型
        if (isPrimitive(type) || isWrapClass(type)) {
            return true;
        }

        // 常用的数据类型（String、Date、BigDecimal等）
        for (Class<?> clazz : IGNORE_TYPES) {
            if (clazz.isAssignableFrom(type)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 对象转Map过程中的属性处理器
     */
    public static interface Object2MapHandler {

        Object handle(Object value);

    }

    /**
     * 拷贝属性
     */
    public static void copyProperties(Object src, Object target) {
        List<Field> fields = ObjectUtil.getFields(src);
        Class<?> srcClazz = src.getClass();
        Class<?> targetClazz = target.getClass();

        for (Field f : fields) {
            String fieldName = f.getName();
            try {
                f.setAccessible(true);
                Object val = f.get(src);

                if (null != val) {
                    String methodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                    Method method = targetClazz.getMethod(methodName, f.getType());
                    method.invoke(target, val);
                }
            } catch (NoSuchMethodException e) {
                logger.warn("[" + srcClazz.getName() + "]属性" + fieldName + "不存在或对应的set方法不存在");
            } catch (Exception e) {
                logger.error("[" + srcClazz.getName() + "]属性" + fieldName + "拷贝失败" + e.getLocalizedMessage(), e);
            }
        }
    }

    /**
     * 根据类名创建该类的实例
     */
    public static Object createClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

}
