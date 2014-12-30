package com.github.sd4324530.fastweixin.api.response;

import com.github.sd4324530.fastweixin.api.entity.Model;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.StrUtil;

/**
 * 微信API响应报文对象基类
 *
 * @author peiyu
 */
public class BaseResponse implements Model {

    private String errcode;
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        //将接口返回的错误信息转换成中文，方便提示用户出错原因
        if (StrUtil.isNotBlank(this.errcode)) {
            return ResultType.get(this.errcode).getDescription();
        }
        return this.errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public final String toJsonString() {
        return JSONUtil.toJson(this);
    }
}
