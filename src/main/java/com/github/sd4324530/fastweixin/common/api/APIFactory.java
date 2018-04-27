package com.github.sd4324530.fastweixin.common.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cl on 2018/4/27.
 * API接口工厂
 */
public class APIFactory<A extends API> {

    protected Map<String, A> apiMap = new HashMap();

    /**
     * 注册API接口
     *
     * @param name 接口名
     * @param api  API接口
     */
    public void registerAPI(String name, A api) {
        apiMap.put(name, api);
    }

    /**
     * 获取API接口
     *
     * @param name 接口名
     * @return
     */
    public A getAPI(String name) {
        return apiMap.get(name);
    }

}
