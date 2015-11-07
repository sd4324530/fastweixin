package com.github.sd4324530.fastweixin.company.message;/**
 * Created by Nottyjay on 2015/6/12.
 */

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 * ====================================================================
 */
public class QYImageMsg extends QYBaseMsg{

    @JSONField(name = "image")
    private Image image;

    public QYImageMsg() { this.setMsgType("image"); }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setMediaId(String mediaId) { this.image = new Image(mediaId); }

    public class Image{
        @JSONField(name = "media_id")
        private String mediaId;

        public Image(String mediaId) {this.mediaId = mediaId;}

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
    }
}
