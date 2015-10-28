package com.github.sd4324530.fastweixin.company.message.req;
/**
 *  微信企业号位置消息事件
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYLocationReqMsg extends QYBaseReqMsg {

    private double locationX;
    private double locationY;
    private int scale;
    private String label;

    public QYLocationReqMsg(double locationX, double locationY, int scale, String label) {
        super();
        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
        setMsgType(QYReqType.LOCATION);
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString(){
        return "QYLocationReqMsg [location_x=" + locationX
                + ", location_y=" + locationY + ", scale=" + scale
                + ", label=" + label + ", toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", msgId=" + msgId + ", agentId=" + agentId + "]";
    }
}
