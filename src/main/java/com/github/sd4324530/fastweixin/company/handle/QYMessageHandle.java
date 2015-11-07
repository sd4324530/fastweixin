package com.github.sd4324530.fastweixin.company.handle;

import com.github.sd4324530.fastweixin.company.message.req.QYBaseReqMsg;
import com.github.sd4324530.fastweixin.company.message.resp.QYBaseRespMsg;

/**
 *  微信企业号消息处理器接口
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public interface QYMessageHandle<M extends QYBaseReqMsg> {
    /**
     * 处理微信消息
     *
     * @param message 微信消息
     * @return 回复用户的消息
     */
    QYBaseRespMsg handle(M message);

    /**
     * 在处理之前，判断本条消息是否符合处理的条件
     *
     * @param message 消息
     * @return 是否需要处理
     */
    boolean beforeHandle(M message);
}
