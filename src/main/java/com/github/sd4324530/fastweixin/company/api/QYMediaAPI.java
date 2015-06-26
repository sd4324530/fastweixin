package com.github.sd4324530.fastweixin.company.api;

import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.response.UploadMediaResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class QYMediaAPI extends QYBaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(QYMediaAPI.class);

    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    public QYMediaAPI(QYAPIConfig config) {
        super(config);
    }

    /**
     * 上传媒体文件
     * @param file 媒体文件
     * @param type 媒体文件类型
     */
    public UploadMediaResponse upload(MediaType type, File file){
        if(type == MediaType.NEWS){
            LOG.debug("企业号媒体素材不包含新闻列表");
            return null;
        }
        UploadMediaResponse response;
        String url = BASE_API_URL + "cgi-bin/media/upload?access_token=#&type=" + type.toString();
        BaseResponse r = executePost(url, null, file);
        response = JSONUtil.toBean(r.getErrmsg(), UploadMediaResponse.class);
        return response;
    }

//    public
}
