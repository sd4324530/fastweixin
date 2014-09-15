package com.github.sd4324530.fastweixin.handle;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;

/**
 * 微信消息处理器接口
 * @author peiyu
 * @since 1.1
 */
public interface MessageHandle {
    BaseMsg handle(BaseReqMsg message);
}
