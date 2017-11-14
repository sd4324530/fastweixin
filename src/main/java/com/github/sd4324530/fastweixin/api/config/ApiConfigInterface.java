package com.github.sd4324530.fastweixin.api.config;

public interface ApiConfigInterface {
	public String getAppid();
	public String getSecret();
	public String getJsApiTicket();
	public String getAccessToken();
	
	public TokenInfo getTokenInfo();
	public void setTokenInfo(TokenInfo tokenInfo);
}
