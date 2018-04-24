package com.github.sd4324530.fastweixin.api.config;

import java.io.Serializable;

/**
 * Created by cl on 2018/4/24.
 * Token响应结果
 */
public class TokenResponse implements Serializable {

    private String token;

    private Integer expiresIn;

    private String errcode;

    private String errmsg;

    public TokenResponse() {
    }

    public TokenResponse(String token, Integer expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public TokenResponse(String errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

}
