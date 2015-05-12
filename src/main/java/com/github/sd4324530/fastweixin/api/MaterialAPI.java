package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.MaterialType;
import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.*;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import org.apache.http.client.config.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
public class MaterialAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(MaterialAPI.class);

    protected MaterialAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 上传永久素材文件。图片素材上限为5000，其他类型为1000
     * @param type 素材类型
     * @param file 素材文件
     * @return
     */
    public UploadMaterialResponse uploadMaterialFile(MaterialType type, File file){
        return uploadMaterialFile(type, file, null, null);
    }

    /**
     * 上传永久视频素材文件。
     * @param type 素材类型
     * @param file 素材文件
     * @param title 素材标题
     * @param introduction 素材描述信息
     * @return
     */
    public UploadMaterialResponse uploadMaterialFile(MaterialType type, File file, String title, String introduction){
        UploadMaterialResponse response;
        String url = "http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=#";
        final Map<String, String> param = new HashMap<String, String>();
        param.put("title", title);
        param.put("introduction", introduction);
        BaseResponse r = executePost(url, JSONUtil.toJson(param), file);
        response = JSONUtil.toBean(r.getErrmsg(), UploadMaterialResponse.class);
        return response;
    }

    /**
     * 上传图文消息素材
     * @param articles
     * @return
     */
    public UploadMaterialResponse uploadMaterialNews(List<Article> articles){
        UploadMaterialResponse response;
        String url = BASE_API_URL + "cgi-bin/material/add_news?access_token=#";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("articles", articles);
        BaseResponse r = executePost(url, JSONUtil.toJson(params));
        response = JSONUtil.toBean(r.getErrmsg(), UploadMaterialResponse.class);
        return response;
    }

    /**
     * 下载永久素材
     * @param mediaId
     * @return
     */
    public DownloadMaterialResponse downloadMaterial(String mediaId){
        DownloadMaterialResponse response = new DownloadMaterialResponse();
        String url = BASE_API_URL + "cgi-bin/material/get_material?access_token=#";
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(NetWorkCenter.CONNECT_TIMEOUT).setConnectTimeout(NetWorkCenter.CONNECT_TIMEOUT).setSocketTimeout(NetWorkCenter.CONNECT_TIMEOUT).build();
        return response;
    }

    /**
     * 获取已创建永久素材的数量
     * @return
     */
    public GetMaterialTotalCountResponse countMaterial(){
        GetMaterialTotalCountResponse response = null;
        String url = BASE_API_URL + "cgi-bin/material/get_materialcount?access_token=#";
        BaseResponse r = executeGet(url);
        response = JSONUtil.toBean(r.getErrmsg(), GetMaterialTotalCountResponse.class);
        return response;
    }

    /**
     * 获取素材列表
     * @param type
     * @param offset
     * @param count
     * @return
     */
    public GetMaterialListResponse batchGetMaterial(MediaType type, int offset, int count){
        if(offset < 0) offset = 0;
        if(count > 20) count = 20;
        if(count < 1) count = 1;

        GetMaterialListResponse response = null;
        String url = BASE_API_URL + "cgi-bin/material/batchget_material?access_token=#";
        BaseResponse r = executePost(url, null);
        response = JSONUtil.toBean(r.getErrmsg(), GetMaterialListResponse.class);

        return response;
    }

    /**
     * 删除一个永久素材
     * @param mediaId
     */
    public ResultType deleteMaterial(String mediaId) {
        String url = BASE_API_URL + "cgi-bin/material/del_material?access_token=#";
        final Map<String, String> param = new HashMap<String, String>();
        param.put("media_id", mediaId);
        BaseResponse response = executePost(url, JSONUtil.toJson(param));
        return ResultType.get(response.getErrcode());
    }
}
