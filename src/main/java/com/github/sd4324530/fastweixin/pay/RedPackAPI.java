package com.github.sd4324530.fastweixin.pay;

import com.github.sd4324530.fastweixin.pay.config.PayConfig;
import com.github.sd4324530.fastweixin.pay.entity.request.GroupRedPackRequest;
import com.github.sd4324530.fastweixin.pay.entity.request.RedPackInfoRequest;
import com.github.sd4324530.fastweixin.pay.entity.request.RedPackRequest;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackInfoResponse;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackResponse;
import com.github.sd4324530.fastweixin.pay.request.PayRequestService;

/**
 * Created by cl on 2018/4/28.
 * 红包API
 */
public class RedPackAPI extends BasePayAPI {

    public RedPackAPI(PayConfig payConfig, PayRequestService payRequestService) {
        super(payConfig, payRequestService);
    }

    /**
     * 发普通红包
     */
    public RedPackResponse sendRedPack(RedPackRequest request) {
        request.setMch_id(payConfig.getMchID());
        request.setWxappid(payConfig.getAppID());

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack",
                request,
                true,
                RedPackResponse.class
        );
    }

    /**
     * 发裂变红包
     */
    public RedPackResponse sendGroupRedPack(GroupRedPackRequest request) {
        request.setMch_id(payConfig.getMchID());
        request.setWxappid(payConfig.getAppID());

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack",
                request,
                true,
                RedPackResponse.class
        );
    }

    /**
     * 查询红包记录
     */
    public RedPackInfoResponse getRedPackInfo(RedPackInfoRequest request) {
        request.setMch_id(payConfig.getMchID());
        request.setAppid(payConfig.getAppID());
        request.setBill_type("MCHT");

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo",
                request,
                true,
                RedPackInfoResponse.class
        );
    }

}
