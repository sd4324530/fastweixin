package com.github.sd4324530.fastweixin.company.message;/**
 * Created by Nottyjay on 2015/6/12.
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashMap;
import java.util.List;
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
public class QYNewsMsg extends QYBaseMsg {

    private static final Integer MAX_ARTICLE_COUNT = 10;

    @JSONField(name = "news")
    private Map<String, Object> news;

    public QYNewsMsg() {
        news = new HashMap<String, Object>();
    }

    public Map<String, Object> getNews() {
        return news;
    }

    public void setNews(Map<String, Object> news) {
        this.news = news;
    }

    public void setArticles(List<QYArticle> articles){
        if(articles.size() > MAX_ARTICLE_COUNT){
            articles = articles.subList(0, MAX_ARTICLE_COUNT);
        }
        news.put("articles", articles);
    }
}


