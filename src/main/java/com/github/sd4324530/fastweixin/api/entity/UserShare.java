package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author peiyu
 */
public class UserShare extends BaseDataCube {

    @JSONField(name = "share_scene")
    private Integer shareScene;
    @JSONField(name = "share_count")
    private Integer shareCount;
    @JSONField(name = "share_user")
    private Integer shareUser;

    public Integer getShareScene() {
        return shareScene;
    }

    public void setShareScene(Integer shareScene) {
        this.shareScene = shareScene;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getShareUser() {
        return shareUser;
    }

    public void setShareUser(Integer shareUser) {
        this.shareUser = shareUser;
    }
}
