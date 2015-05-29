package com.github.sd4324530.fastweixin.api.config;

import com.github.sd4324530.fastweixin.api.entity.BaseModel;

import java.util.Date;

/**
 * 配置变化通知
 * @author peiyu
 */
public final class ConfigChangeNotice extends BaseModel {

    private Date noticeTime;

    private String appid;

    private ChangeType type;

    private String vlaue;

    public ConfigChangeNotice(){
        this.noticeTime = new Date();
    }

    public ConfigChangeNotice(String appid, ChangeType type, String vlaue) {
        this.appid = appid;
        this.type = type;
        this.vlaue = vlaue;
        this.noticeTime = new Date();
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

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String toString() {
        return "ConfigChangeNotice{" +
                "noticeTime=" + noticeTime +
                ", appid='" + appid + '\'' +
                ", type=" + type +
                ", vlaue='" + vlaue + '\'' +
                '}';
    }
}
