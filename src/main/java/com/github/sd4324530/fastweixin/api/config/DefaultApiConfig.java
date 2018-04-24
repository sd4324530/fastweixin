package com.github.sd4324530.fastweixin.api.config;

import com.github.sd4324530.fastweixin.exception.WeixinException;
import com.github.sd4324530.fastweixin.handle.ApiConfigChangeHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * API配置默认实现
 * 实现观察者模式，用于监控token变化
 *
 * @author peiyu
 * @since 1.2
 */
public class DefaultApiConfig extends Observable implements ApiConfig, Serializable {

    /* 默认的有效时间：7100秒 */
    public static final long DEFAULT_EXPIRE_TIME = 7100000L;

    protected static final Logger        LOG             = LoggerFactory.getLogger(DefaultApiConfig.class);

    /**
     * 这里定义token正在刷新的标识，想要达到的目标是当有一个请求来获取token，发现token已经过期（我这里的过期逻辑是比官方提供的早100秒），然后开始刷新token
     * 在刷新的过程里，如果又继续来获取token，会先把旧的token返回，直到刷新结束，之后再来的请求，将获取到新的token
     * 利用AtomicBoolean实现原理：
     * 当请求来的时候，检查token是否已经过期（7100秒）以及标识是否已经是true（表示已经在刷新了，还没刷新完），过期则将此标识设为true，并开始刷新token
     * 在刷新结束前再次进来的请求，由于标识一直是true，而会直接拿到旧的token，由于我们的过期逻辑比官方的早100秒，所以旧的还可以继续用
     * 无论刷新token正在结束还是出现异常，都在最后将标识改回false，表示刷新工作已经结束
     */
    protected final        AtomicBoolean tokenRefreshing = new AtomicBoolean(false);
    protected final        AtomicBoolean jsRefreshing    = new AtomicBoolean(false);

    protected final String  appid;
    protected final String  secret;
    protected       String  accessToken;
    protected       String  jsApiTicket;
    protected       boolean enableJsApi;
    protected       long    jsTokenStartTime;
    protected       long    weixinTokenStartTime;

    /* access_token的有效时间 */
    protected       long    tokenExpireTime = DEFAULT_EXPIRE_TIME;
    protected       long    jsExpireTime = DEFAULT_EXPIRE_TIME;

    /* Token服务 */
    protected TokenService tokenService = this.createTokenService();

    /**
     * 构造方法一，实现同时获取access_token。不启用jsApi
     *
     * @param appid  公众号appid
     * @param secret 公众号secret
     */
    public DefaultApiConfig(String appid, String secret) {
        this(appid, secret, false);
    }

    /**
     * 构造方法二，实现同时获取access_token，启用jsApi
     *
     * @param appid       公众号appid
     * @param secret      公众号secret
     * @param enableJsApi 是否启动js api
     */
    public DefaultApiConfig(String appid, String secret, boolean enableJsApi) {
        this.appid = appid;
        this.secret = secret;
        this.enableJsApi = enableJsApi;
        long now = System.currentTimeMillis();
        initToken(now);
        if (enableJsApi) initJSToken(now);
    }

