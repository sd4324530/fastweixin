package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class DownloadTest {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadTest.class);

    @Test
    public void download(){
        String url = "http://103.7.29.197/vweixinp.tc.qq.com/1007_efd683683fdd41ea9a999ea9ad81f239.f10.mp4?vkey=E79319C2D663CACB3A26812DBE3DC05F33EE92DEA914480FB88A77EF92832D1EDA51FFD2B275A7E9&sha=0&save=1";
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(NetWorkCenter.CONNECT_TIMEOUT).setConnectTimeout(NetWorkCenter.CONNECT_TIMEOUT).setSocketTimeout(NetWorkCenter.CONNECT_TIMEOUT).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse r = client.execute(get);
            LOG.debug("Status code: " + r.getStatusLine().getStatusCode());
            if (HttpStatus.SC_OK == r.getStatusLine().getStatusCode()) {
                InputStream inputStream = r.getEntity().getContent();
                Header[] headers = r.getHeaders("Content-disposition");
                if (null != headers && 0 != headers.length) {
                    Header length = r.getHeaders("Content-Length")[0];
                }
            }
        }catch (IOException e){
            LOG.error("IO异常处理", e);
        }
    }
}
