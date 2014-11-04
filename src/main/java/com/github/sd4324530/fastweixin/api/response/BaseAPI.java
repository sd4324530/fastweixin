package com.github.sd4324530.fastweixin.api.response;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author peiyu
 */
public abstract class BaseAPI {
    protected static final String BASE_API_URL = "https://api.weixin.qq.com/";

    protected static final String SYS_BUSY = "-1";

    protected static final String SUCCESS = "0";

    protected static final String TOKEN_ERROR = "40001";

    protected static final String TOKEN_TYPE_ERROR = "40002";

    protected static final String TOKEN_TIMEOUT = "42001";

    protected final ApiConfig config;

    //用于刷新token时锁住config，防止多次刷新
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public BaseAPI(ApiConfig config) {
        this.config = config;
    }

    protected boolean refreshToken() {
        writeLock.lock();
        final boolean[] result;
        try {
            config.refreshing = true;
            result = new boolean[]{false};
            String url = BASE_API_URL + "cgi-bin/token?grant_type=client_credential&appid=" + this.config.getAppid() + "&secret=" + this.config.getSecret();
            NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
                @Override
                public void onResponse(int resultCode, String resultJson) {
                    if (200 == resultCode) {
                        GetTokenResponse response = JSONUtil.toBean(resultJson, GetTokenResponse.class);
                        BaseAPI.this.config.setAccess_token(response.getAccess_token());
                        result[0] = true;
                    }
                }
            });
        } finally {
            config.refreshing = false;
            writeLock.unlock();
        }
        return result[0];
    }

    protected BaseResponse executePost(String url, String json) {
        BaseResponse response = null;
        BeanUtil.requireNonNull(url, "url is null");

        if(null == config.getAccess_token()) {
            refreshToken();
        }

        System.out.println(config.getAccess_token());

        readLock.lock();
        try {
           //需要传token
           url = url.replace("#",config.getAccess_token());
            response = NetWorkCenter.post(url, json);
        } finally {
            readLock.unlock();
        }

        if(null == response || TOKEN_TIMEOUT.equals(response.getErrcode())) {
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


    protected BaseResponse executeGet(String url) {
        BaseResponse response = null;
        BeanUtil.requireNonNull(url, "url is null");

        if(null == config.getAccess_token()) {
            refreshToken();
        }
        System.out.println(config.getAccess_token());

        readLock.lock();
        try {
            //需要传token
            url = url.replace("#",config.getAccess_token());
            response = NetWorkCenter.get(url);
        } finally {
            readLock.unlock();
        }

        if(null == response || TOKEN_TIMEOUT.equals(response.getErrcode())) {
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
