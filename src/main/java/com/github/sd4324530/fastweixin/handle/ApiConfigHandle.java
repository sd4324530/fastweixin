package com.github.sd4324530.fastweixin.handle;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 *          ====================================================================
 */
public interface ApiConfigHandle {

    /**
     * 修改ApiConfig
     *
     * @param config 微信API配置
     */
    void change(ApiConfig config);
}
