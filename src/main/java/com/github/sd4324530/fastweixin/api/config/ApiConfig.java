package com.github.sd4324530.fastweixin.api.config;

import com.github.sd4324530.fastweixin.api.response.GetJsApiTicketResponse;
import com.github.sd4324530.fastweixin.api.response.GetTokenResponse;
import com.github.sd4324530.fastweixin.exception.WeixinException;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * API配置类，项目中请保证其为单例
 *
 * @author peiyu
 * @since 1.2
 */
public final class ApiConfig implements Serializable {

    private static final Logger        LOG        = LoggerFactory.getLogger(ApiConfig.class);
    public final         AtomicBoolean refreshing = new AtomicBoolean(false);
    private final String  appid;
    private final String  secret;
    private       String  accessToken;
    private       String  jsApiTicket;
    private       boolean enableJsApi;
    private       long    jsTokenStartTime;

    /**
     * 构造方法一，实现同时获取access_token。不启用jsApi
     *
     * @param appid  公众号appid
     * @param secret 公众号secret
     */
    public ApiConfig(String appid, String secret) {
        this(appid, secret, false);
    }

    /**
     * 构造方法二，实现同时获取access_token，启用jsApi
     *
     * @param appid       公众号appid
     * @param secret      公众号secret
     * @param enableJsApi 是否启动js api
     */
    public ApiConfig(String appid, String secret, boolean enableJsApi) {
        this.appid = appid;
        this.secret = secret;
        this.enableJsApi = enableJsApi;
        initToken();
        if (enableJsApi) initJSToken();
    }

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getJsApiTicket() {
        long now = System.currentTimeMillis();
        //官方给出的超时时间是7200秒，这里用7100秒来做，防止出现已经过期的情况
        if (now - this.jsTokenStartTime > 7100000) {
            initJSToken();
        }
        return jsApiTicket;
    }

    public void setJsApiTicket(String jsApiTicket) {
        this.jsApiTicket = jsApiTicket;
    }

    public boolean isEnableJsApi() {
        return enableJsApi;
    }

    public void setEnableJsApi(boolean enableJsApi) {
        this.enableJsApi = enableJsApi;
    }

    /**
     * 初始化微信配置，即第一次获取access_token
     */
    private void initToken() {
        LOG.debug("开始第一次初始化access_token........");
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + this.appid + "&secret=" + this.secret;
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (HttpStatus.SC_OK == resultCode) {
                    GetTokenResponse response = JSONUtil.toBean(resultJson, GetTokenResponse.class);
                    LOG.debug("获取access_token:{}", response.getAccessToken());
                    if(null == response.getAccessToken()) {
                        throw new WeixinException("微信公众号token获取出错，错误信息:" + response.getErrcode() + "," + response.getErrmsg());
                    }
                    ApiConfig.this.accessToken = response.getAccessToken();
                }
            }
        });
    }

    /**
     * 初始化微信JS-SDK，获取JS-SDK token
     */
    private void initJSToken() {
        LOG.debug("初始化 jsapi_ticket........");
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (HttpStatus.SC_OK == resultCode) {
                    GetJsApiTicketResponse response = JSONUtil.toBean(resultJson, GetJsApiTicketResponse.class);
                    LOG.debug("获取jsapi_ticket:{}", response.getTicket());
                    ApiConfig.this.jsApiTicket = response.getTicket();
                    jsTokenStartTime = System.currentTimeMillis();
                }
            }
        });
    }
}
