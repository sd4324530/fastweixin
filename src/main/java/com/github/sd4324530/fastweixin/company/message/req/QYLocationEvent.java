package com.github.sd4324530.fastweixin.company.message.req;
/**
 *  微信企业号地理位置上报事件
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYLocationEvent extends QYBaseEvent {

    private double latitude;
    private double longitude;
    private double precision;

    public QYLocationEvent(double latitude, double longitude, double precision) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
        setEvent(QYEventType.LOCATION);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    @Override
    public String toString(){
        return "QYLocationEvent [latitude=" + latitude + ", longitude" + longitude
                + ", precision=" + precision + ", toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", event=" + event + ", agentId=" + agentId + "]";
    }
}
