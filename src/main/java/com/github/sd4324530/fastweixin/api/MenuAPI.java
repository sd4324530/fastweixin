package com.github.sd4324530.fastweixin.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetMenuResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.CollectionUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 菜单相关API
 *
 * @author peiyu
 * @since 1.2
 */
public class MenuAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(MenuAPI.class);

    public MenuAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单对象
     * @return 调用结果
     */
    public ResultType createMenu(Menu menu) {
        BeanUtil.requireNonNull(menu, "menu is null");
        LOG.debug("创建菜单.....");
        String url = BASE_API_URL + "cgi-bin/menu/create?access_token=#";
        BaseResponse response = executePost(url, menu.toJsonString());
        return ResultType.get(response.getErrcode());
    }

    /**
     * 获取所有菜单
     *
     * @return 菜单列表对象
     */
    public GetMenuResponse getMenu() {
        GetMenuResponse response = null;
        LOG.debug("获取菜单信息.....");
        String url = BASE_API_URL + "cgi-bin/menu/get?access_token=#";

        BaseResponse r = executeGet(url);
        if (isSuccess(r.getErrcode())) {
            JSONObject jsonObject = JSONUtil.getJSONFromString(r.getErrmsg());
            //通过jsonpath不断修改type的值，才能正常解析- -
            List buttonList = (List) JSONPath.eval(jsonObject, "$.menu.button");
            if (CollectionUtil.isNotEmpty(buttonList)) {
                for (Object button : buttonList) {
                    List subList = (List) JSONPath.eval(button, "$.sub_button");
                    if (CollectionUtil.isNotEmpty(subList)) {
                        for (Object sub : subList) {
                            Object type = JSONPath.eval(sub, "$.type");
                            JSONPath.set(sub, "$.type", type.toString().toUpperCase());
                        }
                    }else{
                        Object type = JSONPath.eval(button, "$.type");
                        JSONPath.set(button, "$.type", type.toString().toUpperCase());
                    }
                }
            }
            response = JSONUtil.toBean(jsonObject.toJSONString(), GetMenuResponse.class);
        } else {
            response = JSONUtil.toBean(r.toJsonString(), GetMenuResponse.class);
        }
        return response;
    }

    /**
     * 删除所有菜单
     *
     * @return 调用结果
     */
    public ResultType deleteMenu() {
        LOG.debug("删除菜单.....");
        String url = BASE_API_URL + "cgi-bin/menu/delete?access_token=#";
        BaseResponse response = executeGet(url);
        return ResultType.get(response.getErrcode());
    }
}
