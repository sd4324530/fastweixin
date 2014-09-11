package com.github.sd4324530.fastweixin.message;

import com.github.sd4324530.fastweixin.message.util.MessageBuilder;

public class Article {

    private String title;
    private String description;
    private String picUrl;
    private String url;

    public Article() {

    }

    public Article(String title) {
        this.title = title;
    }

    public Article(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public Article(String title, String description, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toXml() {
        MessageBuilder mb = new MessageBuilder();
        mb.addData("Title", title);
        mb.addData("Description", description);
        mb.addData("PicUrl", picUrl);
        mb.addData("Url", url);
        mb.surroundWith("item");
        return mb.toString();
    }

}
