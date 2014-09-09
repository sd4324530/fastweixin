package com.py.fastweixin.message;

import com.py.fastweixin.message.util.MessageBuilder;

public final class TextMsg extends BaseMsg {

	private StringBuilder contentBuilder;

	public String getContent() {
		return contentBuilder.toString();
	}

	public void setContent(String content) {
		contentBuilder = new StringBuilder(content);
	}

	public TextMsg() {
		contentBuilder = new StringBuilder();
	}

	public TextMsg(String content) {
		setContent(content);
	}

	public TextMsg add(String text) {
		contentBuilder.append(text);
		return this;
	}

	public TextMsg addln() {
		return add("\n");
	}

	public TextMsg addln(String text) {
		contentBuilder.append(text);
		return addln();
	}

	public TextMsg addLink(String text, String url) {
		contentBuilder.append("<a href=\"").append(url).append("\">")
				.append(text).append("</a>");
		return this;
	}

	@Override
	public String toXml() {
		MessageBuilder mb = new MessageBuilder(super.toXml());
		mb.addData("Content", contentBuilder.toString().trim());
		mb.addData("MsgType", RespType.TEXT);
		mb.surroundWith("xml");
		return mb.toString();
	}

}
