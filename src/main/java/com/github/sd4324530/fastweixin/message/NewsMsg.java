package com.github.sd4324530.fastweixin.message;

import java.util.ArrayList;
import java.util.List;

import com.github.sd4324530.fastweixin.message.util.MessageBuilder;

/**
 * @author peiyu
 */
public class NewsMsg extends BaseMsg {

    private static final int WX_MAX_SIZE = 10;

    private int maxSize = WX_MAX_SIZE;

    private List<Article> articles;

    public NewsMsg() {
        this.articles = new ArrayList<Article>(maxSize);
    }

    public NewsMsg(int maxSize) {
        setMaxSize(maxSize);
        this.articles = new ArrayList<Article>(maxSize);
    }

    public NewsMsg(List<Article> articles) {
        setArticles(articles);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        if (maxSize < WX_MAX_SIZE && maxSize >= 1) {
            this.maxSize = maxSize;
        }
        if (articles != null && articles.size() > this.maxSize) {
            articles = articles.subList(0, this.maxSize);
        }
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        if (articles.size() > this.maxSize) {
            this.articles = articles.subList(0, this.maxSize);
        } else {
            this.articles = articles;
        }
    }

    public void add(String title) {
        add(title, null, null, null);
    }

    public void add(String title, String url) {
        add(title, null, null, url);
    }

    public void add(String title, String picUrl, String url) {
        add(new Article(title, null, picUrl, url));
    }

    public void add(String title, String description, String picUrl, String url) {
        add(new Article(title, description, picUrl, url));
    }

    public void add(Article article) {
        if (this.articles.size() < maxSize) {
            this.articles.add(article);
        }
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", RespType.NEWS);
        mb.addTag("ArticleCount", String.valueOf(articles.size()));
        mb.append("<Articles>\n");
        for (Article article : articles) {
            mb.append(article.toXml());
        }
        mb.append("</Articles>\n");
        mb.surroundWith("xml");
        return mb.toString();
    }

}
