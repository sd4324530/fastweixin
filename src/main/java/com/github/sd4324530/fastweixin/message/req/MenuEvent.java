package com.github.sd4324530.fastweixin.message.req;

public final class MenuEvent extends BaseEvent {

    private String eventKey;

    public MenuEvent(String eventKey) {
        super();
        this.eventKey = eventKey;
    }

    public String getEventKey() {
        return eventKey;
    }

    @Override
    public String getEvent() {
        return super.getEvent();
    }

    @Override
    public String toString() {
        return "MenuEvent [eventKey=" + eventKey + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + "]";
    }

}
