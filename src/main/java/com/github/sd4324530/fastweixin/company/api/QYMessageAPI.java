package com.github.sd4324530.fastweixin.company.api;/**
 * Created by Nottyjay on 2015/6/12.
 */

import com.github.sd4324530.fastweixin.api.BaseAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.company.api.response.GetQYSendMessageResponse;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 * ====================================================================
 */
public class QYMessageAPI extends BaseAPI {


    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    public QYMessageAPI(ApiConfig config) {
        super(config);
    }

    public GetQYSendMessageResponse send(){

        return null;
    }
}
