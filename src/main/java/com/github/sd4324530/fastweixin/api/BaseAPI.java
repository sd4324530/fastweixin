package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetTokenResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * API基类，提供一些通用方法
 * 包含自动刷新token、通用get post请求等
 * @author peiyu
 * @since 1.2
 */
public abstract class BaseAPI {
    protected static final String BASE_API_URL = "https://api.weixin.qq.com/";

    protected final ApiConfig config;

    //用于刷新token时锁住config，防止多次刷新
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public BaseAPI(ApiConfig config) {
        this.config = config;
    }

    /**
     * 刷新token
     */
    protected void refreshToken() {
        writeLock.lock();
        try {
            config.refreshing = true;
            String url = BASE_API_URL + "cgi-bin/token?grant_type=client_credential&appid=" + this.config.getAppid() + "&secret=" + this.config.getSecret();
            NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
                @Override
                public void onResponse(int resultCode, String resultJson) {
                    if (200 == resultCode) {
                        GetTokenResponse response = JSONUtil.toBean(resultJson, GetTokenResponse.class);
                        BaseAPI.this.config.setAccess_token(response.getAccess_token());
                    }
                }
            });
        } finally {
            config.refreshing = false;
            writeLock.unlock();
        }
    }

    /**
     * 通用post请求
     * @param url 地址，其中token用#代替
     * @param json 参数，json格式
     * @return 请求结果
     */
    protected BaseResponse executePost(String url, String json) {
        BaseResponse response = null;
        BeanUtil.requireNonNull(url, "url is null");

        readLock.lock();
        try {
           //需要传token
           url = url.replace("#",config.getAccess_token());
            response = NetWorkCenter.post(url, json);
        } finally {
            readLock.unlock();
        }

        if(null == response || ResultType.ACCESS_TOKEN_TIMEOUT.toString().equals(response.getErrcode())) {
            if(!config.refreshing) {
                refreshToken();
            }

            readLock.lock();
            try {
                response = NetWorkCenter.post(url, json);
            } finally {
                readLock.unlock();
            }
        }

        return response;
    }

    /**
     * 通用post请求
     * @param url 地址，其中token用#代替
     * @return 请求结果
     */
    protected BaseResponse executeGet(String url) {
        BaseResponse response = null;
        BeanUtil.requireNonNull(url, "url is null");

        readLock.lock();
        try {
            //需要传token
            url = url.replace("#",config.getAccess_token());
            response = NetWorkCenter.get(url);
        } finally {
            readLock.unlock();
        }

        if(null == response || ResultType.ACCESS_TOKEN_TIMEOUT.toString().equals(response.getErrcode())) {
            if (!config.refreshing) {
                refreshToken();
            }
            readLock.lock();
            try {
                response = NetWorkCenter.get(url);
            } finally {
                readLock.unlock();
            }
        }
        return response;
    }
}
