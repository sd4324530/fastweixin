package com.github.sd4324530.fastweixin.pay.api;

import com.github.sd4324530.fastweixin.pay.entity.request.RedPackInfoRequest;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackInfoResponse;

/**
 * Created by cl on 2018/4/27.
 * 查询红包记录API
 */
public class RedPackInfoAPI extends AbstractPayAPI<RedPackInfoRequest, RedPackInfoResponse> {

    @Override
    public RedPackInfoResponse invoke(RedPackInfoRequest request) {
        request.setMch_id(payConfig.getMchID());
        request.setAppid(payConfig.getAppID());

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo",
                request,
                true,
                RedPackInfoResponse.class
        );
    }

}
