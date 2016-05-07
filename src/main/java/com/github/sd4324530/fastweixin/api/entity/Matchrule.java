package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author peiyu
 * @since 1.3.7
 */
public class Matchrule extends BaseModel {

    @JSONField(name = "tag_id")
    private String tagId;

    @Deprecated
    @JSONField(name = "group_id")
    private String groupId;

    private String sex;

    private String country;

    private String province;

    private String city;

    @JSONField(name = "client_platform_type")
    private String clientPlatformType;

    /**
     * @deprecated 微信官方不再建议使用群组,用标签代替
     * @return 群组ID
     */
    @Deprecated
    public String getGroupId() {
        return groupId;
    }

    /**
     * @deprecated 微信官方不再建议使用群组,用标签代替
     * @param groupId 群组ID
     */
    @Deprecated
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClientPlatformType() {
        return clientPlatformType;
    }

    public void setClientPlatformType(String clientPlatformType) {
        this.clientPlatformType = clientPlatformType;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
