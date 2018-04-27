package com.github.sd4324530.fastweixin.pay.entity.response;

import java.util.List;
import java.util.Map;

/**
 * Created by cl on 2018/4/27.
 * 查询红包记录接口响应实体
 */
public class RedPackInfoResponse extends BaseResponse {

    /* 签名 */
    private String sign;
    /* 商户号 */
    private String mch_id;
    /* 红包单号 */
    private String detail_id;
    /* 红包状态 */
    private String status;
    /* 发放类型 */
    private String send_type;
    /* 红包类型 */
    private String hb_type;
    /* 红包个数 */
    private Integer total_num;
    /* 红包金额 */
    private Integer total_amount;
    /* 失败原因 */
    private String reason;
    /* 红包发送时间 */
    private String send_time;
    /* 红包退款时间 */
    private String refund_time;
    /* 红包退款金额 */
    private Integer refund_amount;
    /* 红包祝福语 */
    private String wishing;
    /* 备注 */
    private String remark;
    /* 活动名称 */
    private String act_name;
    /* 裂变红包领取列表 */
    private Map<String, Object> hblist;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getHb_type() {
        return hb_type;
    }

    public void setHb_type(String hb_type) {
        this.hb_type = hb_type;
    }

    public Integer getTotal_num() {
        return total_num;
    }

    public void setTotal_num(Integer total_num) {
        this.total_num = total_num;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(String refund_time) {
        this.refund_time = refund_time;
    }

    public Integer getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(Integer refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public Map<String, Object> getHblist() {
        return hblist;
    }

    public void setHblist(Map<String, Object> hblist) {
        this.hblist = hblist;
    }

}
