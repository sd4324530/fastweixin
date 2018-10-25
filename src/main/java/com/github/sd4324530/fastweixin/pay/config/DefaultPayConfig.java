package com.github.sd4324530.fastweixin.pay.config;

import com.github.sd4324530.fastweixin.util.StreamUtil;

import java.io.*;

/**
 * Created by cl on 2018/4/26.
 * 支付配置默认实现
 */
public class DefaultPayConfig implements PayConfig {

    /* 商户/公众号AppID */
    protected String appID;
    /* 商户ID */
    protected String mchID;
    /* API秘钥 */
    protected String key;
    /* 证书数据 */
    protected byte[] certBytes;

    public DefaultPayConfig(String appID, String mchID, String key) {
        this(appID, mchID, key, null);
    }

    public DefaultPayConfig(String appID, String mchID, String key, String certPath) {
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;

        if (null != certPath) {
            InputStream in = null;
            try {
                File file = new File(certPath);
                in = new FileInputStream(file);
                certBytes = new byte[(int) file.length()];
                in.read(certBytes);
            } catch (IOException e) {
                throw new RuntimeException("证书[" + certPath + "]加载失败\n" + e.getLocalizedMessage(), e);
            } finally {
                StreamUtil.closeWithWarn(in);
            }
        }
    }

    @Override
    public String getAppID() {
        return appID;
    }

    @Override
    public String getMchID() {
        return mchID;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertAsStream() {
        return new ByteArrayInputStream(certBytes);
    }

}
