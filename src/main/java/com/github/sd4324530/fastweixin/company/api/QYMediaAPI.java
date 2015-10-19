package com.github.sd4324530.fastweixin.company.api;

import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.response.DownloadMediaResponse;
import com.github.sd4324530.fastweixin.company.api.response.UploadMediaResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import com.github.sd4324530.fastweixin.util.StreamUtil;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
     * @return 上传结果
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

    /**
     * 下载媒体文件
     * @param mediaId 媒体ID
     * @return 下载结果
     */
    public DownloadMediaResponse download(String mediaId){
        DownloadMediaResponse response = new DownloadMediaResponse();
        String url = BASE_API_URL + "cgi-bin/media/get?access_token=" + config.getAccessToken() + "&media_id=" + mediaId;
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(NetWorkCenter.CONNECT_TIMEOUT).setConnectTimeout(NetWorkCenter.CONNECT_TIMEOUT).setSocketTimeout(NetWorkCenter.CONNECT_TIMEOUT).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        HttpGet get = new HttpGet(url);
        try{
            CloseableHttpResponse r = client.execute(get);
            if(HttpStatus.SC_OK == r.getStatusLine().getStatusCode()){
                InputStream inputStream = r.getEntity().getContent();
                Header[] headers = r.getHeaders("Content-disposition");
                if (null != headers && 0 != headers.length) {
                    Header length = r.getHeaders("Content-Length")[0];
                    response.setContent(inputStream, Integer.valueOf(length.getValue()));
                    response.setFileName(headers[0].getElements()[0].getParameterByName("filename").getValue());
                } else {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    StreamUtil.copy(inputStream, out);
                    String json = out.toString();
                    response = JSONUtil.toBean(json, DownloadMediaResponse.class);
                }
            }
        } catch (Exception e) {
            LOG.error("异常", e);
        }finally {
            try{
                client.close();
            }catch (IOException e){
                LOG.error("异常", e);
            }
        }
        return response;
    }

}
