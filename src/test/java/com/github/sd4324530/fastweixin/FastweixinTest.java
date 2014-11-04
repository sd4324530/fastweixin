package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import org.junit.Test;

/**
 * @author peiyu
 */
public class FastweixinTest {

    @Test
    public void test() {

        ApiConfig config = new ApiConfig();
        config.setAppid("wxa4deabaf24e6caab");
        config.setSecret("34ea880e3b7a98d0db12d75ff016a39b");

        UserAPI userAPI = new UserAPI(config);
        GetUserInfoResponse userInfo = userAPI.getUserInfo("oV1ogt3UrUgvImtmLifSE4qzSzJQ");
        System.out.println(userInfo.toJsonString());
        GetUserInfoResponse userInfo2 = userAPI.getUserInfo("oV1ogt3UrUgvImtmLifSE4qzSzJQ");
        System.out.println(userInfo2.toJsonString());

    }
}
