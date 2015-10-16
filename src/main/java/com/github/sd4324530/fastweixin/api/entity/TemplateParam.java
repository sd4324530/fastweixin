package com.github.sd4324530.fastweixin.api.entity;

/**
 * 模版参数
 */
public class TemplateParam extends BaseModel {

    /**
     * 值
     */
    private String value;
    /**
     * 颜色
     */
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
