package com.github.sd4324530.fastweixin.company.api.config;

import com.github.sd4324530.fastweixin.api.config.DefaultApiConfig;
import com.github.sd4324530.fastweixin.api.config.TokenService;

/**
 * Created by cl on 2018/4/24.
 * 企业API配置默认实现
 */
public class DefaultQYAPIConfig extends DefaultApiConfig implements QYAPIConfig {

    /* 企业号appid */
    protected final String corpid;
    /* 企业号secret */
    protected final String corpsecret;

    public DefaultQYAPIConfig(String corpid, String corpsecret) {
        this(corpid, corpsecret, false);
    }

    public DefaultQYAPIConfig(String corpid, String corpsecret, boolean enableJsApi) {
        this(corpid, corpsecret, enableJsApi, null);
    }

    public DefaultQYAPIConfig(String corpid, String corpsecret, boolean enableJsApi, TokenService tokenService) {
        super(corpid, corpsecret, enableJsApi, tokenService);
        this.corpid = corpid;
        this.corpsecret = corpsecret;
    }

    @Override
    protected TokenService createTokenService() {
        return new DefaultQYTokenService();
    }

    @Override
    public String getCorpid() {
        return corpid;
    }

    @Override
    public String getCorpsecret() {
        return corpsecret;
    }

}
