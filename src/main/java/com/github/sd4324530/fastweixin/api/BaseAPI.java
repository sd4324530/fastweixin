package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetJsApiTicketResponse;
import com.github.sd4324530.fastweixin.api.response.GetTokenResponse;
import com.github.sd4324530.fastweixin.handle.ApiConfigHandle;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.CollectionUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * API基类，提供一些通用方法
 * 包含自动刷新token、通用get post请求等
 *
 * @author peiyu
 * @since 1.2
 */
public abstract class BaseAPI {

    protected static final String        BASE_API_URL = "https://api.weixin.qq.com/";
    private static final   Logger        LOG          = LoggerFactory.getLogger(BaseAPI.class);
    //用于刷新token时锁住config，防止多次刷新
    private final          ReadWriteLock lock         = new ReentrantReadWriteLock();
    private final          Lock          readLock     = lock.readLock();
    private final          Lock          writeLock    = lock.writeLock();

    protected final ApiConfig       config;
    private         ApiConfigHandle handle;

    /**
     * 构造方法，设置apiConfig
     * @param config 微信API配置对象
     */
    protected BaseAPI(ApiConfig config) {
        this.config = config;
    }

    protected void setApiConfigHandle(ApiConfigHandle handle) {
        this.handle = handle;
    }

    /**
     * 刷新token
     */
    protected void refreshToken() {
        LOG.debug("开始刷新access_token......");
        if (!config.refreshing.get()) {
            writeLock.lock();
            try {
                if (config.refreshing.compareAndSet(false, true)) {
                    String url = BASE_API_URL + "cgi-bin/token?grant_type=client_credential&appid=" + this.config.getAppid() + "&secret=" + this.config.getSecret();
                    NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
                        @Override
                        public void onResponse(int resultCode, String resultJson) {
                            if (HttpStatus.SC_OK == resultCode) {
                                GetTokenResponse response = JSONUtil.toBean(resultJson, GetTokenResponse.class);
                                BaseAPI.this.config.setAccessToken(response.getAccessToken());
                                LOG.debug("刷新access_token成功.....");
                            } else {
                                LOG.warn("获取access_token失败....");
                                LOG.warn("信息:{}", resultJson);
                            }
                        }
                    });

                    if (config.isEnableJsApi()) {
                        LOG.debug("开始刷新 jsapi_ticket........");
                        String url2 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + config.getAccessToken() + "&type=jsapi";
                        NetWorkCenter.get(url2, null, new NetWorkCenter.ResponseCallback() {
                            @Override
                            public void onResponse(int resultCode, String resultJson) {
                                if (HttpStatus.SC_OK == resultCode) {
                                    GetJsApiTicketResponse response = JSONUtil.toBean(resultJson, GetJsApiTicketResponse.class);
                                    LOG.debug("获取jsapi_ticket:{}", response.getTicket());
                                    config.setJsApiTicket(response.getTicket());
                                }
                            }
                        });
                    }
                }
            } finally {
                if (null != handle) {
                    LOG.debug("更新ApiConfig Token");
                    handle.change(config);
                }
                config.refreshing.set(false);
                writeLock.unlock();
            }
        }
    }

    /**
     * 通用post请求
     *
     * @param url  地址，其中token用#代替
     * @param json 参数，json格式
     * @return 请求结果
     */
    protected BaseResponse executePost(String url, String json) {
        return executePost(url, json, null);
    }

    /**
     * 通用post请求
     *
     * @param url  地址，其中token用#代替
     * @param json 参数，json格式
     * @param file 上传的文件
     * @return 请求结果
     */
    protected BaseResponse executePost(String url, String json, File file) {
        BaseResponse response = null;
        BeanUtil.requireNonNull(url, "url is null");
        String postUrl;
        List<File> files = null;
        if (null != file) {
            files = CollectionUtil.newArrayList(file);
        }
        readLock.lock();
        try {
            //需要传token
            postUrl = url.replace("#", config.getAccessToken());
            response = NetWorkCenter.post(postUrl, json, files);
        } finally {
            readLock.unlock();
        }

        if (null == response || ResultType.ACCESS_TOKEN_TIMEOUT.getCode().toString().equals(response.getErrcode())) {
            refreshToken();
            readLock.lock();
            try {
                LOG.debug("接口调用重试....");
                TimeUnit.SECONDS.sleep(1);
                postUrl = url.replace("#", config.getAccessToken());
                response = NetWorkCenter.post(postUrl, json, files);
            } catch (InterruptedException e) {
                LOG.error("线程休眠异常", e);
            } catch (Exception e) {
                LOG.error("请求出现异常", e);
            } finally {
                readLock.unlock();
            }
        }

        return response;
    }


    /**
     * 通用get请求
     *
     * @param url 地址，其中token用#代替
     * @return 请求结果
     */
    protected BaseResponse executeGet(String url) {
        BaseResponse response = null;
        BeanUtil.requireNonNull(url, "url is null");
        String getUrl;
        readLock.lock();
        try {
            //需要传token
            getUrl = url.replace("#", config.getAccessToken());
            response = NetWorkCenter.get(getUrl);
        } finally {
            readLock.unlock();
        }

        if (null == response || ResultType.ACCESS_TOKEN_TIMEOUT.getCode().toString().equals(response.getErrcode())) {
            refreshToken();
            readLock.lock();
            try {
                LOG.debug("接口调用重试....");
                TimeUnit.SECONDS.sleep(1);
                getUrl = url.replace("#", config.getAccessToken());
                response = NetWorkCenter.get(getUrl);
            } catch (InterruptedException e) {
                LOG.error("线程休眠异常", e);
            } catch (Exception e) {
                LOG.error("请求出现异常", e);
            } finally {
                readLock.unlock();
            }
        }
        return response;
    }

    /**
     * 通用get请求
     *
     * @param url  地址，其中token用#代替
     * @param json 参数
     * @return 请求结果
     */
    protected BaseResponse executeGet(String url, String json) {
        BaseResponse response = null;
        BeanUtil.requireNonNull(url, "url is null");
        String getUrl;
        readLock.lock();
        try {
            //需要传token
            getUrl = url.replace("#", config.getAccessToken());
            response = NetWorkCenter.get(getUrl);
        } finally {
            readLock.unlock();
        }

        if (null == response || ResultType.ACCESS_TOKEN_TIMEOUT.getCode().toString().equals(response.getErrcode())) {
            refreshToken();
            readLock.lock();
            try {
                LOG.debug("接口调用重试....");
                TimeUnit.SECONDS.sleep(1);
                getUrl = url.replace("#", config.getAccessToken());
                response = NetWorkCenter.get(getUrl);
            } catch (InterruptedException e) {
                LOG.error("线程休眠异常", e);
            } catch (Exception e) {
                LOG.error("请求出现异常", e);
            } finally {
                readLock.unlock();
            }
        }
        return response;
    }

    /**
     * 判断本次请求是否成功
     *
     * @param errCode 错误码
     * @return 是否成功
     */
    protected boolean isSuccess(String errCode) {
        return ResultType.SUCCESS.getCode().toString().equals(errCode);
    }
}
