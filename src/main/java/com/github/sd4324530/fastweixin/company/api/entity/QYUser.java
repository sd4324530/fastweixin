package com.github.sd4324530.fastweixin.company.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.api.entity.BaseModel;

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
public class QYUser extends BaseModel {

    public final class Gender{
        public static final String MAN = "1";
        public static final String WOMAN = "2";
    }

    @JSONField(name = "userid")
    private String userId;// 用户ID。必须唯一。可用随机数或UUID代替此值
    private String name;// 用户姓名
    private Integer[] department;// 部门
    private String position;// 职务
    private String mobile;// 手机
    private String gender;// 性别
    private String email;// 邮箱
    private String weixinid;// 微信号。不是微信名称
    @JSONField(name = "avatar_mediaid")
    private String avatarMdiaid;//创建用户时头像的mediaid
    private String avatar;//更新用户时，用户头像链接地址
    private Integer status;// 关注状态: 1=已关注，2=已冻结，4=未关注
    private Map<String, Object> extattr;

    public QYUser() {
    }

    public QYUser(String userId, String name, Integer[] department, String position, String mobile, String gender, String email, String weixinid, Map<String, Object> extattr) {
        this.userId = userId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.mobile = mobile;
        this.gender = gender;
        this.email = email;
        this.weixinid = weixinid;
        this.extattr = extattr;
    }

    public QYUser(String userId, String name, Integer[] department, String position, String mobile, String gender, String email, String weixinid, String avatar, Integer status, Map<String, Object> extattr) {
        this.userId = userId;
        this.name = name;
        this.position = position;
        this.mobile = mobile;
        this.gender = gender;
        this.email = email;
        this.weixinid = weixinid;
        this.avatar = avatar;
        this.status = status;
        this.extattr = extattr;
    }

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

    public Integer[] getDepartment() {
        return department;
    }

    public void setDepartment(Integer[] department) {
        if(department.length != 0) {
            this.department = department;
        }
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

    public String getAvatarMdiaid() {
        return avatarMdiaid;
    }

    public void setAvatarMdiaid(String avatarMdiaid) {
        this.avatarMdiaid = avatarMdiaid;
    }
}
