package com.github.sd4324530.fastweixin.company.api.enums;

/**
 * 微信企业号菜单类型枚举
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 * @author Nottyjay
 * @version 1.0.beta
 * @since 1.3.6
 * ====================================================================
 */
public enum QYMenuType {
    /**
     * 点击推事件
     */
    CLICK("click"),

    /**
     * 跳转URL
     */
    VIEW("view"),

    /*-------------------------以下仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户------------------------*/

    /**
     * 扫码推事件
     */
    SCANCODE_PUSH("scancode_push"),

    /**
     * 扫码推事件且弹出“消息接收中”提示框
     */
    SCANCODE_WAITMSG("scancode_waitmsg"),

    /**
     * 弹出系统拍照发图
     */
    PIC_SYSPHOTO("pic_sysphoto"),

    /**
     * 弹出拍照或者相册发图
     */
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),

    /**
     * 弹出微信相册发图器
     */
    PIC_WEIXIN("pic_weixin"),

    /**
     * 弹出地理位置选择器
     */
    LOCATION_SELECT("location_select");

    String value;

    QYMenuType(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
