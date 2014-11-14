package com.github.sd4324530.fastweixin.api.entity;

import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.util.JSONUtil;

import java.util.List;

/**
 * 菜单按钮对象
 * @author peiyu
 */
public class MenuButton implements Model {

    /**
     * 菜单类别
     */
    private MenuType type;

    /**
     * 菜单上显示的文字
     */
    private String name;

    /**
     * 菜单key，当MenuType值为CLICK时用于区别菜单
     */
    private String key;

    /**
     * 菜单跳转的URL，当MenuType值为VIEW时用
     */
    private String url;

    /**
     * 二级菜单列表，每个一级菜单下最多5个
     */
    private List<MenuButton> sub_button;

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<MenuButton> sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toJsonString() {
        return JSONUtil.toJson(this);
    }
}
