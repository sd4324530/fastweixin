package com.github.sd4324530.fastweixin.company.message;/**
 * Created by Nottyjay on 2015/6/12.
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 * ====================================================================
 */
public class QYBaseMsg implements Serializable {

    public static final class Safe {
        public static final String YES = "1";// 保密消息
        public static final String NO  = "0";// 非保密消息
    }

    @JSONField(name = "touser")
    private String toUser;
    @JSONField(name = "toparty")
    private String toParty;
    @JSONField(name = "totag")
    private String toTag;
    @JSONField(name = "msgtype")
    private String msgType;
    @JSONField(name = "agentid")
    private String agentId;
    @JSONField(name = "safe")
    private String safe = Safe.NO;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToParty() {
        return toParty;
    }

    public void setToParty(String toParty) {
        this.toParty = toParty;
    }

    public String getToTag() {
        return toTag;
    }

    public void setToTag(String toTag) {
        this.toTag = toTag;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }
}
