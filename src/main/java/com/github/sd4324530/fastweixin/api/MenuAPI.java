package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetMenuResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;

/**
 * 菜单相关API
 *
 * @author peiyu
 * @since 1.2
 */
public class MenuAPI extends BaseAPI {
    public MenuAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单对象
     */
    public void createMenu(Menu menu) {
        BeanUtil.requireNonNull(menu, "menu is null");
        String url = BASE_API_URL + "cgi-bin/menu/create?access_token=#";
        executePost(url, menu.toJsonString());
    }

    /**
     * 获取所有菜单
     *
     * @return 菜单列表对象
     */
    public GetMenuResponse getMenu() {
        GetMenuResponse response = null;
        String url = BASE_API_URL + "cgi-bin/menu/get?access_token=#";

        BaseResponse r = executeGet(url);
        if (null == r.getErrcode() || "".equals(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetMenuResponse.class);
        }
        return response;
    }

    /**
     * 删除所有菜单
     */
    public void deleteMenu() {
        String url = BASE_API_URL + "cgi-bin/menu/delete?access_token=#";
        executeGet(url);
    }
}
