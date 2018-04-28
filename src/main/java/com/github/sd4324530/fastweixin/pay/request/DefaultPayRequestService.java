package com.github.sd4324530.fastweixin.pay.request;

import com.github.sd4324530.fastweixin.CommonConstants;
import com.github.sd4324530.fastweixin.pay.config.PayConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * Created by cl on 2018/4/27.
 * 默认的支付请求服务实现
 */
public class DefaultPayRequestService implements PayRequestService {

    /* 连接超时时间 */
    protected Integer connectTimeout;
    /* 读取超时时间 */
    protected Integer readTimeout;

    /* 代理服务器地址 */
    protected String proxyHost;
    /* 代理服务器端口号 */
    protected Integer proxyPort;

    public DefaultPayRequestService() {
    }

    public DefaultPayRequestService(int connectTimeout, int readTimeout) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public DefaultPayRequestService(int connectTimeout, int readTimeout, String proxyHost, Integer proxyPort) {
        this(connectTimeout, readTimeout);
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    @Override
    public PayRequestResult request(PayConfig config, String url, String reqBody, boolean useCert) {
        BasicHttpClientConnectionManager connManager = null;
        ConnectionSocketFactory connSocketFactory = null;
        try {
            if (useCert) {
                // 加载证书
                char[] password = config.getMchID().toCharArray();
                KeyStore ks = KeyStore.getInstance("PKCS12");
                ks.load(config.getCertAsStream(), password);

                // 实例化密钥库，初始化密钥工厂
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(ks, password);

                // 创建 SSLContext
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

                connSocketFactory = new SSLConnectionSocketFactory(sslContext,
                        new String[]{"TLSv1"}, null, new DefaultHostnameVerifier());
            } else {
                connSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
            }

            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", connSocketFactory).build(),
                    null, null, null);

            HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
            RequestConfig requestConfig = this.buildRequestConfig();

            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(new StringEntity(reqBody, "UTF-8"));

            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity, "UTF-8");

            return new PayRequestResult(statusCode, result);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public PayRequestResult requestWithCert(PayConfig config, String url, String reqBody) {
        return this.request(config, url, reqBody, true);
    }

    @Override
    public PayRequestResult requestWithoutCert(PayConfig config, String url, String reqBody) {
        return this.request(config, url, reqBody, false);
    }

    /**
     * 构造RequestConfig，当未指定配置参数时取全局配置
     *
     * @return
     */
    protected RequestConfig buildRequestConfig() {
        RequestConfig.Builder configBuilder = RequestConfig.custom();

        // 超时配置
        Integer readTimeout = defaultValue(this.readTimeout, CommonConstants.READ_TIMEOUT);
        Integer connectTimeout = defaultValue(this.connectTimeout, CommonConstants.CONNECT_TIMEOUT);
        configBuilder.setSocketTimeout(readTimeout).setConnectTimeout(connectTimeout);

        // 代理设置
        String proxyHost = defaultValue(this.proxyHost, CommonConstants.PROXY_HOST);
        Integer proxyPort = defaultValue(this.proxyPort, CommonConstants.PROXY_PORT);
        if (null != proxyHost && null != proxyPort) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            configBuilder.setProxy(proxy);
        }

        return configBuilder.build();
    }

    protected <T> T defaultValue(T val, T defaultVal) {
        return null != val ? val : defaultVal;
    }

}
