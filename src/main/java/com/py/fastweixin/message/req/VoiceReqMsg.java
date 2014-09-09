package com.py.fastweixin.message.req;

/**
 * @author peiyu
 */
public final class VoiceReqMsg extends BaseReqMsg {

	private String mediaId;
	private String format;

	private String recognition;

	public String getMediaId() {
		return mediaId;
	}

	public String getFormat() {
		return format;
	}

	public String getRecognition() {
		return recognition;
	}

	public VoiceReqMsg(String mediaId, String format, String recognition) {
		super();
		this.mediaId = mediaId;
		this.format = format;
		this.recognition = recognition;
		setMsgType(ReqType.VOICE);
	}

	@Override
	public String toString() {
		return "VoiceReqMsg [mediaId=" + mediaId + ", format=" + format
				+ ", recognition=" + recognition + ", msgId=" + msgId
				+ ", toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + "]";
	}

}
