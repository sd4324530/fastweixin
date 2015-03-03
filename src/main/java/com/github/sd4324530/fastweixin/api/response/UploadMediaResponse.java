package com.github.sd4324530.fastweixin.api.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author peiyu
 */
public class UploadMediaResponse extends BaseResponse {

    @JSONField(name="type")
    private String type;
    @JSONField(name = "media_id")
    private String mediaId;
    @JSONField(name = "created_at")
    private Date   createdAt;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
