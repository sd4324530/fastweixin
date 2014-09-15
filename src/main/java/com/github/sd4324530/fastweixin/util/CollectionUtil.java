package com.github.sd4324530.fastweixin.util;

import java.util.Collection;

/**
 * 集合常用方法工具类
 * @author peiyu
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<? extends Object> collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<? extends Object> collection) {
        return null != collection && !collection.isEmpty();
    }
}
