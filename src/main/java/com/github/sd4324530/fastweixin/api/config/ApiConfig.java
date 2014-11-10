package com.github.sd4324530.fastweixin.api.config;

import com.github.sd4324530.fastweixin.api.response.GetTokenResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;

/**
 * API配置类，项目中请保证其为单例
 * @author peiyu
 * @since 1.2
 */
public final class ApiConfig {

    private final String appid;

    private final String secret;

    private String access_token;

    public volatile boolean refreshing = false;

    /**
     * 唯一构造方法，实现同时获取access_token
     * @param appid 公众号appid
     * @param secret 公众号secret
     */
    public ApiConfig(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
        init();
    }

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * 初始化微信配置，即第一次获取access_token
     */
    public void init() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + this.appid + "&secret=" + this.secret;
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (200 == resultCode) {
                    GetTokenResponse response = JSONUtil.toBean(resultJson, GetTokenResponse.class);
                    ApiConfig.this.access_token = response.getAccess_token();
                }
            }
        });
    }
}
