package com.github.sd4324530.fastweixin.company.message.req;

import com.github.sd4324530.fastweixin.message.req.ReqType;

/**
 *  微信企业号推送事件类别
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public final class QYReqType{

    public static final String TEXT        = "text";
    public static final String IMAGE       = "image";
    public static final String LOCATION    = "location";
    public static final String VOICE       = "voice";
    public static final String VIDEO       = "video";
    public static final String SHORT_VIDEO = "shortvideo";
    public static final String EVENT       = "event";

    private QYReqType() {
    }
}
