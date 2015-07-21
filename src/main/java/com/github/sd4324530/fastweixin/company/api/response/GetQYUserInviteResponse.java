package com.github.sd4324530.fastweixin.company.api.response;/**
 * Created by Nottyjay on 2015/6/11.
 */

import com.github.sd4324530.fastweixin.api.response.BaseResponse;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 *          ====================================================================
 */
public class GetQYUserInviteResponse extends BaseResponse {
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
