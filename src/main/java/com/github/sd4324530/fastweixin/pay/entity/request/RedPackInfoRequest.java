package com.github.sd4324530.fastweixin.pay.entity.request;

/**
 * Created by cl on 2018/4/27.
 * 查询红包记录接口请求实体
 */
public class RedPackInfoRequest extends BaseRequest {

    /* 商户订单号 */
    private String mch_billno;
    /* 商户号 */
    private String mch_id;
    /* 公众号Appid */
    private String appid;
    /* 订单类型 */
    private String bill_type;

    public String getMch_billno() {
        return mch_billno;
    }

    public void setMch_billno(String mch_billno) {
        this.mch_billno = mch_billno;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

}
