package com.github.sd4324530.fastweixin.message.req;

public class SendPicsInfoEvent extends BaseEvent {
	
	private String eventKey;
	private Integer count;
	private String picList;
	
	public SendPicsInfoEvent(String eventKey, Integer count, String picList) {
		super();
		this.eventKey = eventKey;
		this.count = count;
		this.picList = picList;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getPicList() {
		return picList;
	}

	public void setPicList(String picList) {
		this.picList = picList;
	}

	@Override
    public String toString() {
        return "MenuEvent [eventKey=" + eventKey + ", count=" + count + ", picList=" + picList + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + "]";
    }
}
