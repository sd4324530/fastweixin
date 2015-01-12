package com.github.sd4324530.fastweixin.api.enums;

/**
 * Oauth授权作用域枚举
 *
 * @author peiyu
 */
public enum OauthScope {

    /**
     * 仅仅获取用户openid
     */
    SNSAPI_BASE("snsapi_base"),

    /**
     * 获取用户完整信息
     */
    SNSAPI_USERINFO("snsapi_userinfo");

    String value;

    private OauthScope(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
