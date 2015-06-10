package com.github.sd4324530.fastweixin.company.api;

import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.entity.User;
import com.github.sd4324530.fastweixin.util.JSONUtil;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class QYUserAPI extends BaseAPI {


    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    public QYUserAPI(QYAPIConfig config) {
        super(config);
    }

    /**
     * 创建一个新用户
     * @param user
     * @return
     */
    public ResultType create(User user){
        String url = BASE_API_URL + "cgi-bin/user/create?access_token=#";
        BaseResponse response = executePost(url, JSONUtil.toJson(user));
        return ResultType.get(response.getErrcode());
    }


}
