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

	private static final int MAX_EXPIRE_SECONDS = 2592000;

	public QrcodeAPI(ApiConfig config) {
		super(config);
	}

	/**
	 * 创建字符串类型场景值二维码
	 *
	 * @param actionName    二维码类型，QR_STR_SCENE为临时,QR_LIMIT_STR_SCENE为永久
	 * @param sceneStr      场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
	 * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
	 * @return 二维码对象
	 */
	public QrcodeResponse createQrcode(QrcodeType actionName, String sceneStr, Integer expireSeconds) {
		return createQrcode(actionName, (Integer) null, sceneStr, expireSeconds);
	}


	/**
	 * 创建整形场景值二维码
	 *
	 * @param actionName    二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
	 * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
	 * @return 二维码对象
	 */
	public QrcodeResponse createQrcode(QrcodeType actionName, int sceneId, Integer expireSeconds) {

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
		Integer sceneIdInt = null;
		try {
			sceneIdInt = Integer.parseInt(sceneId);
		} catch (NumberFormatException e) {
			LOG.warn("场景值转换为数字异常");
		}
		return createQrcode(actionName, sceneIdInt, sceneStr, expireSeconds);
	}

	/**
	 * 创建二维码
	 *
	 * @param actionName    二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
	 * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @param sceneStr      场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
	 * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
	 * @return 二维码对象
	 */
	public QrcodeResponse createQrcode(QrcodeType actionName, Integer sceneId, String sceneStr, Integer expireSeconds) {
		BeanUtil.requireNonNull(actionName, "actionName is null");
		LOG.debug("创建二维码信息.....");
		if (BeanUtil.isNull(sceneId) && BeanUtil.isNull(sceneStr)) {
			throw new IllegalArgumentException("需要有场景值ID");
		}
		if (BeanUtil.nonNull(sceneId)) {
			if (sceneId < 1 || sceneId > 100000) {
				throw new IllegalArgumentException("sceneId:取值范围为 1-100000");
			}
		}
		if (StrUtil.isNotBlank(sceneStr)) {
			if (sceneStr.length() > 64) {
				throw new IllegalArgumentException("场景值sceneStr:长度限制为1到64位");
			}
		}

		QrcodeResponse response = null;
		String url = BASE_API_URL + "cgi-bin/qrcode/create?access_token=#";

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("action_name", actionName);
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		Map<String, Object> scene = new HashMap<String, Object>();
		if (BeanUtil.nonNull(sceneId)) {
			scene.put("scene_id", sceneId);
		}
		if (StrUtil.isNotBlank(sceneStr)) {
			scene.put("scene_str", sceneStr);
		}
		actionInfo.put("scene", scene);
		param.put("action_info", actionInfo);
		switch (actionName) {
			case QR_SCENE:
			case QR_STR_SCENE:
				if (BeanUtil.isNull(expireSeconds) || expireSeconds > MAX_EXPIRE_SECONDS || expireSeconds < 1) {
					throw new IllegalArgumentException("错误的场景二维码有效时间,取值范围为 1-2592000");
				}
				param.put("expire_seconds", expireSeconds);
				break;
			default:
				break;
		}
		BaseResponse r = executePost(url, JSONUtil.toJson(param));
		String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
		response = JSONUtil.toBean(resultJson, QrcodeResponse.class);
		return response;
	}
}
