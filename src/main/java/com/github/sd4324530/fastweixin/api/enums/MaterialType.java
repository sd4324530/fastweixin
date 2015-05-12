package com.github.sd4324530.fastweixin.api.enums;

/**
 * Created by jileilei on 15/5/12.
 */
public enum MaterialType {

    /**
     * 图片
     */
    IMAGE("image"),

    /**
     * 语音
     */
    VOICE("voice"),

    /**
     * 视频
     */
    VIDEO("video"),

    /**
     * 图文消息
     */
    NEWS("news");

    String value;

    private MaterialType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
