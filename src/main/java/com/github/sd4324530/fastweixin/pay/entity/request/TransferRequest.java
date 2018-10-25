package com.github.sd4324530.fastweixin.pay.entity.request;

import java.io.Serializable;

/**
 * Created by cl on 2018/4/25.
 * 付款接口请求实体
 */
public class TransferRequest extends BaseRequest {

    /* 商户账号appid */
    private String mch_appid;
    /* 商户号 */
    private String mchid;
    /* 设备号 */
    private String device_info;
    /* 商户订单号 */
    private String partner_trade_no;
    /* 用户openid */
    private String openid;
    /* 校验用户姓名选项 */
    private String check_name;
    /* 收款用户姓名 */
    private String re_user_name;
    /* 金额 */
    private Integer amount;
    /* 企业付款描述信息 */
    private String desc;
    /* Ip地址 */
    private String spbill_create_ip;

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

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheck_name() {
        return check_name;
    }

    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getRe_user_name() {
        return re_user_name;
    }

    public void setRe_user_name(String re_user_name) {
        this.re_user_name = re_user_name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

}
