package com.py.fastweixin.message.req;

public class BaseEvent extends BaseReq {

	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public BaseEvent() {
		setMsgType(ReqType.EVENT);
	}

}
