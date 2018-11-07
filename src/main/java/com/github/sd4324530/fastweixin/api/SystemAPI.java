package com.github.sd4324530.fastweixin.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.sd4324530.fastweixin.api.config.ApiConfigInterface;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.util.CollectionUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统API
 *
 * @author peiyu
 */
public class SystemAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(SystemAPI.class);

    public SystemAPI(ApiConfigInterface config) {
        super(config);
    }

    /**
     * 获取微信服务器IP地址列表
     *
     * @return IP地址列表
     */
    public List<String> getCallbackIP() {
        LOG.debug("获取微信服务器IP地址......");
        List<String> result = null;
        String url = BASE_API_URL + "cgi-bin/getcallbackip?access_token=#";
        BaseResponse r = executeGet(url);
        if (isSuccess(r.getErrcode())) {
            JSONArray array = JSON.parseObject(r.getErrmsg()).getJSONArray("ip_list");
            result = CollectionUtil.newArrayList(array.size());
            for (Object obj : array) result.add(obj.toString());
        }
        return result;
    }

    /**
     * 将一条长链接转成短链接
     *
     * @param longUrl 长链接
     * @return 对应的短链接
     */
    public String getShortUrl(String longUrl) {
        String result = "";
        LOG.debug("获取短URL.......");
        if (checkUrl(longUrl)) {
            String url = BASE_API_URL + "cgi-bin/shorturl?access_token=#";
            Map<String, String> params = new HashMap<String, String>();
            params.put("action", "long2short");
            params.put("long_url", longUrl);
            BaseResponse r = executePost(url, JSONUtil.toJson(params));
            if (isSuccess(r.getErrcode())) {
                result = JSONUtil.toMap(r.getErrmsg()).get("short_url").toString();
            }
        }
        return result;
    }

    /**
     * 检查URL是否支持
     *
     * @param url 需要检查的URL
     * @return 是否支持
     */
    private boolean checkUrl(String url) {
        return StrUtil.isNotBlank(url) && (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("weixin://wxpay"));
    }
}
