package com.github.sd4324530.fastweixin.api.enums;

/**
 * @author peiyu
 */
public enum MediaType {

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
     * 缩略图
     */
    THUMB("thumb");

    String value;

    MediaType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
