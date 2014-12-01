package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.api.MediaAPI;
import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.DownloadMediaResponse;
import com.github.sd4324530.fastweixin.api.response.GetUsersResponse;
import com.github.sd4324530.fastweixin.api.response.UploadMediaResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author peiyu
 */
public class FastweixinTest {

    private static final Logger LOG = LoggerFactory.getLogger(FastweixinTest.class);

    @Test
    public void test() {
        String appid = "wxa4deabaf24e6caab";
        String secret = "34ea880e3b7a98d0db12d75ff016a39b";
        ApiConfig config = new ApiConfig(appid, secret);
//        createMenu(config);
//        getUserList(config);
//        uploadMedia(config);
        downloadMedia(config);
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
        main1.setSubButton(list);

        List<MenuButton> mainList = new ArrayList<MenuButton>();
        mainList.add(main1);
        //将主菜单加入请求对象
        request.setButton(mainList);
        LOG.debug(request.toJsonString());
        //创建菜单
        ResultType resultType = menuAPI.createMenu(request);
        LOG.debug(resultType.toString());
    }

    /**
     * 获取关注者列表
     * @param config API配置
     */
    public void getUserList(ApiConfig config) {
        UserAPI userAPI = new UserAPI(config);
        GetUsersResponse users = userAPI.getUsers(null);
        LOG.debug("user count:{}", users.getCount());
        LOG.debug("user total:{}", users.getTotal());
        String[] openids = users.getData().getOpenid();
        for(String id : openids) {
            LOG.debug("id:{}", id);
        }
    }

    public void uploadMedia(ApiConfig config) {
        MediaAPI mediaAPI = new MediaAPI(config);
        UploadMediaResponse response = mediaAPI.uploadMedia(MediaType.IMAGE, new File("E:/123.jpg"));
        LOG.debug(response.toJsonString());
    }

    public void downloadMedia(ApiConfig config) {
        MediaAPI mediaAPI = new MediaAPI(config);
        DownloadMediaResponse response = mediaAPI.downloadMedia("Kw0k6yeKxLaebweRwAUS2x08bcOx2nHMWAXO4s1lMpN_t5Fcsm-svrxe_EfGAgwo");
        LOG.debug("error:{}", response.getErrcode());
        try {
            response.writeTo(new FileOutputStream(new File("E:/222.jpg")));
        } catch (FileNotFoundException e) {
            LOG.error("异常", e);
        } catch (IOException e) {
            LOG.error("异常", e);
        }
    }
}
