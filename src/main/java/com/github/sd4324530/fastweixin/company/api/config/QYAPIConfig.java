package com.github.sd4324530.fastweixin.company.api.config;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;

/**
 * Created by cl on 2018/4/24.
 * 企业API配置接口，将原QYAPIConfig配置类抽象成一个接口
 */
public interface QYAPIConfig extends ApiConfig {

    String getCorpid();

    String getCorpsecret();

}
