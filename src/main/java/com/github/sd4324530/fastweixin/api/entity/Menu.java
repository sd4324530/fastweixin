package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.exception.WeixinException;

import java.util.List;

/**
 * 菜单对象，包含所有菜单按钮
 *
 * @author peiyu
 */
public class Menu extends BaseModel {

    /**
     * 一级菜单列表，最多3个
     */
    private List<MenuButton> button;

    /**
     * 菜单匹配规则
     *
     * @since 1.3.7
     */
    private Matchrule matchrule;

    /**
     * 菜单ID，查询时会返回，删除个性化菜单时会用到
     *
     * @since 1.3.7
     */
    @JSONField(name = "menuid")
    private String menuId;

    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        if (null == button || button.size() > 3) {
            throw new WeixinException("主菜单最多3个");
        }
        this.button = button;
    }

    public Matchrule getMatchrule() {
        return matchrule;
    }

    public void setMatchrule(Matchrule matchrule) {
        this.matchrule = matchrule;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
