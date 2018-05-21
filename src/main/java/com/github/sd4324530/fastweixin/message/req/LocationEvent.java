package com.github.sd4324530.fastweixin.message.req;

public final class LocationEvent extends BaseEvent {

    private double latitude;
    private double longitude;
    private double precision;

    public LocationEvent(double latitude, double longitude, double precision) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getPrecision() {
        return precision;
    }

    @Override
    public String toString() {
        return "LocationEvent [latitude=" + latitude + ", longitude="
                + longitude + ", precision=" + precision + ", toUserName="
                + toUserName + ", fromUserName=" + fromUserName
                + ", createTime=" + createTime + ", msgType=" + msgType + "]";
    }

}
