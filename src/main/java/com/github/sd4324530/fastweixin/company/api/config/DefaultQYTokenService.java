package com.github.sd4324530.fastweixin.company.api.config;


import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.config.DefaultTokenService;
import com.github.sd4324530.fastweixin.api.config.TokenService;

/**
 * Created by cl on 2018/4/24.
 * 默认的企业Token服务实现，直接通过请求微信官方api接口获取
 */
public class DefaultQYTokenService extends DefaultTokenService implements TokenService {

    @Override
    protected String generateAccessTokenUrl(ApiConfig apiConfig) {
        return "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + apiConfig.getAppid() + "&corpsecret=" + apiConfig.getSecret();
    }

    @Override
    protected String generateJsApiTicketUrl(ApiConfig apiConfig) {
        return "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=" + apiConfig.getAccessToken();
    }

}
