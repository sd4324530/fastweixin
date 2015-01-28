package com.github.sd4324530.fastweixin.api.response;

import com.github.sd4324530.fastweixin.api.entity.UserShareHour;

import java.util.List;

/**
 * @author peiyu
 */
public class GetUserShareHourResponse extends BaseResponse {

    private List<UserShareHour> list;

    public List<UserShareHour> getList() {
        return list;
    }

    public void setList(List<UserShareHour> list) {
        this.list = list;
    }
}
