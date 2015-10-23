package com.github.sd4324530.fastweixin.company.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;

/**
 * Response -- 从Oauth中获取的用户信息
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 * @author Nottyjay
 * @version 1.0.beta
 * @since 1.3.6
 * ====================================================================
 */
public class GetOauthUserInfoResponse extends BaseResponse{

    @JSONField(name = "UserId")
    private String userid;
    @JSONField(name = "OpenId")
    private String openid;
    @JSONField(name = "DeviceId")
    private String deviceid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}
