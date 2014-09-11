package com.github.sd4324530.fastweixin.message;

import com.github.sd4324530.fastweixin.message.util.MessageBuilder;

public class MusicMsg extends BaseMsg {

	private String title;
	private String description;
	private String musicUrl;
	private String hqMusicUrl;
	private String thumbMediaId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public MusicMsg(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public MusicMsg(String thumbMediaId, String title, String description,
			String musicUrl, String hqMusicUrl) {
		this.title = title;
		this.description = description;
		this.musicUrl = musicUrl;
		this.hqMusicUrl = hqMusicUrl;
		this.thumbMediaId = thumbMediaId;
	}

	@Override
	public String toXml() {
		MessageBuilder mb = new MessageBuilder(super.toXml());
		mb.addData("MsgType", RespType.MUSIC);
		mb.append("<Music>\n");
		mb.addData("Title", title);
		mb.addData("Description", description);
		mb.addData("MusicUrl", musicUrl);
		mb.addData("HQMusicUrl", hqMusicUrl);
		mb.addData("ThumbMediaId", thumbMediaId);
		mb.append("</Music>\n");
		mb.surroundWith("xml");
		return mb.toString();
	}

}
