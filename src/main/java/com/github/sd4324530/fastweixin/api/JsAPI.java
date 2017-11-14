package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfigInterface;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.GetSignatureResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JsApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 微信js-sdk相关API
 * @author peiyu
 */
public class JsAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(JsAPI.class);

    public JsAPI(ApiConfigInterface config) {
        super(config);
    }

    /**
     * 获取js-sdk所需的签名，简化逻辑
     * 不太在意随机数和时间戳的场景，使用更加方便
     * @param url 当前网页的URL，不包含#及其后面部分
     * @return 签名以及相关参数
     */
    public GetSignatureResponse getSignature(String url) {
        BeanUtil.requireNonNull(url, "请传入当前网页的URL，不包含#及其后面部分");
        //当前时间的秒数
        long timestame = System.currentTimeMillis() / 1000;
        //使用UUID来当随机字符串
        String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
        return getSignature(nonceStr, timestame, url);
    }

    /**
     * 获取js-sdk所需的签名，给调用者最大的自由度，控制生成签名的参数
     * @param nonceStr 随机字符串
     * @param timestame 时间戳
     * @param url 当前网页的URL，不包含#及其后面部分
     * @return 签名以及相关参数
     */
    public GetSignatureResponse getSignature(String nonceStr, long timestame, String url) {
        BeanUtil.requireNonNull(url, "请传入当前网页的URL，不包含#及其后面部分");
        GetSignatureResponse response = new GetSignatureResponse();
        String jsApiTicket = this.config.getJsApiTicket();
        String sign;
        try {
            sign = JsApiUtil.sign(jsApiTicket, nonceStr, timestame, url);
        } catch (Exception e) {
            LOG.error("获取签名异常:", e);
            response.setErrcode(ResultType.OTHER_ERROR.getCode().toString());
            response.setErrmsg("获取签名异常");
            return response;
        }
        response.setNoncestr(nonceStr);
        response.setSignature(sign);
        response.setTimestamp(timestame);
        response.setUrl(url);
        response.setErrcode(ResultType.SUCCESS.getCode().toString());
        return response;
    }
}
