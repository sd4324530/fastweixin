package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.util.StreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by cl on 2018/4/25.
 * 公共常量类
 */
public class CommonConstants {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommonConstants.class);

    /**
     * 签名类型枚举
     */
    public enum SignType {
        MD5, HMACSHA256
    }

    /* 连接超时时间 */
    public final static int CONNECT_TIMEOUT;
    /* 读取超时时间 */
    public final static int READ_TIMEOUT;

    /* 代理主机地址 */
    public final static String PROXY_HOST;
    /* 代理主机端口号 */
    public final static Integer PROXY_PORT;

    static {
        // 初始化全局配置
        String connectTimeout = "10000";
        String readTimeout = "10000";
        String proxyHost = null;
        String proxyPort = null;

        InputStream in = null;
        try {
            in = CommonConstants.class.getClassLoader().getResourceAsStream("fastweixin.properties");
            if (null != in) {
                Properties prop = new Properties();
                prop.load(in);

                connectTimeout = prop.getProperty("http.connectTimeout", connectTimeout);
                readTimeout = prop.getProperty("http.readTimeout", readTimeout);
                proxyHost = prop.getProperty("proxy.host");
                proxyPort = prop.getProperty("proxy.port");
            }
        } catch (Exception e) {
            LOGGER.error("fastweixin.properties配置文件读取失败\n" + e.getLocalizedMessage(), e);
        } finally {
            StreamUtil.closeWithWarn(in);
        }

        CONNECT_TIMEOUT = Integer.valueOf(connectTimeout);
        READ_TIMEOUT = Integer.valueOf(readTimeout);
        PROXY_HOST = proxyHost;
        PROXY_PORT = null != proxyPort ? Integer.valueOf(proxyPort) : null;
    }

    private CommonConstants() {
    }

}
