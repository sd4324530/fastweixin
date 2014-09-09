package com.py.fastweixin.message.req;

public final class VideoReqMsg extends BaseReqMsg {

	private String mediaId;
	private String thumbMediaId;

	public String getMediaId() {
		return mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public VideoReqMsg(String mediaId, String thumbMediaId) {
		super();
		this.mediaId = mediaId;
		this.thumbMediaId = thumbMediaId;
		setMsgType(ReqType.VIDEO);
	}

	@Override
	public String toString() {
		return "VideoReqMsg [mediaId=" + mediaId + ", thumbMediaId="
				+ thumbMediaId + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", msgId=" + msgId
				+ "]";
	}

}
