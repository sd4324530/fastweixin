package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author peiyu
 */
public class UpstreamMsgDist extends BaseDataCube {

    @JSONField(name = "count_interval")
    private Integer countInterval;
    @JSONField(name = "msg_user")
    private Integer msgUser;

    public Integer getCountInterval() {
        return countInterval;
    }

    public void setCountInterval(Integer countInterval) {
        this.countInterval = countInterval;
    }

    public Integer getMsgUser() {
        return msgUser;
    }

    public void setMsgUser(Integer msgUser) {
        this.msgUser = msgUser;
    }
}
