package com.github.sd4324530.fastweixin.api.entity;

import java.util.List;

/**
 * 模版消息
 */
public class TemplateMsg extends BaseModel {
    private String touser;
    private String templateId;
    private String url;
    private String topcolor;

    private List<TemplateParam> data;

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

    public List<TemplateParam> getData() {
        return data;
    }

    public void setData(List<TemplateParam> data) {
        this.data = data;
    }
}
