package com.github.sd4324530.fastweixin.pay.entity.request;

/**
 * Created by cl on 2018/4/27.
 * 裂变红包接口请求实体
 */
public class GroupRedPackRequest extends BaseRedPackRequest {

    /* 红包金额设置方式 */
    private String amt_type;

    public String getAmt_type() {
        return amt_type;
    }

    public void setAmt_type(String amt_type) {
        this.amt_type = amt_type;
    }

}
