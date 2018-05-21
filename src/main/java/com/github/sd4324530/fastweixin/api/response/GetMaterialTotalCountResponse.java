package com.github.sd4324530.fastweixin.api.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class GetMaterialTotalCountResponse extends BaseResponse {

    @JSONField(name="video_count")
    private int video;
    @JSONField(name="voice_count")
    private int voice;
    @JSONField(name="image_count")
    private int image;
    @JSONField(name="news_count")
    private int news;

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "GetMaterialTotalCountResponse{" +
                "video=" + video +
                ", voice=" + voice +
                ", image=" + image +
                ", news=" + news +
                '}';
    }
}
