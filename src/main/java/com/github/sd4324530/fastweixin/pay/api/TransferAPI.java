package com.github.sd4324530.fastweixin.pay.api;

import com.github.sd4324530.fastweixin.pay.entity.request.TransferRequest;
import com.github.sd4324530.fastweixin.pay.entity.response.TransferResponse;

/**
 * Created by cl on 2018/4/27.
 * 付款API
 */
public class TransferAPI extends AbstractPayAPI<TransferRequest, TransferResponse> {

    @Override
    public TransferResponse invoke(TransferRequest request) {
        request.setMch_appid(payConfig.getAppID());
        request.setMchid(payConfig.getMchID());

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers",
                request,
                true,
                TransferResponse.class);
    }

}
