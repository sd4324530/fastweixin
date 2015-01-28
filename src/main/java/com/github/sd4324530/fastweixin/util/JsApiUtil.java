package com.github.sd4324530.fastweixin.util;

import com.github.sd4324530.fastweixin.message.aes.SHA1;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author daxiaoming
 */
public class JsApiUtil {
    /**
     * 计算 wx.config() 中需要使用的签名。每个页面都需要进行计算
     * @param jsApiTicket
     * @param nonceStr
     * @param timestame
     * @param url
     * @return
     * @throws Exception
     */
    public static String sign(String jsApiTicket, String nonceStr, long timestame, String url) throws Exception {
        Map<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("jsapi_ticket", jsApiTicket);
        paramMap.put("noncestr", nonceStr);
        paramMap.put("timestamp", Long.toString(timestame));
        paramMap.put("url", url);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry: paramMap.entrySet()){
            sb.append("&").append(entry.toString());
        }

        return SHA1.getSHA1HexString(sb.substring(1));
    }

}
