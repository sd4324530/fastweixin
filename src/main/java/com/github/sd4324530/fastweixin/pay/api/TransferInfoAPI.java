package com.github.sd4324530.fastweixin.pay.api;

import com.github.sd4324530.fastweixin.pay.entity.request.TransferInfoRequest;
import com.github.sd4324530.fastweixin.pay.entity.response.TransferInfoResponse;

/**
 * Created by cl on 2018/4/27.
 * 查询付款记录API
 */
public class TransferInfoAPI extends AbstractPayAPI<TransferInfoRequest, TransferInfoResponse> {

    @Override
    public TransferInfoResponse invoke(TransferInfoRequest request) {
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
