package com.github.sd4324530.fastweixin.message;

import com.github.sd4324530.fastweixin.message.util.MessageBuilder;

/**
 * @author peiyu
 */
public class VoiceMsg extends BaseMsg {

	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public VoiceMsg(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public String toXml() {
		MessageBuilder mb = new MessageBuilder(super.toXml());
		mb.addData("MsgType", RespType.VOICE);
		mb.append("<Voice>\n");
		mb.addData("MediaId", mediaId);
		mb.append("</Voice>\n");
		mb.surroundWith("xml");
		return mb.toString();
	}

}
