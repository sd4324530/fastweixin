package com.github.sd4324530.fastweixin.pay.request;

/**
 * Created by cl on 2018/4/27.
 * 支付请求结果
 */
public class PayRequestResult {

    /* HTTP请求状态码 */
    private int statusCode;

    /* 请求返回结果 */
    private String result;

    public PayRequestResult() {
    }

    public PayRequestResult(int statusCode, String result) {
        this.statusCode = statusCode;
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
