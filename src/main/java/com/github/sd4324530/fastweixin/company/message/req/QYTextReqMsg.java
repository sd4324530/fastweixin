package com.github.sd4324530.fastweixin.company.message.req;

/**
 *  微信企业号文本消息事件
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYTextReqMsg extends QYBaseReqMsg {

    private String content;

    public QYTextReqMsg(String content) {
        super();
        this.content = content;
        setMsgType(QYReqType.TEXT);
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "QYTextReqMsg [content=" + content
                + ", toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", msgId=" + msgId + ", agentId=" + agentId + "]";
    }
}
