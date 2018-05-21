package com.github.sd4324530.fastweixin.company.message.req;
/**
 *  微信企业号事件消息类型
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public final class QYEventType {

    public static final String SUBSCRIBE       = "subscribe";
    public static final String UNSUBSCRIBE     = "unsubscribe";
    public static final String CLICK           = "CLICK";
    public static final String VIEW            = "VIEW";
    public static final String LOCATION        = "LOCATION";
    public static final String SCAN            = "SCAN";
    public static final String SCANCODEPUSH    = "scancode_push";
    public static final String SCANCODEWAITMSG = "scancode_waitmsg";
    public static final String PICSYSPHOTO     = "pic_sysphoto";
    public static final String PICPHOTOORALBUM = "pic_photo_or_album";
    public static final String PICWEIXIN       = "pic_weixin";
    public static final String LOCATIONSELECT  = "location_select";
    public static final String TEMPLATESENDJOBFINISH  = "TEMPLATESENDJOBFINISH";
    public static final String MASSSENDJOBFINISH="MASSSENDJOBFINISH";
    public static final String ENTERAGENT      = "enter_agent";
    public static final String BATCHJOBRESULT  = "batch_job_result";

    private QYEventType() {
    }
}
