package com.github.sd4324530.fastweixin.message;

import com.github.sd4324530.fastweixin.message.util.MessageBuilder;

public final class TextMsg extends BaseMsg {

	private StringBuilder contentBuilder;

	public TextMsg() {
		contentBuilder = new StringBuilder();
	}

	public TextMsg(String content) {
		setContent(content);
	}

	private static boolean isEmpty(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public String getContent() {
		return contentBuilder.toString();
	}

	public void setContent(String content) {
		contentBuilder = new StringBuilder(content);
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

	/**
	 * 在文本消息中添加点击文字跳转到小程序
	 * 限制：需要小程序和公众号有关联才可以进行跳转
	 *
	 * @param text  显示的标题
	 * @param appId 小程序的APP ID
	 * @param path  要跳转的小程序路径 为全路径 例如 pages/index/index
	 * @param url   当用户客户端不支持小程序时的备用连接 非必填
	 * @return
	 * @throws Exception 异常信息
	 */
	public TextMsg addMiniPrograms(String text, String appId, String path, String url) throws Exception {
		if (isEmpty(appId)) {
			throw new Exception("小程序ID不能为空");
		}
		if (isEmpty(path)) {
			throw new Exception("小程序访问路径不能为空");
		}
		appId = appId.trim();
		path = path.trim();
		contentBuilder.append("<a ")
				.append(" data-miniprogram-appid=\"").append(appId).append("\"")
				.append(" data-miniprogram-path=\"").append(path).append("\"");
		if (!isEmpty(url) && url.trim().startsWith("http")) {
			url = url.trim();
			contentBuilder.append(" href=\"").append(url).append("\"");
		}
		contentBuilder.append(">").append(text).append("</a>");
		return addln();
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
