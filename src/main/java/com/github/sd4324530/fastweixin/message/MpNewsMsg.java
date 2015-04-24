package com.github.sd4324530.fastweixin.message;
/**
 * 提交至微信的图文消息素材
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 * @author Nottyjay
 * @version 1.0.beta
 * ====================================================================
 */
public class MpNewsMsg extends BaseMsg {

    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
