package com.github.sd4324530.fastweixin.pay.api;

import com.github.sd4324530.fastweixin.pay.entity.request.GroupRedPackRequest;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackResponse;

/**
 * Created by cl on 2018/4/27.
 * 发裂变红包API
 */
public class GroupRedPackAPI extends AbstractPayAPI<GroupRedPackRequest, RedPackResponse> {

    @Override
    public RedPackResponse invoke(GroupRedPackRequest request) {
        request.setMch_id(payConfig.getMchID());
        request.setWxappid(payConfig.getAppID());

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack",
                request,
                true,
                RedPackResponse.class
        );
    }

}
