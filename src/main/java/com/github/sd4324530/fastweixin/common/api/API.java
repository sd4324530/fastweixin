package com.github.sd4324530.fastweixin.common.api;


/**
 * Created by cl on 2018/4/27.
 * API接口
 */
public interface API<S, T> {

    T invoke(S request);

}
