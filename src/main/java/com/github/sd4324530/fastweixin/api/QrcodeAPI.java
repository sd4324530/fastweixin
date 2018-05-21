package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.QrcodeType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.QrcodeResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 二维码相关API
 *
 * @author peiyu
 * @since 1.2
 */
public class QrcodeAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(QrcodeAPI.class);

    public QrcodeAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 创建二维码
     *
     * @param actionName    二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
     * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
     * @return 二维码对象
     */
    public QrcodeResponse createQrcode(QrcodeType actionName, String sceneId, Integer expireSeconds) {
        return createQrcode(actionName, sceneId, null, expireSeconds);
    }

    /**
     * 创建二维码
     *
     * @param actionName    二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
     * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param sceneStr      场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
     * @return 二维码对象
     */
    public QrcodeResponse createQrcode(QrcodeType actionName, String sceneId, String sceneStr, Integer expireSeconds) {
        BeanUtil.requireNonNull(actionName, "actionName is null");
        BeanUtil.requireNonNull(sceneId, "actionInfo is null");

        LOG.debug("创建二维码信息.....");

        QrcodeResponse response = null;
        String url = BASE_API_URL + "cgi-bin/qrcode/create?access_token=#";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("action_name", actionName);
        Map<String, Object> actionInfo = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        if (StrUtil.isNotBlank(sceneId))
            scene.put("scene_id", sceneId);
        if (StrUtil.isNotBlank(sceneStr))
            scene.put("scene_str", sceneStr);
        actionInfo.put("scene", scene);
        param.put("action_info", actionInfo);
        if (BeanUtil.nonNull(expireSeconds) && 0 != expireSeconds) {
            param.put("expire_seconds", expireSeconds);
        }
        BaseResponse r = executePost(url, JSONUtil.toJson(param));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, QrcodeResponse.class);
        return response;
    }
}
