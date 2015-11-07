package com.github.sd4324530.fastweixin.api.entity;

/**
 * Created by ld on 15/10/15.
 */
public class UserInfo extends BaseModel{
    private String openid;
    private String lang="zh_CN";
    public UserInfo(){

    }
    public UserInfo(String openid){
        this.openid=openid;
    }
    public UserInfo(String openid, String lang){
        this.openid=openid;
        this.lang=lang;
    }
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
