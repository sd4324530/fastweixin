package com.github.sd4324530.fastweixin.pay.entity.response;

import java.io.Serializable;

/**
 * Created by cl on 2018/4/25.
 * 付款接口响应实体
 */
public class TransferResponse extends BaseResponse {

    /* 商户账号appid */
    private String mch_appid;
    /* 商户号 */
    private String mchid;
    /* 设备号 */
    private String device_info;
    /* 随机字符串 */
    private String nonce_str;
    /* 商户订单号 */
    private String partner_trade_no;
    /* 微信订单号 */
    private String payment_no;
    /* 微信支付成功时间 */
    private String payment_time;

    public String getMch_appid() {
        return mch_appid;
    }

    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

}
