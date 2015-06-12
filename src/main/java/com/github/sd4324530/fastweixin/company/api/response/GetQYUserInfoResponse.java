package com.github.sd4324530.fastweixin.company.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.company.api.entity.QYUser;

import java.util.Map;

/**
 *
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class GetQYUserInfoResponse extends BaseResponse {

    @JSONField(name = "userid")
    private String userId;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "departmnent")
    private Integer[] departments;
    @JSONField(name = "position")
    private String position;
    @JSONField(name = "mobile")
    private String mobile;
    @JSONField(name = "gender")
    private String gender;
    @JSONField(name = "email")
    private String email;
    @JSONField(name = "weixinid")
    private String weixinid;
    @JSONField(name = "avatar")
    private String avatar;
    @JSONField(name = "status")
    private Integer status;
    @JSONField(name = "extattr")
    private Map<String, Object> extattr;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getDepartments() {
        return departments;
    }

    public void setDepartments(Integer[] departments) {
        this.departments = departments;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Object> getExtattr() {
        return extattr;
    }

    public void setExtattr(Map<String, Object> extattr) {
        this.extattr = extattr;
    }

    public QYUser getUser(){
        return new QYUser(userId, name, departments, position, mobile, gender, email, weixinid, avatar, status, extattr);
    }
}
