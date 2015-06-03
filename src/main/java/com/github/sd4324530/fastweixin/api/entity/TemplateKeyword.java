package com.github.sd4324530.fastweixin.api.entity;
/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class TemplateKeyword {

    private String value;
    private String color;

    public TemplateKeyword(String value, String color) {
        this.value = value;
        this.color = color;
    }

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

    public static TemplateKeyword create(String value, String color){
        return new TemplateKeyword(value, color);
    }
}
