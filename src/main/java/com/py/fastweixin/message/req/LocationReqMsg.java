package com.py.fastweixin.message.req;

public final class LocationReqMsg extends BaseReqMsg {

	private double locationX;
	private double locationY;
	private int scale;
	private String label;

	public double getLocationX() {
		return locationX;
	}

	public double getLocationY() {
		return locationY;
	}

	public int getScale() {
		return scale;
	}

	public String getLabel() {
		return label;
	}

	public LocationReqMsg(double locationX, double locationY, int scale,
			String label) {
		super();
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label = label;
		setMsgType(ReqType.LOCATION);
	}

	@Override
	public String toString() {
		return "LocationReqMsg [locationX=" + locationX + ", locationY="
				+ locationY + ", scale=" + scale + ", label=" + label
				+ ", toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + ", msgId=" + msgId + "]";
	}

}
