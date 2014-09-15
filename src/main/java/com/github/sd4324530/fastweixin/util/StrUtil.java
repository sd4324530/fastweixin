package com.github.sd4324530.fastweixin.util;

/**
 * 字符串常用方法工具类
 * @author peiyu
 */
public class StrUtil {

    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

    public static boolean isNotBlank(String str) {
        return null != str && !"".equals(str.trim());
    }
}