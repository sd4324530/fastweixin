package com.github.sd4324530.fastweixin.company.message.req;

/**
 *  微信企业号图片消息事件
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYImageReqMsg extends QYBaseReqMsg {

    private String picUrl;
    private String mediaId;

    public QYImageReqMsg(String picUrl, String mediaId) {
        super();
        this.picUrl = picUrl;
        this.mediaId = mediaId;
        setMsgType(QYReqType.IMAGE);
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    @Override
    public String toString() {
        return "QYImageReqMsg [picUrl=" + picUrl + ", mediaId=" + mediaId
                + ", toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", msgId=" + msgId + ", agentId=" + agentId + "]";
    }
}
