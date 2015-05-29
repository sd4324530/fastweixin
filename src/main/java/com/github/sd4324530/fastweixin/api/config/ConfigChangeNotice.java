package com.github.sd4324530.fastweixin.api.config;

import com.github.sd4324530.fastweixin.api.entity.BaseModel;

/**
 * 配置变化通知
 * @author peiyu
 */
public final class ConfigChangeNotice extends BaseModel {
    private String appid;

    private ChangeType type;

    private String vlaue;

    public ConfigChangeNotice(){}

    public ConfigChangeNotice(String appid, ChangeType type, String vlaue) {
        this.appid = appid;
        this.type = type;
        this.vlaue = vlaue;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public ChangeType getType() {
        return type;
    }

    public void setType(ChangeType type) {
        this.type = type;
    }

    public String getVlaue() {
        return vlaue;
    }

    public void setVlaue(String vlaue) {
        this.vlaue = vlaue;
    }

    @Override
    public String toString() {
        return "ConfigChangeNotice{" +
                "appid='" + appid + '\'' +
                ", type=" + type +
                ", vlaue='" + vlaue + '\'' +
                '}';
    }
}
