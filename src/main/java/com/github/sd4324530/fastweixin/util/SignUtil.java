package com.github.sd4324530.fastweixin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.github.sd4324530.fastweixin.util.StrUtil.isHasBlank;

/**
 * 绑定服务器工具类
 *
 * @author peiyu
 */
public final class SignUtil {

    /**
     * 此类不需要实例化
     */
    private SignUtil() {
    }

    private static final char[] digit = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 认证微信，可以参见微信开发者文档
     *
     * @param token     我们自己设定的token值
     * @param signature 微信传来的变量
     * @param timestamp 微信传来的变量
     * @param nonce     微信传来的变量
     * @return 是否合法
     */
    public static boolean checkSignature(String token, String signature,
                                         String timestamp, String nonce) {
        if (isHasBlank(token, signature, timestamp, nonce)) {
            return false;
        }
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tmpStr != null && tmpStr.equalsIgnoreCase(signature);
    }

    /**
     * 将byte数组变为16进制对应的字符串
     *
     * @param byteArray byte数组
     * @return 转换结果
     */
    private static String byteToStr(byte[] byteArray) {
        int len = byteArray.length;
        StringBuilder strDigest = new StringBuilder(len * 2);
        for (int i = 0; i < len; i++) {
            strDigest.append(byteToHexStr(byteArray[i]));
        }
        return strDigest.toString();
    }

    private static String byteToHexStr(byte mByte) {
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];
        return new String(tempArr);
    }
}
