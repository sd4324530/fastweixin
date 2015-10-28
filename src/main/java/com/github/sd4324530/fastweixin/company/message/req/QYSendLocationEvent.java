package com.github.sd4324530.fastweixin.company.message.req;
/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since
 *  ====================================================================
 */
public class QYSendLocationEvent extends QYMenuEvent {

    private String locationX;
    private String locationY;
    private int scale;
    private String label;
    private String poiname;

    public QYSendLocationEvent(String eventKey, String locationX, String locationY, int scale, String label, String poiname) {
        super(eventKey);
        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
        this.poiname = poiname;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
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

    public String getPoiname() {
        return poiname;
    }

    public void setPoiname(String poiname) {
        this.poiname = poiname;
    }

    @Override
    public String toString(){
        return "QYMenuEvent [locationX=" + locationX + ", locationY=" + locationY
                + ", scale=" + scale + ", label=" + label + ", poiname=" + poiname
                + ", eventKey=" + getEventKey()
                + ", toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", event=" + event + ", agentId=" + agentId + "]";
    }
}
