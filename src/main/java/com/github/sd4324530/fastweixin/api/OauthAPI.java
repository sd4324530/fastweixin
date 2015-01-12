package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 网页授权API
 *
 * @author peiyu
 */
public class OauthAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(OauthAPI.class);

    public OauthAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 生成回调url，这个结果要求用户在微信中打开，即可获得token，并指向redirectUrl
     *
     * @param redirectUrl 用户自己设置的回调地址
     * @param scope       授权作用域
     * @param state       用户自带参数
     * @return 回调url，用户在微信中打开即可开始授权
     */
    public String getOauthPageUrl(String redirectUrl, OauthScope scope, String state) {
        BeanUtil.requireNonNull(redirectUrl, "redirectUrl is null");
        BeanUtil.requireNonNull(scope, "scope is null");
        String userState = StrUtil.isBlank(state) ? "STATE" : state;
        String url = null;
        try {
            url = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("异常", e);
        }
        StringBuilder stringBuilder = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?");
        stringBuilder.append("appid=").append(this.config.getAppid())
                .append("&redirect_uri=").append(url)
                .append("&response_type=code&scope=").append(scope.toString())
                .append("&state=")
                .append(userState)
                .append("#wechat_redirect");
        return stringBuilder.toString();
    }

    /**
     * 用户同意授权后在回调url中会得到code，调用此方法用code换token以及openid，所以如果仅仅是授权openid，到这步就结束了
     *
     * @param code 授权后得到的code
     * @return token对象
     */
    public OauthGetTokenResponse getToken(String code) {
        BeanUtil.requireNonNull(code, "code is null");
        OauthGetTokenResponse response = null;
        String url = BASE_API_URL + "sns/oauth2/access_token?appid=" + this.config.getAppid() + "&secret=" + this.config.getSecret() + "&code=" + code + "&grant_type=authorization_code";
        BaseResponse r = executeGet(url);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), OauthGetTokenResponse.class);
        }
        return response;
    }

    /**
     * 刷新token
     *
     * @param refreshToken token对象中会包含refreshToken字段，通过这个字段再次刷新token
     * @return 全新的token对象
     */
    public OauthGetTokenResponse refreshToken(String refreshToken) {
        BeanUtil.requireNonNull(refreshToken, "refreshToken is null");
        OauthGetTokenResponse response = null;
        String url = BASE_API_URL + "sns/oauth2/refresh_token?appid=" + this.config.getAppid() + "&grant_type=refresh_token&refresh_token=" + refreshToken;
        BaseResponse r = executeGet(url);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), OauthGetTokenResponse.class);
        }
        return response;
    }

    /**
     * 获取用户详细信息
     *
     * @param token  token
     * @param openid 用户openid
     * @return 用户信息对象
     */
    public GetUserInfoResponse getUserInfo(String token, String openid) {
        BeanUtil.requireNonNull(token, "token is null");
        BeanUtil.requireNonNull(openid, "openid is null");
        GetUserInfoResponse response = null;
        String url = BASE_API_URL + "sns/userinfo?access_token=" + token + "&openid=" + openid + "&lang=zh_CN";
        BaseResponse r = executeGet(url);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUserInfoResponse.class);
        }
        return response;
    }

    /**
     * 校验token是否合法有效
     *
     * @param token  token
     * @param openid 用户openid
     * @return 是否合法有效
     */
    public boolean validToken(String token, String openid) {
        BeanUtil.requireNonNull(token, "token is null");
        BeanUtil.requireNonNull(openid, "openid is null");
        String url = BASE_API_URL + "sns/auth?access_token=" + token + "&openid=" + openid;
        BaseResponse r = executeGet(url);
        return "0".equals(r.getErrcode());
    }
}
