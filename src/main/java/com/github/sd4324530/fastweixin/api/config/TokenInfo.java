package com.github.sd4324530.fastweixin.api.config;

import java.io.Serializable;
/**
 * 需要动态更新的内容
 * */
public class TokenInfo implements Serializable{
	private static final long serialVersionUID = 4555519391524965850L;
	//similar to Struct in C language
	public  String  accessToken;
	public  long    weixinTokenStartTime=0L;
	public  String  jsApiTicket;
	public  long    jsTokenStartTime=0L;
	
	public TokenInfo() {
		super();
	}
	public TokenInfo(String accessToken, long weixinTokenStartTime, String jsApiTicket, long jsTokenStartTime) {
		super();
		this.accessToken = accessToken;
		this.weixinTokenStartTime = weixinTokenStartTime;
		this.jsApiTicket = jsApiTicket;
		this.jsTokenStartTime = jsTokenStartTime;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getWeixinTokenStartTime() {
		return weixinTokenStartTime;
	}
	public void setWeixinTokenStartTime(long weixinTokenStartTime) {
		this.weixinTokenStartTime = weixinTokenStartTime;
	}
	public String getJsApiTicket() {
		return jsApiTicket;
	}
	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}
	public long getJsTokenStartTime() {
		return jsTokenStartTime;
	}
	public void setJsTokenStartTime(long jsTokenStartTime) {
		this.jsTokenStartTime = jsTokenStartTime;
	}	
	
	
}
