package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.CustomAccount;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetCustomAccountsResponse;
import com.github.sd4324530.fastweixin.exception.WeixinException;
import com.github.sd4324530.fastweixin.message.*;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客服相关API
 *
 * @author peiyu
 * @since 1.2
 */
public class CustomAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(CustomAPI.class);

    public CustomAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 发布客服消息
     *
     * @param openid  关注者ID
     * @param message 消息对象，支持各种消息类型
     * @return 调用结果
     */
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
        } else if (message instanceof MpNewsMsg) {
        	MpNewsMsg msg = (MpNewsMsg) message;
            params.put("msgtype", "mpnews");
            Map<String, String> news = new HashMap<String, String>();
            news.put("media_id", msg.getMediaId());
            params.put("mpnews", news);
        }
        BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 添加客服帐号
     *
     * @param customAccount 客服对象
     * @return 添加结果
     */
    public ResultType addCustomAccount(CustomAccount customAccount) {
        LOG.debug("添加客服帐号.....");
        BeanUtil.requireNonNull(customAccount.getAccountName(), "帐号必填");
        BeanUtil.requireNonNull(customAccount.getNickName(), "昵称必填");
        String url = BASE_API_URL + "customservice/kfaccount/add?access_token=#";
        Map<String, String> params = new HashMap<String, String>();
        params.put("kf_account", customAccount.getAccountName());
        params.put("nickname", customAccount.getNickName());
        if (StrUtil.isNotBlank(customAccount.getPassword())) {
            params.put("password", customAccount.getPassword());
        }
        BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 修改客服帐号信息
     *
     * @param customAccount 客服帐号信息
     * @return 修改结果
     */
    public ResultType updateCustomAccount(CustomAccount customAccount) {
        LOG.debug("修改客服帐号信息......");
        BeanUtil.requireNonNull(customAccount.getAccountName(), "帐号必填");
        BeanUtil.requireNonNull(customAccount.getNickName(), "昵称必填");
        String url = BASE_API_URL + "customservice/kfaccount/update?access_token=#";
        Map<String, String> params = new HashMap<String, String>();
        params.put("kf_account", customAccount.getAccountName());
        params.put("nickname", customAccount.getNickName());
        if (StrUtil.isNotBlank(customAccount.getPassword())) {
            params.put("password", customAccount.getPassword());
        }
        BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 删除客服帐号
     * @param accountName 客服帐号名
     * @return 删除结果
     */
    public ResultType deleteCustomAccount(String accountName) {
        LOG.debug("删除客服帐号信息......");
        String url = BASE_API_URL + "customservice/kfaccount/del?access_token=#&kf_account=" + accountName;
        BaseResponse response = executePost(url, null);
        return ResultType.get(response.getErrcode());
    }

    /**
     * 设置客服帐号头像
     *
     * @param accountName 客服帐号名
     * @param file        头像文件
     * @return 设置结果
     */
    public ResultType uploadHeadImg(String accountName, File file) {
        LOG.debug("设置客服帐号头像.....");
        BeanUtil.requireNonNull(accountName, "帐号必填");
        BeanUtil.requireNonNull(file, "文件为空");

        String fileName = file.getName().toLowerCase();
        if (!fileName.endsWith("jpg")) {
            throw new WeixinException("头像必须是jpg格式");
        }
        String url = BASE_API_URL + "customservice/kfaccount/uploadheadimg?access_token=#&kf_account=" + accountName;
        BaseResponse response = executePost(url, null, file);
        return ResultType.get(response.getErrcode());
    }

    /**
     * 获取所有客服帐号信息
     * @return 所有客服帐号信息对象
     */
    public GetCustomAccountsResponse getCustomAccountList() {
        LOG.debug("获取所有客服帐号信息....");
        GetCustomAccountsResponse response;
        String url = BASE_API_URL + "cgi-bin/customservice/getkflist?access_token=#";
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, GetCustomAccountsResponse.class);
        return response;
    }
}
