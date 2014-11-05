package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.QrcodeResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;

/**
 * 二维码相关API
 * @author peiyu
 */
public class QrcodeAPI extends BaseAPI {
    public QrcodeAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 创建二维码
     * @param actionName
     * @param actionInfo
     * @param expireSeconds
     * @return
     */
    public QrcodeResponse createQrcode(String actionName, String actionInfo, Integer expireSeconds) {
        BeanUtil.requireNonNull(actionName, "actionName is null");
        BeanUtil.requireNonNull(actionInfo, "actionInfo is null");

        QrcodeResponse response = null;
        String url = BASE_API_URL + "cgi-bin/qrcode/create?access_token=#";

        BaseResponse r = executePost(url, null);
        if(null == r.getErrcode() || "".equals(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), QrcodeResponse.class);
        }
        return response;
    }
}
