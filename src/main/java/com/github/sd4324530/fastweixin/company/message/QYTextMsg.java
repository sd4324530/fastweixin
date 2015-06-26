package com.github.sd4324530.fastweixin.company.message;/**
 * Created by Nottyjay on 2015/6/12.
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 * ====================================================================
 */
public class QYTextMsg extends QYBaseMsg {

    @JSONField(name = "text")
    private Text text;

    public QYTextMsg() {
        this.setMsgType("text");
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public static class Text{
        @JSONField(name = "content")
        private String content;

        public Text(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
