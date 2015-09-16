package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

/**
 * 模版消息
 */
public class TemplateMsg extends BaseModel {
    private String touser;
    @JSONField(name = "template_id")
    private String templateId;
    private String url;
    private String topcolor;

    private Map<String, TemplateParam> data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Map<String, TemplateParam> getData() {
        return data;
    }

    public void setData(Map<String, TemplateParam> data) {
        this.data = data;
    }
}
