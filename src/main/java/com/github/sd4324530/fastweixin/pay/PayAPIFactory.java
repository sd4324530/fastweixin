package com.github.sd4324530.fastweixin.pay;


import com.github.sd4324530.fastweixin.common.api.APIFactory;
import com.github.sd4324530.fastweixin.pay.config.PayConfig;
import com.github.sd4324530.fastweixin.pay.request.PayRequestService;

/**
 * Created by cl on 2018/4/27.
 * 支付API工厂
 */
public class PayAPIFactory extends APIFactory<PayAPI> {

    /* 支付API配置 */
    protected PayConfig payConfig;
    /* 支付请求服务 */
    protected PayRequestService payRequestService;

    public PayAPIFactory(PayConfig payConfig, PayRequestService payRequestService) {
        this.payConfig = payConfig;
        this.payRequestService = payRequestService;
    }

    @Override
    public void registerAPI(String name, PayAPI api) {
        api.init(payConfig, payRequestService);
        super.registerAPI(name, api);
    }

}
