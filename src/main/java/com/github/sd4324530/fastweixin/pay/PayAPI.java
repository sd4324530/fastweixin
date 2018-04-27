package com.github.sd4324530.fastweixin.pay;

import com.github.sd4324530.fastweixin.exception.WeixinException;
import com.github.sd4324530.fastweixin.pay.config.PayConfig;
import com.github.sd4324530.fastweixin.pay.entity.request.*;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackInfoResponse;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackResponse;
import com.github.sd4324530.fastweixin.pay.entity.response.TransferInfoResponse;
import com.github.sd4324530.fastweixin.pay.entity.response.TransferResponse;
import com.github.sd4324530.fastweixin.pay.request.PayRequestResult;
import com.github.sd4324530.fastweixin.pay.request.PayRequestService;
import com.github.sd4324530.fastweixin.util.ObjectUtil;
import com.github.sd4324530.fastweixin.util.XmlUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cl on 2018/4/25.
 * 支付API
 */
public class PayAPI {

    protected static final Logger LOG = LoggerFactory.getLogger(PayAPI.class);

    /* 支付API配置 */
    protected PayConfig payConfig;
    /* 支付请求服务 */
    protected PayRequestService payRequestService;

    public PayAPI() {
    }

    public PayAPI(PayConfig payConfig, PayRequestService payRequestService) {
        this.payConfig = payConfig;
        this.payRequestService = payRequestService;
    }

    public void setPayConfig(PayConfig payConfig) {
        this.payConfig = payConfig;
    }

    public void setPayRequestService(PayRequestService payRequestService) {
        this.payRequestService = payRequestService;
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

        return this.doRequest(
                "https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo",
                request,
                true,
                RedPackInfoResponse.class
        );
    }

    /**
     * 请求接口
     *
     * @param url     接口url
     * @param data    接口数据
     * @param useCert 是否使用证书
     * @param clazz   返回数据的Class
     * @param <T>
     * @return
     */
    protected <T> T doRequest(String url, BaseRequest data, boolean useCert, Class<T> clazz) {
        // 生成随机字符串
        data.setNonce_str(PayUtil.generateRandomStr());

        // 生成签名
        Map<String, Object> params = ObjectUtil.object2Map(data);
        params.remove("sign");
        String sign = PayUtil.sign(params, payConfig.getKey());
        params.put("sign", sign);
        Map<String, Object> reqParams = new LinkedHashMap<String, Object>();
        reqParams.put("xml", params);
        String xml = XmlUtil.mapToXml(reqParams);

        LOG.info("[PayAPI]请求接口：{}，发送报文：\n{}", url, xml);
        PayRequestResult result = payRequestService.request(payConfig, url, xml, useCert);
        int statusCode = result.getStatusCode();
        String resp = result.getResult();
        LOG.info("[PayAPI]请求接口：{}，返回状态码：{}，报文：\n{}", url, statusCode, resp);

        if (HttpStatus.SC_OK != statusCode) {
            throw new WeixinException("接口[" + url + "]请求出错，错误码：" + statusCode);
        }

        Map<String, Object> respMap = XmlUtil.xmlToMap(resp);
        respMap = (Map<String, Object>) respMap.get("xml");
        T respObj = ObjectUtil.map2Object(respMap, clazz);

        return respObj;
    }

}
