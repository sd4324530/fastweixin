package com.github.sd4324530.fastweixin.api.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author peiyu
 */
public class GetTokenResponse extends BaseResponse {

    @JSONField(name = "access_token")
    private String  accessToken;
    @JSONField(name = "expires_in")
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
