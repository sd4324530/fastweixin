package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用户增减数据
 *
 * @author peiyu
 */
public class UserSummary extends BaseDataCube {

    /**
     * 用户的渠道，数值代表的含义如下：
     * 0代表其他 30代表扫二维码 17代表名片分享 35代表搜号码（即微信添加朋友页的搜索） 39代表查询微信公众帐号 43代表图文页右上角菜单
     */
    @JSONField(name = "user_source")
    private Integer userSource;
    /**
     * 新增的用户数量
     */
    @JSONField(name = "new_user")
    private Integer newUser;
    /**
     * 取消关注的用户数量，new_user减去cancel_user即为净增用户数量
     */
    @JSONField(name = "cancel_user")
    private Integer cancelUser;

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }

    public Integer getNewUser() {
        return newUser;
    }

    public void setNewUser(Integer newUser) {
        this.newUser = newUser;
    }

    public Integer getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(Integer cancelUser) {
        this.cancelUser = cancelUser;
    }
}
