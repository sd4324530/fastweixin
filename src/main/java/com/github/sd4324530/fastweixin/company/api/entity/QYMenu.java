package com.github.sd4324530.fastweixin.company.api.entity;

import com.github.sd4324530.fastweixin.api.entity.BaseModel;
import com.github.sd4324530.fastweixin.exception.WeixinException;

import java.util.ArrayList;
import java.util.List;

/**
 *  微信完整菜单
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYMenu extends BaseModel {

    private List<QYMenuButton> button;// 一级菜单。总共3个

    public List<QYMenuButton> getButton() {
        return button;
    }

    public void setButton(List<QYMenuButton> button) {
        if(button.size() > 3){
            throw new WeixinException("一级菜单最多3个");
        }
        this.button = button;
    }

    public void addButton(QYMenuButton singleButton){
        if(button == null){
            button = new ArrayList<QYMenuButton>(3);
        }
        if(button.size() == 3){
            throw new WeixinException("一级菜单最多3个");
        }

        this.button.add(singleButton);
    }
}
