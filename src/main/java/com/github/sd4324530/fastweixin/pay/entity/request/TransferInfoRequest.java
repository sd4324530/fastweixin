package com.github.sd4324530.fastweixin.pay.entity.request;

/**
 * Created by cl on 2018/4/27.
 * 查询付款接口请求实体
 */
public class TransferInfoRequest extends BaseRequest {

    /* 商户号Appid */
    private String appid;
    /* 商户号 */
    private String mch_id;
    /* 商户订单号 */
    private String partner_trade_no;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

}
