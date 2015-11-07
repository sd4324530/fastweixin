package com.github.sd4324530.fastweixin.company.message.req;
/**
 *  微信企业号事件消息基类
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYBaseEvent extends QYBaseReq{

    String event;

    public QYBaseEvent() {
        setMsgType(QYReqType.EVENT);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
