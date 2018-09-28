package com.github.sd4324530.fastweixin.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cl on 2018/4/27.
 * 类型转换工具类，支持将字符串转换为指定类型
 */
public class TypeConvertUtil {

    private static Map<Class<?>, TypeConverter> converterMap = new HashMap<Class<?>, TypeConverter>();

    static {
        // short/Short转换器
        TypeConverter shortConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return Short.valueOf(src);
            }
        };
        registerConverter(short.class, shortConverter);
        registerConverter(Short.class, shortConverter);

        // int/Integer转换器
        TypeConverter intConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return Integer.valueOf(src);
            }
        };
        registerConverter(int.class, intConverter);
        registerConverter(Integer.class, intConverter);

        // long/Long转换器
        TypeConverter longConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return Long.valueOf(src);
            }
        };
        registerConverter(long.class, longConverter);
        registerConverter(Long.class, longConverter);

        // float/Float转换器
        TypeConverter floatConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return Float.valueOf(src);
            }
        };
        registerConverter(float.class, floatConverter);
        registerConverter(Float.class, floatConverter);

        // double/Double转换器
        TypeConverter doubleConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return Double.valueOf(src);
            }
        };
        registerConverter(double.class, doubleConverter);
        registerConverter(Double.class, doubleConverter);

        // byte/Byte转换器
        TypeConverter byteConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return Byte.valueOf(src);
            }
        };
        registerConverter(byte.class, byteConverter);
        registerConverter(Byte.class, byteConverter);

        // char/Character转换器
        TypeConverter charConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return Character.valueOf(src.length() > 0 ? src.toCharArray()[0] : '0');
            }
        };
        registerConverter(char.class, charConverter);
        registerConverter(Character.class, charConverter);

        // boolean/Boolean转换器
        TypeConverter boolConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return Boolean.valueOf(src);
            }
        };
        registerConverter(boolean.class, boolConverter);
        registerConverter(Boolean.class, boolConverter);

        // Date/Timestamp转换器，目前只支持long型的时间戳格式
        TypeConverter dateConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                long longValue = Long.valueOf(src);
                return new Timestamp(longValue);
            }
        };
        registerConverter(Date.class, dateConverter);
        registerConverter(Timestamp.class, dateConverter);

        // BigDecimal转换器
        TypeConverter decimalConverter = new TypeConverter() {
            @Override
            public Object convert(String src) {
                return new BigDecimal(src);
            }
        };
        registerConverter(BigDecimal.class, decimalConverter);
    }

    private TypeConvertUtil() {
    }

    /**
     * 注册转换器
     *
     * @param clazz
     * @param typeConverter
     */
    public static void registerConverter(Class<?> clazz, TypeConverter typeConverter) {
        converterMap.put(clazz, typeConverter);
    }

    /**
     * 将字符串转换为对应类型
     *
     * @param src
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convert(String src, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) src;
        }
        TypeConverter typeConverter = converterMap.get(clazz);
        return (T) typeConverter.convert(src);
    }

    /**
     * 判断是否支持该类型的转换
     *
     * @param clazz
     * @return
     */
    public static boolean isSupported(Class<?> clazz) {
        TypeConverter typeConverter = converterMap.get(clazz);
        return null != typeConverter;
    }

    /**
     * 类型转换器，将String转换成目标类型
     */
    public static interface TypeConverter {
        Object convert(String src);
    }

}
