package com.github.sd4324530.fastweixin.api.config;


import com.github.sd4324530.fastweixin.handle.ApiConfigChangeHandle;

/**
 * API配置类，项目中请保证其为单例
 * 实现观察者模式，用于监控token变化
 * 默认实现，单机版可直接使用
 * @author peiyu
 * @since 1.2
 */
public  class ApiConfig  implements ApiConfigInterface {
    
	//ApiConfig是Refresher的wrapper
	private Refresher proxy; 
    /**
     * 构造方法一，实现同时获取access_token。不启用jsApi
     *
     * @param appid  公众号appid
     * @param secret 公众号secret
     */
    public ApiConfig(String appid, String secret) {
        proxy = new Refresher(appid, secret, false);
        proxy.tryRefresh();
    }

    /**
     * 构造方法二，实现同时获取access_token，启用jsApi
     *
     * @param appid       公众号appid
     * @param secret      公众号secret
     * @param enableJsApi 是否启动js api
     */
    public ApiConfig(String appid, String secret, boolean enableJsApi) {  
       proxy = new Refresher(appid, secret, enableJsApi);
       proxy.tryRefresh();
    }

	@Override
	public String getJsApiTicket() {
		return proxy.getJsApiTicket();
	}

	@Override
	public String getAccessToken() {
		return proxy.getAccessToken();
	}

	@Override
	public String getAppid() {
		return proxy.getAppid();
	}

	@Override
	public String getSecret() {
		return proxy.getSecret();
	}



    /**
     * 添加配置变化监听器
     *
     * @param handle 监听器
     */
    public void addHandle(final ApiConfigChangeHandle handle) {
    		proxy.addObserver(handle);
    }

    /**
     * 移除配置变化监听器
     *
     * @param handle 监听器
     */
    public void removeHandle(final ApiConfigChangeHandle handle) {
    		proxy.deleteObserver(handle);
    }

    /**
     * 移除所有配置变化监听器
     */
    public void removeAllHandle() {
    		proxy.deleteObservers();
    }

	@Override
	public TokenInfo getTokenInfo() {
		return proxy.getTokenInfo();
	}

	@Override
	public void setTokenInfo(TokenInfo tokenInfo) {
		proxy.setTokenInfo(tokenInfo);
	}
}
