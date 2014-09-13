package com.github.sd4324530.fastweixin.servlet;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.*;
import com.github.sd4324530.fastweixin.util.MessageUtil;
import com.github.sd4324530.fastweixin.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 微信公众平台交互操作基类，提供几乎所有微信公众平台交互方式
 * 基于springmvc框架，方便使用此框架的项目集成
 * @author peiyu
 */
@RestController
public abstract class WeixinControllerSupport {

    private static final Logger log = LoggerFactory.getLogger(WeixinControllerSupport.class);

    /**
     * 子类提供token用于绑定微信公众平台
     * @return token值
     */
    protected abstract String getToken();

    /**
     * 绑定微信服务器
     * @param request 请求
     * @return 响应内容
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    protected final String bind(HttpServletRequest request) throws ServletException, IOException {
        if (isLegal(request)) {
            log.debug("绑定微信公众平台成功!");
            return request.getParameter("echostr");
        } else {
            log.debug("绑定微信公众平台失败!");
            return "";
        }
    }

    /**
     * 微信消息交互处理
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    protected final String process(HttpServletRequest request) throws ServletException, IOException {
        if (!isLegal(request)) {
            return "";
        }
        log.debug("开始处理微信消息.....");
        String resp = processRequest(request);
        return resp;
    }

    private String processRequest(HttpServletRequest request) {

        Map<String, String> reqMap = MessageUtil.parseXml(request);
        String fromUserName = reqMap.get("FromUserName");
        String toUserName = reqMap.get("ToUserName");
        String msgType = reqMap.get("MsgType");

        BaseMsg msg = null;

        if (msgType.equals(ReqType.EVENT)) {
            String eventType = reqMap.get("Event");

            String ticket = reqMap.get("Ticket");
            if (ticket != null) {
                String eventKey = reqMap.get("EventKey");
                QrCodeEvent event = new QrCodeEvent(eventKey, ticket);
                buildBasicEvent(reqMap, event);
                msg = handleQrCodeEvent(event);
            }
            if (eventType.equals(EventType.SUBSCRIBE)) {
                BaseEvent event = new BaseEvent();
                buildBasicEvent(reqMap, event);
                msg = handleSubscribe(event);
            }
            else if (eventType.equals(EventType.UNSUBSCRIBE)) {
                BaseEvent event = new BaseEvent();
                buildBasicEvent(reqMap, event);
                msg = handleUnsubscribe(event);
            }
            else if (eventType.equals(EventType.CLICK)) {
                String eventKey = reqMap.get("EventKey");
                MenuEvent event = new MenuEvent(eventKey);
                buildBasicEvent(reqMap, event);
                msg = handleMenuClickEvent(event);
            }
            else if (eventType.equals(EventType.VIEW)) {
                String eventKey = reqMap.get("EventKey");
                MenuEvent event = new MenuEvent(eventKey);
                buildBasicEvent(reqMap, event);
                msg = handleMenuViewEvent(event);
            } else if (eventType.equals(EventType.LOCATION)) {
                double latitude = Double.parseDouble(reqMap.get("Latitude"));
                double longitude = Double.parseDouble(reqMap.get("Longitude"));
                double precision = Double.parseDouble(reqMap.get("Precision"));
                LocationEvent event = new LocationEvent(latitude, longitude,
                        precision);
                buildBasicEvent(reqMap, event);
                msg = handleLocationEvent(event);
            }

        } else {
            if (msgType.equals(ReqType.TEXT)) {
                String content = reqMap.get("Content");
                TextReqMsg textReqMsg = new TextReqMsg(content);
                buildBasicReqMsg(reqMap, textReqMsg);
                msg = handleTextMsg(textReqMsg);
            } else if (msgType.equals(ReqType.IMAGE)) {
                String picUrl = reqMap.get("PicUrl");
                String mediaId = reqMap.get("MediaId");
                ImageReqMsg imageReqMsg = new ImageReqMsg(picUrl, mediaId);
                buildBasicReqMsg(reqMap, imageReqMsg);
                msg = handleImageMsg(imageReqMsg);
            } else if (msgType.equals(ReqType.VOICE)) {
                String format = reqMap.get("Format");
                String mediaId = reqMap.get("MediaId");
                String recognition = reqMap.get("Recognition");
                VoiceReqMsg voiceReqMsg = new VoiceReqMsg(mediaId, format,
                        recognition);
                buildBasicReqMsg(reqMap, voiceReqMsg);
                msg = handleVoiceMsg(voiceReqMsg);
            } else if (msgType.equals(ReqType.VIDEO)) {
                String thumbMediaId = reqMap.get("ThumbMediaId");
                String mediaId = reqMap.get("MediaId");
                VideoReqMsg videoReqMsg = new VideoReqMsg(mediaId, thumbMediaId);
                buildBasicReqMsg(reqMap, videoReqMsg);
                msg = handleVideoMsg(videoReqMsg);
            } else if (msgType.equals(ReqType.LOCATION)) {
                double locationX = Double.parseDouble(reqMap.get("Location_X"));
                double locationY = Double.parseDouble(reqMap.get("Location_Y"));
                int scale = Integer.parseInt(reqMap.get("Scale"));
                String label = reqMap.get("Label");
                LocationReqMsg locationReqMsg = new LocationReqMsg(locationX,
                        locationY, scale, label);
                buildBasicReqMsg(reqMap, locationReqMsg);
                msg = handleLocationMsg(locationReqMsg);
            } else if (msgType.equals(ReqType.LINK)) {
                String title = reqMap.get("Title");
                String description = reqMap.get("Description");
                String url = reqMap.get("Url");
                LinkReqMsg linkReqMsg = new LinkReqMsg(title, description, url);
                buildBasicReqMsg(reqMap, linkReqMsg);
                msg = handleLinkMsg(linkReqMsg);
            }

        }

        if (msg == null) {
            return "";
        }

        msg.setFromUserName(toUserName);
        msg.setToUserName(fromUserName);
        return msg.toXml();
    }

    /**
     * 处理文本消息，有需要时子类重写
     * @param msg 请求消息对象
     * @return 响应消息对象
     */
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理图片消息，有需要时子类重写
     * @param msg 请求消息对象
     * @return 响应消息对象
     */
    protected BaseMsg handleImageMsg(ImageReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理语音消息，有需要时子类重写
     * @param msg 请求消息对象
     * @return 响应消息对象
     */
    protected BaseMsg handleVoiceMsg(VoiceReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理视频消息，有需要时子类重写
     * @param msg 请求消息对象
     * @return 响应消息对象
     */
    protected BaseMsg handleVideoMsg(VideoReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理地理位置消息，有需要时子类重写
     * @param msg 请求消息对象
     * @return 响应消息对象
     */
    protected BaseMsg handleLocationMsg(LocationReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理链接消息，有需要时子类重写
     * @param msg 请求消息对象
     * @return 响应消息对象
     */
    protected BaseMsg handleLinkMsg(LinkReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理扫描二维码事件，有需要时子类重写
     * @param event 扫描二维码事件对象
     * @return 响应消息对象
     */
    protected BaseMsg handleQrCodeEvent(QrCodeEvent event) {
        return handleDefaultEvent(event);
    }

    /**
     * 处理地理位置事件，有需要时子类重写
     * @param event 地理位置事件对象
     * @return 响应消息对象
     */
    protected BaseMsg handleLocationEvent(LocationEvent event) {
        return handleDefaultEvent(event);
    }

    /**
     * 处理菜单点击事件，有需要时子类重写
     * @param event 菜单点击事件对象
     * @return 响应消息对象
     */
    protected BaseMsg handleMenuClickEvent(MenuEvent event) {
        return handleDefaultEvent(event);
    }

    /**
     * 处理菜单跳转事件，有需要时子类重写
     * @param event 菜单跳转事件对象
     * @return 响应消息对象
     */
    protected BaseMsg handleMenuViewEvent(MenuEvent event) {
        return handleDefaultEvent(event);
    }

    /**
     * 处理添加关注事件，有需要时子类重写
     * @param event 添加关注事件对象
     * @return 响应消息对象
     */
    protected BaseMsg handleSubscribe(BaseEvent event) {
        return new TextMsg("感谢您的关注!");
    }

    /**
     * 处理取消关注事件，有需要时子类重写
     * @param event 取消关注事件对象
     * @return 响应消息对象
     */
    protected BaseMsg handleUnsubscribe(BaseEvent event) {
        return null;
    }

    protected BaseMsg handleDefaultMsg(BaseReqMsg msg) {
        return null;
    }

    protected BaseMsg handleDefaultEvent(BaseEvent event) {
        return null;
    }

    private void buildBasicReqMsg(Map<String, String> reqMap, BaseReqMsg reqMsg) {
        addBasicReqParams(reqMap, reqMsg);
        reqMsg.setMsgId(reqMap.get("MsgId"));
    }

    private void buildBasicEvent(Map<String, String> reqMap, BaseEvent event) {
        addBasicReqParams(reqMap, event);
        event.setEvent(reqMap.get("Event"));
    }

    private void addBasicReqParams(Map<String, String> reqMap, BaseReq req) {
        req.setMsgType(reqMap.get("MsgType"));
        req.setFromUserName(reqMap.get("FromUserName"));
        req.setToUserName(reqMap.get("ToUserName"));
        req.setCreateTime(Long.parseLong(reqMap.get("CreateTime")));
    }

    private boolean isLegal(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        return SignUtil.checkSignature(getToken(), signature, timestamp, nonce);
    }
}
