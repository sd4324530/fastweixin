package com.github.sd4324530.fastweixin.pay;

import com.github.sd4324530.fastweixin.common.api.API;
import com.github.sd4324530.fastweixin.pay.config.PayConfig;
import com.github.sd4324530.fastweixin.pay.request.PayRequestService;

/**
 * Created by cl on 2018/4/27.
 * 支付API
 */
public interface PayAPI<S, T> extends API<S, T> {

    /**
     * API初始化
     *
     * @param payConfig         支付API配置
     * @param payRequestService 支付请求服务
     */
    void init(PayConfig payConfig, PayRequestService payRequestService);

}
