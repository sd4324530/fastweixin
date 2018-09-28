package com.github.sd4324530.fastweixin.api.config;

/**
 * Created by cl on 2018/4/24.
 * 微信Token服务，用于获取access_token和js-sdk token
 */
public interface TokenService {

    void getAccessToken(ApiConfig apiConfig, TokenResponseCallback callback);

    void getJsApiTicket(ApiConfig apiConfig, TokenResponseCallback callback);

}
