package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetSendMessageResponse;
import com.github.sd4324530.fastweixin.message.*;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.soap.Text;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息相关API
 *
 * @author peiyu, Nottyjay
 * @since 1.3
 */
public class MessageAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(MessageAPI.class);

    public MessageAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 群发消息给用户。
     * 本方法调用需要账户为微信已认证账户
     * @param message 消息主体
     * @param isToAll 是否发送给全部用户。false时需要填写groupId，true时可忽略groupId树形
     * @param groupId 群组ID
     * @return
     */
    public GetSendMessageResponse sendMessageToUser(BaseMsg message, boolean isToAll, String groupId){
        BeanUtil.requireNonNull(message, "message is null");
        LOG.debug("群发消息......");
        String url = BASE_API_URL + "cgi-bin/message/mass/sendall?access_token=#";
        final Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("is_to_all", isToAll);
        if(!isToAll){
            BeanUtil.requireNonNull(groupId, "groupId is null");
            filterMap.put("group_id", groupId);
        }
        params.put("filter", filterMap);
        if(message instanceof MpNewsMsg){
            params.put("msgtype", "mpnews");
            MpNewsMsg msg = (MpNewsMsg)message;
            Map<String, Object> mpNews = new HashMap<String, Object>();
            mpNews.put("media_id", msg.getMediaId());
            params.put("mpnews", mpNews);
        }else if(message instanceof TextMsg){
            params.put("msgtype", "text");
            TextMsg msg = (TextMsg)message;
            Map<String ,Object> text = new HashMap<String, Object>();
            text.put("content", msg.getContent());
            params.put("text", text);
        }else if(message instanceof VoiceMsg){
            params.put("msgtype", "voice");
            VoiceMsg msg = (VoiceMsg)message;
            Map<String, Object> voice = new HashMap<String ,Object>();
            voice.put("media_id", msg.getMediaId());
            params.put("voice", voice);
        }else if(message instanceof ImageMsg){
            params.put("msgtype", "image");
            ImageMsg msg = (ImageMsg)message;
            Map<String, Object> image = new HashMap<String, Object>();
            image.put("media_id", msg.getMediaId());
            params.put("image", image);
        }else if(message instanceof VideoMsg){
            // TODO 此处方法特别
        }
        BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return JSONUtil.toBean(response.getErrmsg(), GetSendMessageResponse.class);
    }

    /**
     * 发布客服消息
     *
     * @param openid  关注者ID
     * @param message 消息对象，支持各种消息类型
     * @return 调用结果
     * @deprecated 此方法已经不再建议使用，使用CustomAPI中方法代替
     */
    @Deprecated
    public ResultType sendCustomMessage(String openid, BaseMsg message) {
        BeanUtil.requireNonNull(openid, "openid is null");
        BeanUtil.requireNonNull(message, "message is null");
        LOG.debug("发布客服消息......");
        String url = BASE_API_URL + "cgi-bin/message/custom/send?access_token=#";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openid);
        if (message instanceof TextMsg) {
            TextMsg msg = (TextMsg) message;
            params.put("msgtype", "text");
            Map<String, String> text = new HashMap<String, String>();
            text.put("content", msg.getContent());
            params.put("text", text);
        } else if (message instanceof ImageMsg) {
            ImageMsg msg = (ImageMsg) message;
            params.put("msgtype", "image");
            Map<String, String> image = new HashMap<String, String>();
            image.put("media_id", msg.getMediaId());
            params.put("image", image);
        } else if (message instanceof VoiceMsg) {
            VoiceMsg msg = (VoiceMsg) message;
            params.put("msgtype", "voice");
            Map<String, String> voice = new HashMap<String, String>();
            voice.put("media_id", msg.getMediaId());
            params.put("voice", voice);
        } else if (message instanceof VideoMsg) {
            VideoMsg msg = (VideoMsg) message;
            params.put("msgtype", "video");
            Map<String, String> video = new HashMap<String, String>();
            video.put("media_id", msg.getMediaId());
            video.put("thumb_media_id", msg.getMediaId());
            video.put("title", msg.getTitle());
            video.put("description", msg.getDescription());
            params.put("video", video);
        } else if (message instanceof MusicMsg) {
            MusicMsg msg = (MusicMsg) message;
            params.put("msgtype", "music");
            Map<String, String> music = new HashMap<String, String>();
            music.put("thumb_media_id", msg.getThumbMediaId());
            music.put("title", msg.getTitle());
            music.put("description", msg.getDescription());
            music.put("musicurl", msg.getMusicUrl());
            music.put("hqmusicurl", msg.getHqMusicUrl());
            params.put("music", music);
        } else if (message instanceof NewsMsg) {
            NewsMsg msg = (NewsMsg) message;
            params.put("msgtype", "news");
            Map<String, Object> news = new HashMap<String, Object>();
            List<Object> articles = new ArrayList<Object>();
            List<Article> list = msg.getArticles();
            for (Article article : list) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("title", article.getTitle());
                map.put("description", article.getDescription());
                map.put("url", article.getUrl());
                map.put("picurl", article.getPicUrl());
                articles.add(map);
            }
            news.put("articles", articles);
            params.put("news", news);
        }
        BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return ResultType.get(response.getErrcode());
    }
}
