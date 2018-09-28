package com.github.sd4324530.fastweixin.api.config;

import com.github.sd4324530.fastweixin.handle.ApiConfigChangeHandle;

/**
 * Created by cl on 2018/4/24.
 * API配置接口，将原ApiConfig配置类抽象成一个接口
 */
public interface ApiConfig {

    String getAppid();

    String getSecret();

    String getAccessToken();

    String getJsApiTicket();

    void addHandle(ApiConfigChangeHandle handle);

    void removeHandle(ApiConfigChangeHandle handle);

    void removeAllHandle();

}
