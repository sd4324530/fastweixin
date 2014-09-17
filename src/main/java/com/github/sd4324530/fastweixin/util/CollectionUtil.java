package com.github.sd4324530.fastweixin.util;

import java.util.Collection;

/**
 * 集合常用方法工具类
 *
 * @author peiyu
 */
public final class CollectionUtil {

    /**
     * 此类不需要实例化
     */
    private CollectionUtil() {
    }

    /**
     * 判断一个集合是否为空
     * null或者空集合都会返回true
     *
     * @param collection 需要判断的集合
     * @return 是否有值，null或者空集合都是返回true
     */
    public static boolean isEmpty(Collection<? extends Object> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 判断一个集合是否不为空
     *
     * @param collection 需要判断的集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Collection<? extends Object> collection) {
        return null != collection && !collection.isEmpty();
    }
}
