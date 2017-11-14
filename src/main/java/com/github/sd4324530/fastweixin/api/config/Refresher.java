package com.github.sd4324530.fastweixin.api.config;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.api.response.GetJsApiTicketResponse;
import com.github.sd4324530.fastweixin.api.response.GetTokenResponse;
import com.github.sd4324530.fastweixin.exception.WeixinException;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import com.github.sd4324530.fastweixin.util.StrUtil;

public class Refresher  extends AbstractApiConfig {
	
	private static final Logger LOG  = LoggerFactory.getLogger(Refresher.class);
	
	public Refresher(String appid, String secret) {
		super(appid, secret);
	}
	public Refresher(String appid, String secret, boolean enableJsApi) {
		super(appid, secret, enableJsApi);
	}
	
	public Refresher(String appid, String secret, boolean enableJsApi, long refreshIntervalTime) {
		super(appid, secret, enableJsApi, refreshIntervalTime);
	}

    /**
     * 初始化微信配置，即第一次获取access_token
     *
     * @param refreshTime 刷新时间
     */
	@Override
	
    public void initToken(final long refreshTime) {
        LOG.debug("开始初始化access_token........");
        //记住原本的时间，用于出错回滚
        final long oldTime = this.tokenInfo.weixinTokenStartTime;
        this.tokenInfo.weixinTokenStartTime = refreshTime;
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + this.appid + "&secret=" + this.secret;
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (HttpStatus.SC_OK == resultCode) {
                    GetTokenResponse response = JSONUtil.toBean(resultJson, GetTokenResponse.class);
                    LOG.debug("获取access_token:{}", response.getAccessToken());
                    if (null == response.getAccessToken()) {
                        //刷新时间回滚
                    		tokenInfo.weixinTokenStartTime = oldTime;
                        throw new WeixinException("微信公众号token获取出错，错误信息:" + response.getErrcode() + "," + response.getErrmsg());
                    }
                    tokenInfo.accessToken = response.getAccessToken();
                    //设置通知点
                    setChanged();
                    notifyObservers(new ConfigChangeNotice(appid, ChangeType.ACCESS_TOKEN, tokenInfo.accessToken));
                }
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
	@Override
    public void initJSToken(final long refreshTime) {
        LOG.debug("初始化 jsapi_ticket........");
        //记住原本的时间，用于出错回滚
        final long oldTime = this.tokenInfo.jsTokenStartTime;
        this.tokenInfo.jsTokenStartTime = refreshTime;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + tokenInfo.accessToken + "&type=jsapi";
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (HttpStatus.SC_OK == resultCode) {
                    GetJsApiTicketResponse response = JSONUtil.toBean(resultJson, GetJsApiTicketResponse.class);
                    LOG.debug("获取jsapi_ticket:{}", response.getTicket());
                    if (StrUtil.isBlank(response.getTicket())) {
                        //刷新时间回滚
                    	tokenInfo.jsTokenStartTime = oldTime;
                        throw new WeixinException("微信公众号jsToken获取出错，错误信息:" + response.getErrcode() + "," + response.getErrmsg());
                    }
                    tokenInfo.jsApiTicket = response.getTicket();
                    //设置通知点
                    setChanged();
                    notifyObservers(new ConfigChangeNotice(appid, ChangeType.JS_TOKEN, tokenInfo.jsApiTicket));
                }
            }
        });
        //刷新工作做完，将标识设置回false
        this.jsRefreshing.set(false);
    }
}
