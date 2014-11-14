package com.github.sd4324530.fastweixin.api.entity;

import com.github.sd4324530.fastweixin.util.JSONUtil;

import java.util.List;

/**
 * 菜单对象，包含所有菜单按钮
 * @author peiyu
 */
public class Menu implements Model {

    /**
     * 一级菜单列表，最多3个
     */
    private List<MenuButton> button;

    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        this.button = button;
    }

    @Override
    public String toJsonString() {
        return JSONUtil.toJson(this);
    }
}
