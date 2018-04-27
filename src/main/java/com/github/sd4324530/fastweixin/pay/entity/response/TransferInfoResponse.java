package com.github.sd4324530.fastweixin.pay.entity.response;

/**
 * Created by cl on 2018/4/27.
 * 查询付款接口响应实体
 */
public class TransferInfoResponse extends BaseResponse {

    /* 商户单号 */
    private String partner_trade_no;
    /* 商户号 */
    private String mch_id;
    /* 付款单号 */
    private String detail_id;
    /* 转账状态 */
    private String status;
    /* 失败原因 */
    private String reason;
    /* 收款用户openid */
    private String openid;
    /* 收款用户姓名 */
    private String transfer_name;
    /* 付款金额 */
    private Integer payment_amount;
    /* 转账时间 */
    private String transfer_time;
    /* 付款描述 */
    private String desc;

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTransfer_name() {
        return transfer_name;
    }

    public void setTransfer_name(String transfer_name) {
        this.transfer_name = transfer_name;
    }

    public Integer getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(Integer payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getTransfer_time() {
        return transfer_time;
    }

    public void setTransfer_time(String transfer_time) {
        this.transfer_time = transfer_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
