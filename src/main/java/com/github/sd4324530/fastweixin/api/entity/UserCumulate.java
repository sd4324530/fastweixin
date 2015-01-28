package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 累计用户数据
 *
 * @author peiyu
 */
public class UserCumulate extends BaseDataCube {

    @JSONField(name = "cumulate_user")
    private Integer cumulateUser;

    public Integer getCumulateUser() {
        return cumulateUser;
    }

    public void setCumulateUser(Integer cumulateUser) {
        this.cumulateUser = cumulateUser;
    }
}
