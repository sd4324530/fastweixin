package com.github.sd4324530.fastweixin.pay.api;

import com.github.sd4324530.fastweixin.pay.entity.request.RedPackRequest;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackResponse;

/**
 * Created by cl on 2018/4/27.
 * 发普通红包API
 */
public class RedPackAPI extends AbstractPayAPI<RedPackRequest, RedPackResponse> {

    @Override
    public RedPackResponse invoke(RedPackRequest request) {
        request.setMch_id(payConfig.getMchID());
        request.setWxappid(payConfig.getAppID());

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack",
                request,
                true,
                RedPackResponse.class
        );
    }

}
