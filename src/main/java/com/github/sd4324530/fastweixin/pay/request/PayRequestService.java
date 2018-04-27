package com.github.sd4324530.fastweixin.pay.request;

import com.github.sd4324530.fastweixin.pay.config.PayConfig;

/**
 * Created by cl on 2018/4/26.
 * 支付请求服务
 */
public interface PayRequestService {

    /**
     * 请求
     *
     * @param config  支付API配置
     * @param url     请求url
     * @param reqBody 请求body报文
     * @param useCert 是否使用证书
     * @return
     */
    PayRequestResult request(PayConfig config, String url, String reqBody, boolean useCert);

    /**
     * 使用证书的请求（可重试的，双向认证的请求）
     *
     * @param config  支付API配置
     * @param url     请求url
     * @param reqBody 请求body报文
     * @return
     */
    PayRequestResult requestWithCert(PayConfig config, String url, String reqBody);

    /**
     * 不使用证书的请求（可重试的，非双向认证的请求）
     *
     * @param config  支付API配置
     * @param url     请求url
     * @param reqBody 请求body报文
     * @return
     */
    PayRequestResult requestWithoutCert(PayConfig config, String url, String reqBody);

}
