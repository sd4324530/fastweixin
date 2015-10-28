package com.github.sd4324530.fastweixin.company.message.req;
/**
 *  微信企业号扫码事件
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYScanCodeEvent extends QYMenuEvent {

    private String scanType;
    private String scanResult;

    public QYScanCodeEvent(String eventKey, String scanType, String scanResult) {
        super(eventKey);
        this.scanType = scanType;
        this.scanResult = scanResult;
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
    public String toString(){
        return "QYMenuEvent [scanType=" + scanType + ", scanResult=" + scanResult+ ", eventKey=" + getEventKey()
                + ", toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", event=" + event + ", agentId=" + agentId + "]";
    }
}
