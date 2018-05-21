package com.github.sd4324530.fastweixin.company.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.api.entity.BaseModel;
import com.github.sd4324530.fastweixin.company.api.enums.QYMenuType;
import com.github.sd4324530.fastweixin.exception.WeixinException;

import java.util.List;

/**
 *  菜单按钮对象
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYMenuButton extends BaseModel {

    private QYMenuType type;// 菜单类型
    private String name;// 菜单显式的名称
    private String key;// 菜单类型不为view或为空时可用。代表事件名称
    private String url;// 菜单类型为view时可用。代表点击按钮后跳转的页面地址
    @JSONField(name = "sub_button")
    private List<QYMenuButton> subButton;

    public QYMenuType getType() {
        return type;
    }

    public void setType(QYMenuType type) {
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

    public List<QYMenuButton> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<QYMenuButton> subButton) {
        if(subButton.size() > 5){
            throw new WeixinException("二级菜单最多5个");
        }
        this.subButton = subButton;
    }
}
