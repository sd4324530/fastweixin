package com.github.sd4324530.fastweixin.api.config;

import com.github.sd4324530.fastweixin.api.response.GetJsApiTicketResponse;
import com.github.sd4324530.fastweixin.api.response.GetTokenResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.apache.http.HttpStatus;

/**
 * Created by cl on 2018/4/24.
 * 默认的Token服务实现，直接通过请求微信官方api接口获取
 */
public class DefaultTokenService implements TokenService {

    @Override
    public void getAccessToken(ApiConfig apiConfig, final TokenResponseCallback callback) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
                apiConfig.getAppid() + "&secret=" + apiConfig.getSecret();
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (HttpStatus.SC_OK == resultCode) {
                    GetTokenResponse resp = JSONUtil.toBean(resultJson, GetTokenResponse.class);
                    if (null != resp.getAccessToken()) {
                        callback.onSuccess(new TokenResponse(resp.getAccessToken(), resp.getExpiresIn()));
                    } else {
                        callback.onFailure(new TokenResponse(resp.getErrcode(), resp.getErrmsg()));
                    }
                } else {
                    callback.onFailure(new TokenResponse(String.valueOf(resultCode), "Http请求异常，状态码：" + resultCode));
                }
            }
        });
    }

    @Override
    public void getJsApiTicket(ApiConfig apiConfig, final TokenResponseCallback callback) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" +
                apiConfig.getAccessToken() + "&type=jsapi";
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (HttpStatus.SC_OK == resultCode) {
                    GetJsApiTicketResponse resp = JSONUtil.toBean(resultJson, GetJsApiTicketResponse.class);
                    if (StrUtil.isNotBlank(resp.getTicket())) {
                        callback.onSuccess(new TokenResponse(resp.getTicket(), resp.getExpiresIn()));
                    } else {
                        callback.onFailure(new TokenResponse(resp.getErrcode(), resp.getErrmsg()));
                    }
                } else {
                    callback.onFailure(new TokenResponse(String.valueOf(resultCode), "Http请求异常，状态码：" + resultCode));
                }
            }
        });
    }

}
