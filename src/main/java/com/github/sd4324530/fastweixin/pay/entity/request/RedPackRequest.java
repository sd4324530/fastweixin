package com.github.sd4324530.fastweixin.pay.entity.request;

/**
 * Created by cl on 2018/4/27.
 * 普通红包接口请求实体
 */
public class RedPackRequest extends BaseRedPackRequest {

    /* Ip地址 */
    private String client_ip;

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

}
