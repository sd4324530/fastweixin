package com.github.sd4324530.fastweixin.message.req;

public final class ScanCodeEvent extends BaseEvent {

	private String eventKey;
	private String scanType;
	private String scanResult;

    public ScanCodeEvent(String eventKey, String scanType, String scanResult) {
        super();
        this.eventKey = eventKey;
        this.scanType = scanType;
        this.scanResult = scanResult;
    }

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public String getScanResult() {
		return scanResult;
	}

	public void setScanResult(String scanResult) {
		this.scanResult = scanResult;
	}

	@Override
    public String toString() {
        return "ScanCodeEvent [eventKey=" + eventKey + ", scanType=" + scanType + ", scanResult=" + scanResult + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + "]";
    }
}
