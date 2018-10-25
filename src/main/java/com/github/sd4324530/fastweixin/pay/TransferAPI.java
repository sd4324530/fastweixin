package com.github.sd4324530.fastweixin.pay;

import com.github.sd4324530.fastweixin.pay.config.PayConfig;
import com.github.sd4324530.fastweixin.pay.entity.request.TransferInfoRequest;
import com.github.sd4324530.fastweixin.pay.entity.request.TransferRequest;
import com.github.sd4324530.fastweixin.pay.entity.response.TransferInfoResponse;
import com.github.sd4324530.fastweixin.pay.entity.response.TransferResponse;
import com.github.sd4324530.fastweixin.pay.request.PayRequestService;

/**
 * Created by cl on 2018/4/28.
 * 付款API
 */
public class TransferAPI extends BasePayAPI {

    public TransferAPI(PayConfig payConfig, PayRequestService payRequestService) {
        super(payConfig, payRequestService);
    }

    /**
     * 付款
     */
    public TransferResponse transfer(TransferRequest request) {
        request.setMch_appid(payConfig.getAppID());
        request.setMchid(payConfig.getMchID());

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers",
                request,
                true,
                TransferResponse.class);
    }

    /**
     * 查询付款记录
     */
    public TransferInfoResponse getTransferInfo(TransferInfoRequest request) {
        request.setAppid(payConfig.getAppID());
        request.setMch_id(payConfig.getMchID());

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo",
                request,
                true,
                TransferInfoResponse.class
        );
    }

}
