package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author peiyu
 */
public class InterfaceSummary extends BaseDataCube {

    @JSONField(name = "callback_count")
    private Integer callbackCount;
    @JSONField(name = "fail_count")
    private Integer failCount;
    @JSONField(name = "total_time_cost")
    private Integer totalTimeCost;
    @JSONField(name = "max_time_cost")
    private Integer maxTimeCost;

    public Integer getCallbackCount() {
        return callbackCount;
    }

    public void setCallbackCount(Integer callbackCount) {
        this.callbackCount = callbackCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getTotalTimeCost() {
        return totalTimeCost;
    }

    public void setTotalTimeCost(Integer totalTimeCost) {
        this.totalTimeCost = totalTimeCost;
    }

    public Integer getMaxTimeCost() {
        return maxTimeCost;
    }

    public void setMaxTimeCost(Integer maxTimeCost) {
        this.maxTimeCost = maxTimeCost;
    }
}
