package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.TemplateKeyword;
import com.github.sd4324530.fastweixin.api.enums.IndustryType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetTemplateIdResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class TemplateAPI extends BaseAPI {

    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    public TemplateAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 设置行业与副行业信息
     * @param mainIndustry 主行业
     * @param sideIndustry 副行业
     * @return
     */
    public ResultType setIndustry(IndustryType mainIndustry, IndustryType sideIndustry){
        String url = BASE_API_URL + "cgi-bin/template/api_set_industry?access_token=#";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("industry_id1", mainIndustry.getCode());
        params.put("industry_id2", sideIndustry.getCode());
        BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 调用模板
     * @param templateCode
     */
    public GetTemplateIdResponse getTemplateId(String templateCode){
        GetTemplateIdResponse response = null;
        String url = BASE_API_URL + "cgi-bin/template/api_add_template?access_token=#";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("template_id_short", templateCode);
        BaseResponse r = executePost(url, JSONUtil.toJson(params));
        response = JSONUtil.toBean(r.getErrmsg(), GetTemplateIdResponse.class);
        return response;
    }

    /**
     * 发送消息
     * @param openId
     * @param templateId
     * @param toUrl
     * @param topColor
     * @param templateParams
     */
    public void send(String openId, String templateId, String toUrl, String topColor, Map<String, TemplateKeyword> templateParams){
        String url = BASE_API_URL + "cgi-bin/message/template/send?access_token=#";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openId);
        params.put("template_id", templateId);
        params.put("url", toUrl);
        params.put("data", templateParams);
        BaseResponse r = executePost(url, JSONUtil.toJson(params));
    }
}
