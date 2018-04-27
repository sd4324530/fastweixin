package com.github.sd4324530.fastweixin.pay.config;

import java.io.InputStream;

/**
 * Created by cl on 2018/4/26.
 * 支付配置
 */
public interface PayConfig {

    /**
     * 获取AppID
     *
     * @return
     */
    String getAppID();

    /**
     * 获取商户ID
     *
     * @return
     */
    String getMchID();

    /**
     * 获取API秘钥
     *
     * @return
     */
    String getKey();

    /**
     * 获取商户证书输入流
     *
     * @return
     */
    InputStream getCertAsStream();

}
