package com.github.sd4324530.fastweixin.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.message.Article;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class DownloadMaterialResponse extends BaseResponse {

    // 当素材是图文素材的时候
    @JSONField(name="news_item")
    private List<Article> news;

    // 当素材是视频素材的时候
    @JSONField(name="title")
    private String title;
    @JSONField(name="description")
    private String description;
    @JSONField(name="downUrl")
    private String downUrl;

    private byte[] content;

    public List<Article> getNews() {
        return news;
    }

    public void setNews(List<Article> news) {
        this.news = news;
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

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public void setContent(InputStream content){

    }

    public void writeTo(OutputStream out) throws IOException{
        out.write(this.content);
        out.flush();
        out.close();
    }
}
