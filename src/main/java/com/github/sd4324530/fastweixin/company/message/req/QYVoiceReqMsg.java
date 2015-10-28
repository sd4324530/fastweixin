package com.github.sd4324530.fastweixin.company.message.req;

/**
 *  微信企业号语音消息事件
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYVoiceReqMsg extends QYBaseReqMsg{
    private String mediaId;
    private String format;

    public QYVoiceReqMsg(String mediaId, String format) {
        this.mediaId = mediaId;
        this.format = format;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "QYVoiceReqMsg [format=" + format + ", mediaId=" + mediaId
                + ", toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", msgId=" + msgId + ", agentId=" + agentId + "]";
    }
}
