package com.github.sd4324530.fastweixin.pay.entity.response;

import java.io.Serializable;

/**
 * Created by cl on 2018/4/25.
 */
public abstract class BaseResponse implements Serializable {

    /* 返回状态码 */
    private String return_code;
    /* 返回信息 */
    private String return_msg;

    /* 业务结果 */
    private String result_code;
    /* 错误代码 */
    private String err_code;
    /* 错误代码描述 */
    private String err_code_des;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

}
