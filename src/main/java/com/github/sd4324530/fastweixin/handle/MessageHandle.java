package com.github.sd4324530.fastweixin.handle;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;

/**
 * 微信消息处理器接口
 * @author peiyu
 * @since 1.1
 */
public interface MessageHandle {
    /**
     * 处理微信消息
     * @param message 微信消息
     * @return 回复用户的消息
     */
    BaseMsg handle(BaseReqMsg message);
}
