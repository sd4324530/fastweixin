package com.github.sd4324530.fastweixin.api.request;

import com.github.sd4324530.fastweixin.api.entity.Model;
import com.github.sd4324530.fastweixin.util.JSONUtil;

/**
 * @author peiyu
 */
public class BaseRequest implements Model {

    public final String toJsonString() {
        return JSONUtil.toJson(this);
    }
}
