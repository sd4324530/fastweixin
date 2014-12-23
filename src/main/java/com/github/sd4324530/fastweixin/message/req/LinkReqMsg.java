package com.github.sd4324530.fastweixin.message.req;

public final class LinkReqMsg extends BaseReqMsg {

    private String title;// ��Ϣ����
    private String description;// ��Ϣ����
    private String url;// ��Ϣ����

    public LinkReqMsg(String title, String description, String url) {
        super();
        this.title = title;
        this.description = description;
        this.url = url;
        setMsgType(ReqType.EVENT);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "LinkReqMsg [title=" + title + ", description=" + description
                + ", url=" + url + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + ", msgId=" + msgId
                + "]";
    }

}
