package com.github.sd4324530.fastweixin.api.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 发送模版消息响应
 */
public class SendTemplateResponse extends BaseResponse {

    /**
     * 消息id
     */
    @JSONField(name = "msgid")
    private String msgid;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
