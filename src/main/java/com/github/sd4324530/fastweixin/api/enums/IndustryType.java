package com.github.sd4324530.fastweixin.api.enums;
/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public enum IndustryType {

    /**
     * IT科技-互联网/电子商务
     */
    INTERNET(1),

    /**
     * IT科技-IT软件与服务
     */
    SOFTWARE(2),

    /**
     * IT科技-IT硬件与设备
     */
    DEVICE(3),

    /**
     * IT科技-电子技术
     */
    ELECTRONIC(4),

    /**
     * IT科技-通信与运营商
     */
    OPERATOR(5),

    /**
     * IT科技-网络游戏
     */
    ONLINE_GAME(6),

    BANK(7),

    FUND(8),

    INSURANCE(9),

    RESTAURANT(10),

    HOTEL(11),

    TRAVEL(12),

    EXPRESS(13),

    LOGISTICS(14),

    STORAGE(15);

    private int code;

    IndustryType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
