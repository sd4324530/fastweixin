package com.github.sd4324530.fastweixin.api.request;

import com.github.sd4324530.fastweixin.api.entity.MenuButton;

import java.util.List;

/**
 * @author peiyu
 */
public class MenuRequest extends BaseRequest {

    private List<MenuButton> button;

    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        this.button = button;
    }
}
