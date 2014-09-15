package com.github.sd4324530.fastweixin.util;

/**
 * 对象常用方法工具类
 * @author peiyu
 */
public final class BeanUtil {

    public static boolean isNull(Object object) {
        return null == object;
    }

    public static boolean nonNull(Object object) {
        return null != object;
    }
}
