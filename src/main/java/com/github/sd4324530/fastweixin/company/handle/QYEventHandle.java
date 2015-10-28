package com.github.sd4324530.fastweixin.company.handle;

import com.github.sd4324530.fastweixin.company.message.req.QYBaseEvent;
import com.github.sd4324530.fastweixin.company.message.resp.QYBaseRespMsg;

/**
 *  微信企业号事件处理器接口
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public interface QYEventHandle<E extends QYBaseEvent>{

    /**
     * 处理微信事件
     *
     * @param event 微信事件
     * @return 回复用户的消息
     */
    QYBaseRespMsg handle(E event);

    /**
     * 在处理之前，判断本事件是否符合处理的条件
     *
     * @param event 事件
     * @return 是否需要处理
     */
    boolean beforeHandle(E event);
}
