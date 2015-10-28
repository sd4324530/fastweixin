package com.github.sd4324530.fastweixin.company.message.resp;

import com.github.sd4324530.fastweixin.message.util.MessageBuilder;

import java.io.Serializable;

/**
 *  微信企业号被动响应消息基类
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYBaseRespMsg implements Serializable {

    private String toUserName;
    private String fromUserName;
    private int createTime;
    private String msgType;

    public QYBaseRespMsg() {
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String toXml(){
        // 159 = 106 + 28(ToUserName) + 15(FromUserName) + 10(CreateTime)
        MessageBuilder builder = new MessageBuilder(159);
        builder.addData("ToUserName", getToUserName());
        builder.addData("FromUserName", getFromUserName());
        builder.addData("CreateTime", String.valueOf(System.currentTimeMillis()/1000));
        return builder.toString();
    }

    @Override
    public String toString() {
        return toXml();
    }
}
