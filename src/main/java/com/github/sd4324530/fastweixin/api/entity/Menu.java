package com.github.sd4324530.fastweixin.api.entity;

import com.github.sd4324530.fastweixin.util.JSONUtil;

import java.util.List;

/**
 * @author peiyu
 */
public class Menu implements Model {

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
