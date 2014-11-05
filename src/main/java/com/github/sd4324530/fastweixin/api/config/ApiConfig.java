package com.github.sd4324530.fastweixin.api.config;

/**
 * API配置类，项目中请保证其为单例
 * @author peiyu
 */
public final class ApiConfig {

    private String appid;

    private String secret;

    private String access_token;

    public volatile boolean refreshing = false;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public static class Builder {
        private String appid;

        private String secret;

        public Builder setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public Builder setSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public ApiConfig build() {
            ApiConfig config = new ApiConfig();
            config.appid = this.appid;
            config.secret = this.secret;
            return config;
        }
    }
}
