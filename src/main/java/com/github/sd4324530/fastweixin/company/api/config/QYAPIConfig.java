package com.github.sd4324530.fastweixin.company.api.config;

import com.github.sd4324530.fastweixin.api.config.ChangeType;
import com.github.sd4324530.fastweixin.api.config.ConfigChangeNotice;
import com.github.sd4324530.fastweixin.api.response.GetJsApiTicketResponse;
import com.github.sd4324530.fastweixin.api.response.GetTokenResponse;
import com.github.sd4324530.fastweixin.exception.WeixinException;
import com.github.sd4324530.fastweixin.handle.ApiConfigChangeHandle;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public final class QYAPIConfig extends Observable implements Serializable{

    private static final Logger LOG = LoggerFactory.getLogger(QYAPIConfig.class);

    private final Integer CACHE_TIME = 7100000;
    private final AtomicBoolean tokenRefreshing = new AtomicBoolean(false);
    private final AtomicBoolean jsRefreshing = new AtomicBoolean(false);

    private final String corpid;
    private final String corpsecret;
    private String accessToken;
    private String jsApiTicket;
    private boolean enableJsApi;
    private long jsTokenStartTime;
    private long weixinTokenStartTime;

    /**
     * 构造方法一，实现同时获取AccessToken。不启用jsApi
     * @param corpID
     * @param corpSecret
     */
    public QYAPIConfig(String corpID, String corpSecret){
        this(corpID, corpSecret, false);
    }

    /**
     * 构造方法二，实现同时获取AccessToken，启用jsApi
     * @param corpid
     * @param corpsecret
     * @param enableJsApi
     */
    public QYAPIConfig(String corpid, String corpsecret, boolean enableJsApi) {
        this.corpid = corpid;
        this.corpsecret = corpsecret;
        this.enableJsApi = enableJsApi;
    }

    public String getCorpid() {
        return corpid;
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    public String getAccessToken(){
        long now = System.currentTimeMillis();
        long time = now - this.weixinTokenStartTime;
        try{
            if(time > CACHE_TIME && tokenRefreshing.compareAndSet(false, true)){
                LOG.debug("准备刷新tokean.........");
                initToken(now);
            }
        }finally {
            tokenRefreshing.set(false);
        }
        return accessToken;
    }

    public String getJsApiTicket(){
        if(enableJsApi){
            long now = System.currentTimeMillis();
            long time = now - this.jsTokenStartTime;
            try{
                if(now - this.jsTokenStartTime > CACHE_TIME && jsRefreshing.compareAndSet(false, true)){
                    LOG.debug("准备刷新JSTokean..........");
                    getAccessToken();
                    initJSToken(now);
                    jsRefreshing.set(false);
                }
            }finally {
                jsRefreshing.set(false);
            }
        }else{
            jsApiTicket = null;
        }
        return jsApiTicket;
    }

    public boolean isEnableJsApi() {
        return enableJsApi;
    }

    public void setEnableJsApi(boolean enableJsApi) {
        this.enableJsApi = enableJsApi;
        if (!enableJsApi)
            this.jsApiTicket = null;
    }

    public void addHandle(final ApiConfigChangeHandle handle){
        super.addObserver(handle);
    }

    public void removeHandle(final ApiConfigChangeHandle handle){
        super.deleteObserver(handle);
    }

    public void removeAllHandle(){
        super.deleteObservers();
    }

    private void initToken(final long refreshTime){
        LOG.debug("开始初始化access_token..........");
        // 记住原本的事件，用于出错回滚
        final long oldTime = this.weixinTokenStartTime;
        this.weixinTokenStartTime = refreshTime;
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback(){
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if(HttpStatus.SC_OK == resultCode){
                    GetTokenResponse response = JSONUtil.toBean(resultJson, GetTokenResponse.class);
                    LOG.debug("获取access_token:{}", response.getAccessToken());
                    if(null == response.getAccessToken()){
                        // 刷新时间回滚
                        weixinTokenStartTime = oldTime;
                        throw new WeixinException("微信企业号token获取出错，错误信息:" + response.getErrcode() + "," + response.getErrmsg());
                    }
                    accessToken = response.getAccessToken();
                    // 设置通知点
                    setChanged();
                    notifyObservers(new ConfigChangeNotice(corpid, ChangeType.ACCESS_TOKEN, accessToken));
                }
            }
        });
    }

    private void initJSToken(final long refreshTime){
        LOG.debug("初始化 jsapi_ticket.........");
        // 记住原本的事件，用于出错回滚
        final long oldTime = this.jsTokenStartTime;
        this.jsTokenStartTime = refreshTime;
        String url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=" + accessToken;
        NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback(){

            @Override
            public void onResponse(int resultCode, String resultJson) {
                if(HttpStatus.SC_OK == resultCode){
                    GetJsApiTicketResponse response = JSONUtil.toBean(resultJson, GetJsApiTicketResponse.class);
                    LOG.debug("获取jsapi_ticket:{}", response.getTicket());
                    if(StrUtil.isBlank(response.getTicket())){
                        //刷新时间回滚
                        jsTokenStartTime = oldTime;
                        throw new WeixinException("微信企业号jsToken获取出错，错误信息:" + response.getErrcode() + "," + response.getErrmsg());
                    }
                    jsApiTicket = response.getTicket();
                    //设置通知点
                    setChanged();
                    notifyObservers(new ConfigChangeNotice(corpid, ChangeType.JS_TOKEN, jsApiTicket));
                }
            }
        });
    }
}
