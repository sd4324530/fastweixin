package com.github.sd4324530.fastweixin.pay;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by cl on 2018/4/26.
 * 支付工具类
 */
public class PayUtil {

    private PayUtil() {
    }

    /**
     * 支付签名
     *
     * @param params 签名参数集合
     * @param key    安全密钥
     * @return
     */
    public static String sign(Map<String, Object> params, String key) {
        return sign(params, key, PayConstants.SignType.MD5);
    }

    /**
     * 支付签名
     *
     * @param params   签名参数集合
     * @param key      安全密钥
     * @param signType 签名方式
     * @return
     */
    public static String sign(Map<String, Object> params, String key, PayConstants.SignType signType) {
        Set<String> keySet = params.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);

        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            Object val = params.get(key);
            if (null != val && val.toString().trim().length() > 0) {
                sb.append(k).append("=").append(val.toString().trim());
            }
        }
        sb.append("&key=").append(key);

        if (signType == PayConstants.SignType.MD5) {
            return toMD5(sb.toString());
        } else if (signType == PayConstants.SignType.HMACSHA256) {
            return toHMACSHA256(sb.toString(), key);
        } else {
            throw new RuntimeException(String.format("Invalid sign_type: %s", signType));
        }
    }

    /**
     * 生成MD5签名
     *
     * @param str
     * @return
     */
    public static String toMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 生成HMACSHA256签名
     *
     * @param str
     * @param key
     * @return
     */
    public static String toHMACSHA256(String str, String key) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            mac.init(secretKey);
            byte[] bytes = mac.doFinal(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 生成随机字符串，32位
     *
     * @return
     */
    public static String generateRandomStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

}
