package com.github.sd4324530.fastweixin.company.api;

import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.response.GetOauthUserInfoResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 企业号身份授权
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * -------------------------------------------------------------------
 * @author Nottyjay
 * @version 1.0.beta
 * @since 1.3.6
 * ====================================================================
 */
public class QYOauthAPI extends QYBaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(QYOauthAPI.class);

    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    public QYOauthAPI(QYAPIConfig config) {
        super(config);
    }

    /**
     * 生成回调url，这个结果要求用户在微信中打开，即可获得token，并指向redirectUrl
     * @param redirectUrl 用户自己设置的回调url
     * @param scope       授权作用域
     * @param state       用户自带参数
     * @return 回调url，用户在微信中打开即可开始授权
     */
    public String getOauthPageUrl(String redirectUrl, OauthScope scope, String state){
        if(StrUtil.isBlank(redirectUrl)){
            throw new NullPointerException("redirectUrl is null");
        }
        BeanUtil.requireNonNull(scope, "scope is null");
        String userstate = StrUtil.isBlank(state) ? "STATE" : state;
        String url = null;
        try{
            url = URLEncoder.encode(redirectUrl, "UTF-8");
        }catch (UnsupportedEncodingException e){
            LOG.error("异常", e);
        }
        StringBuffer stringBuffer = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?");
        stringBuffer.append("appid=").append(this.config.getCorpid())
                .append("&redirect_uri=").append(url)
                .append("&response_type=code&scope=").append(scope.toString())
                .append("&state=").append(userstate)
                .append("#wechat_redirect");
        return stringBuffer.toString();
    }

}
