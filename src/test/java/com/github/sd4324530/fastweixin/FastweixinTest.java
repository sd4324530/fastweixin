package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.GetUsersResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peiyu
 */
public class FastweixinTest {

    @Test
    public void test() {
        String appid = "wxa4deabaf24e6caab";
        String secret = "34ea880e3b7a98d0db12d75ff016a39b";
        ApiConfig config = new ApiConfig(appid, secret);
//        createMenu(config);
        getUserList(config);
    }

    /**
     * 创建菜单
     * @param config API配置
     */
    private void createMenu(ApiConfig config) {
        MenuAPI menuAPI = new MenuAPI(config);

        //先删除之前的菜单
        menuAPI.deleteMenu();
        Menu request = new Menu();
        //准备一级主菜单
        MenuButton main1 = new MenuButton();
        main1.setType(MenuType.CLICK);
        main1.setKey("main1");
        main1.setName("mainName1");
        //准备子菜单
        MenuButton sub1 = new MenuButton();
        sub1.setKey("sub1");
        sub1.setName("name1");
        sub1.setType(MenuType.VIEW);
        sub1.setUrl("http://www.baidu.com");

        List<MenuButton> list = new ArrayList<MenuButton>();
        list.add(sub1);
        //将子菜单放入主菜单里
        main1.setSub_button(list);

        List<MenuButton> mainList = new ArrayList<MenuButton>();
        mainList.add(main1);
        //将主菜单加入请求对象
        request.setButton(mainList);
        //创建菜单
        ResultType resultType = menuAPI.createMenu(request);
        System.out.println(resultType.toString());
    }

    /**
     * 获取关注者列表
     * @param config API配置
     */
    public void getUserList(ApiConfig config) {
        UserAPI userAPI = new UserAPI(config);
        GetUsersResponse users = userAPI.getUsers(null);
        System.out.println(users.getCount());
        System.out.println(users.getTotal());
        String[] openids = users.getData().getOpenid();
        for(String id : openids) {
            System.out.println(id);
        }
    }
}
