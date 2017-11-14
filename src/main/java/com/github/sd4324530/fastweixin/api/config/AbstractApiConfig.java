package com.github.sd4324530.fastweixin.api.config;


import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.handle.ApiConfigChangeHandle;


/**
 * 为了较方便地实现多节点应用支持，重构ApiConfig， 将原ApiConfig类被拆成多个类：一个接口ApiConfigInterface、一个继承了原来大部分逻辑的抽象类AbstractApiConfig，一个具体的刷新类Refresher和TokenInfo
 * 其中ApiConfigInterface定义了提供token和jsTicket的接口，而AbstractApiConfig封装了原ApiConfig的绝大部分处理逻辑，
 * Refresher负责具体的请求刷新，TokenInfo设计为可序列化的POJO类，封装了token和jsTicket以及对应的刷新时间；
 * 
 * 单机版仍继续使用ApiConfig，因新的ApiConfig仍和原ApiConfig功能相同，因其实现了接口ApiConfigInterface，具体功能借助Refresher来实现，因此可以说新
 * ApiConfig是Refresher的wrapper，或者Refresher是ApiConfig的代理实现类。
 * 注意：创建子类化实例时，未进行token刷新，创建完实例后须明确调用tryRefresh进行刷新获取，但创建新ApiConfig实例时，则已经调用了tryRefresh进行了刷新
 * 
 * 
 * @author rwsbillyang
 * @since 1.3.16?
 * */
public abstract class AbstractApiConfig  extends Observable implements ApiConfigInterface{
	
	private static final Logger        LOG             = LoggerFactory.getLogger(ApiConfig.class);
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
    protected       boolean enableJsApi;
    protected long refreshIntervalTime = 7100000;
	protected TokenInfo tokenInfo;   //真正需要动态更新且可能须序列化的内容
    
 
	/**
     * 构造方法一，实现同时获取access_token。不启用jsApi
     * 注意：创建子类化实例时，未进行token刷新，创建完实例后须明确调用tryRefresh进行刷新获取
     * @param appid  公众号appid
     * @param secret 公众号secret
     */
    public AbstractApiConfig(String appid, String secret) {
        this(appid, secret, false);
    }

    /**
     * 构造方法二，实现同时获取access_token，启用jsApi
     * 注意：创建子类化实例时，未进行token刷新，创建完实例后须明确调用tryRefresh进行刷新获取
     * @param appid       公众号appid
     * @param secret      公众号secret
     * @param enableJsApi 是否启动js api
     */
    public AbstractApiConfig(String appid, String secret, boolean enableJsApi) {
    		this.appid = appid;
        this.secret = secret;
        this.enableJsApi = enableJsApi;
        tokenInfo = new TokenInfo();
    }
    /**
     * 构造方法3，实现同时获取access_token，启用jsApi
     * 注意：创建子类化实例时，未进行token刷新，创建完实例后须明确调用tryRefresh进行刷新获取
     * @param appid       公众号appid
     * @param secret      公众号secret
     * @param enableJsApi 是否启动js api
     * @param refreshIntervalTime 单位millisecond毫秒，自定义刷新间隔时间。当大于该值时，将刷新，refreshIntervalTime必须小于7200000且大于0
     */
    public AbstractApiConfig(String appid, String secret, boolean enableJsApi,long refreshIntervalTime) {
    		this(appid, secret, enableJsApi);
    		if(refreshIntervalTime<7200000&&refreshIntervalTime>0)
    			this.refreshIntervalTime = refreshIntervalTime;
    }
    /**
     * 试图刷新token和jsTicket,先实例化再refresh 
     * */
    public void tryRefresh()
    {
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
    public boolean isEnableJsApi() {
        return enableJsApi;
    }

    public void setEnableJsApi(boolean enableJsApi) {
        this.enableJsApi = enableJsApi;
        if (!enableJsApi)
            this.tokenInfo.jsApiTicket = null;
    }
    
    public long getRefreshIntervalTime() {
		return refreshIntervalTime;
	}

	public void setRefreshIntervalTime(long refreshIntervalTime) {
		this.refreshIntervalTime = refreshIntervalTime;
	}


	public long getWeixinTokenStartTime() {
		return this.tokenInfo.weixinTokenStartTime;
	}
	public long getJsTokenStartTime() {
		return this.tokenInfo.jsTokenStartTime;
	}

	public void updateAccessTokenInfo(String accessToken,long weixinTokenStartTime) {
		this.tokenInfo.accessToken = accessToken;
		this.tokenInfo.weixinTokenStartTime = weixinTokenStartTime;
	}

	public void updateJsApiTicket(String jsApiTicket,long jsTokenStartTime) {
		this.tokenInfo.jsApiTicket = jsApiTicket;
		this.tokenInfo.jsTokenStartTime = jsTokenStartTime;
	}
	
	@Override
	public TokenInfo getTokenInfo() {
		return tokenInfo;
	}
	@Override
	public void setTokenInfo(TokenInfo tokenInfo) {
		this.tokenInfo = tokenInfo;
	}
	
	@Override
    public String getAccessToken() {
        long now = System.currentTimeMillis();
        long time = now - this.tokenInfo.weixinTokenStartTime;
        try {
            /*
             * 判断优先顺序：
             * 1.官方给出的超时时间是7200秒，这里用refreshIntervalTime秒来做，防止出现已经过期的情况
             * 2.刷新标识判断，如果已经在刷新了，则也直接跳过，避免多次重复刷新，如果没有在刷新，则开始刷新
             */

            if (time > refreshIntervalTime && this.tokenRefreshing.compareAndSet(false, true)) {
                LOG.debug("准备刷新token.............");
                initToken(now);
            }
        } catch (Exception e) {
            LOG.warn("刷新Token出错.", e);
            //刷新工作出现有异常，将标识设置回false
            this.tokenRefreshing.set(false);
        }
        return tokenInfo.accessToken;
    }
	@Override
    public String getJsApiTicket() {
        if (enableJsApi) {
            long now = System.currentTimeMillis();
            try {
                //官方给出的超时时间是7200秒，这里用refreshIntervalTime秒来做，防止出现已经过期的情况
                if (now - this.tokenInfo.jsTokenStartTime > refreshIntervalTime && this.jsRefreshing.compareAndSet(false, true)) {
                    getAccessToken();
                    initJSToken(now);
                }
            } catch (Exception e) {
                LOG.warn("刷新jsTicket出错.", e);
                //刷新工作出现有异常，将标识设置回false
                this.jsRefreshing.set(false);
            }
        } else {
        		tokenInfo.jsApiTicket = null;
        }
        return tokenInfo.jsApiTicket;
    }


    /**
     * 添加配置变化监听器
     *
     * @param handle 监听器
     */
    public void addHandle(final ApiConfigChangeHandle handle) {
    		super.addObserver(handle);
    }

    /**
     * 移除配置变化监听器
     *
     * @param handle 监听器
     */
    public void removeHandle(final ApiConfigChangeHandle handle) {
    		super.deleteObserver(handle);
    }

    /**
     * 移除所有配置变化监听器
     */
    public void removeAllHandle() {
    		super.deleteObservers();
    }
    
    public abstract void initToken(final long refreshTime) ;
    public abstract void initJSToken(final long refreshTime) ;
    
    
}
