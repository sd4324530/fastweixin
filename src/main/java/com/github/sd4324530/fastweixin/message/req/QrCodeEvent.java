package com.github.sd4324530.fastweixin.message.req;

public final class QrCodeEvent extends BaseEvent {

	private String eventKey;
	private String ticket;

	public String getEventKey() {
		return eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public QrCodeEvent(String eventKey, String ticket) {
		super();
		this.eventKey = eventKey;
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "QrCodeEvent [eventKey=" + eventKey + ", ticket=" + ticket
				+ ", toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + "]";
	}

}