    @Override
    public String getAppid() {
        return appid;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public String getAccessToken() {
        long now = System.currentTimeMillis();
        long time = now - this.weixinTokenStartTime;
        try {
            /*
             * 判断优先顺序：
             * 1.官方给出的超时时间是7200秒，这里用7100秒来做，防止出现已经过期的情况
             * 2.刷新标识判断，如果已经在刷新了，则也直接跳过，避免多次重复刷新，如果没有在刷新，则开始刷新
             */

            if (time > tokenExpireTime && this.tokenRefreshing.compareAndSet(false, true)) {
                LOG.debug("准备刷新token.............");
                initToken(now);
            }
        } catch (Exception e) {
            LOG.warn("刷新Token出错.", e);
            //刷新工作出现有异常，将标识设置回false
            this.tokenRefreshing.set(false);
        }
        return accessToken;
    }

    @Override
    public String getJsApiTicket() {
        if (enableJsApi) {
            long now = System.currentTimeMillis();
            try {
                //官方给出的超时时间是7200秒，这里用7100秒来做，防止出现已经过期的情况
                if (now - this.jsTokenStartTime > jsExpireTime && this.jsRefreshing.compareAndSet(false, true)) {
                    getAccessToken();
                    initJSToken(now);
                }
            } catch (Exception e) {
                LOG.warn("刷新jsTicket出错.", e);
                //刷新工作出现有异常，将标识设置回false
                this.jsRefreshing.set(false);
            }
        } else {
            jsApiTicket = null;
        }
        return jsApiTicket;
    }

    /**
     * 添加配置变化监听器
     *
     * @param handle 监听器
     */
    @Override
    public void addHandle(final ApiConfigChangeHandle handle) {
        super.addObserver(handle);
    }

    /**
     * 移除配置变化监听器
     *
     * @param handle 监听器
     */
    @Override
    public void removeHandle(final ApiConfigChangeHandle handle) {
        super.deleteObserver(handle);
    }

    /**
     * 移除所有配置变化监听器
     */
    @Override
    public void removeAllHandle() {
        super.deleteObservers();
    }

    /**
     * 初始化微信配置，即第一次获取access_token
     *
     * @param refreshTime 刷新时间
     */
    protected void initToken(final long refreshTime) {
        LOG.debug("开始初始化access_token........");
        //记住原本的时间，用于出错回滚
        final long oldTime = this.weixinTokenStartTime;
        this.weixinTokenStartTime = refreshTime;

        // 获取accessToken
        tokenService.getAccessToken(this, new TokenResponseCallback() {
            @Override
            public void onSuccess(TokenResponse response) {
                LOG.debug("获取access_token:{}", response.getToken());
                accessToken = response.getToken();
                //设置通知点
                setChanged();
                notifyObservers(new ConfigChangeNotice(appid, ChangeType.ACCESS_TOKEN, accessToken));
            }

            @Override
            public void onFailure(TokenResponse response) {
                //刷新时间回滚
                weixinTokenStartTime = oldTime;
                throw new WeixinException("微信公众号token获取出错，错误信息:" + response.getErrcode() + "," + response.getErrmsg());
            }
        });

        //刷新工作做完，将标识设置回false
        this.tokenRefreshing.set(false);
    }

    /**
     * 初始化微信JS-SDK，获取JS-SDK token
     *
     * @param refreshTime 刷新时间
     */
    protected void initJSToken(final long refreshTime) {
        LOG.debug("初始化 jsapi_ticket........");
        //记住原本的时间，用于出错回滚
        final long oldTime = this.jsTokenStartTime;
        this.jsTokenStartTime = refreshTime;

        // 获取jsApiTicket
        tokenService.getJsApiTicket(this, new TokenResponseCallback() {
            @Override
            public void onSuccess(TokenResponse response) {
                LOG.debug("获取jsapi_ticket:{}", response.getToken());
                jsApiTicket = response.getToken();
                //设置通知点
                setChanged();
                notifyObservers(new ConfigChangeNotice(appid, ChangeType.JS_TOKEN, jsApiTicket));
            }

            @Override
            public void onFailure(TokenResponse response) {
                //刷新时间回滚
                jsTokenStartTime = oldTime;
                throw new WeixinException("微信公众号jsToken获取出错，错误信息:" + response.getErrcode() + "," + response.getErrmsg());
            }
        });

        //刷新工作做完，将标识设置回false
        this.jsRefreshing.set(false);
    }

    /**
     * 创建Token服务
     * 创建默认的实现，需要扩展时由子类重写该方法提供其他的实现
     * @return
     */
    protected TokenService createTokenService() {
        return new DefaultTokenService();
    }

}
