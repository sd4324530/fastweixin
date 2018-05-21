package com.github.sd4324530.fastweixin.util;

import com.github.sd4324530.fastweixin.message.aes.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类
 * 用于解析微信平台消息xml报文
 *
 * @author peiyu
 */
public final class MessageUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MessageUtil.class);

    private static final String FORMAT = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";

    /**
     * 此类不需要实例化
     */
    private MessageUtil() {
    }

    /**
     * 解析从微信服务器来的请求，将消息或者事件返回出去
     *
     * @param request http请求对象
     * @param token   用户设置的taken
     * @param appId   公众号的APPID
     * @param aesKey  用户设置的加密密钥
     * @return 微信消息或者事件Map
     */
    public static Map<String, Object> parseXml(HttpServletRequest request, String token, String appId, String aesKey) {
        Map<String, Object> map = new HashMap<String, Object>();

        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (StrUtil.isNotBlank(aesKey)) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                StreamUtil.copy(inputStream, outputStream);
                String body = outputStream.toString();
                LOG.debug("收到的消息密文:{}", body);

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                StringReader sr = new StringReader(body);
                InputSource is = new InputSource(sr);
                Document document = db.parse(is);

                Element root = document.getDocumentElement();
                NodeList nodelist1 = root.getElementsByTagName("Encrypt");


                WXBizMsgCrypt pc = new WXBizMsgCrypt(token, aesKey, appId);
                String msgSignature = request.getParameter("msg_signature");
                String timeStamp = request.getParameter("timestamp");
                String nonce = request.getParameter("nonce");
                LOG.debug("msgSignature:{}", msgSignature);
                LOG.debug("timeStamp:{}", timeStamp);
                LOG.debug("nonce:{}", nonce);
                String encrypt = nodelist1.item(0).getTextContent();
                String fromXML = String.format(FORMAT, encrypt);
                String message = pc.decryptMsg(msgSignature, timeStamp, nonce, fromXML);
                inputStream = new ByteArrayInputStream(message.getBytes());
            }
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(inputStream);
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    String tagName = event.asStartElement().getName()
                            .toString();
                    if("SendPicsInfo".equals(tagName)) {
                        map.put(tagName, eventSendPicsInfo(reader));
                    } else if("SendLocationInfo".equals(tagName)) {
                        map.put(tagName, eventSendLocationInfo(reader));
                    } else if("ScanCodeInfo".equals(tagName)) {
                        map.put(tagName, eventScanCodePush(reader));
                    } else if("xml".equals(tagName)) {
                    } else {
                        map.put(tagName, reader.getElementText());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("IO出现异常", e);
        } catch (XMLStreamException e) {
            LOG.error("XML解析出现异常", e);
        } catch (Exception e) {
            LOG.error("其他异常", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error("IO出现异常", e);
            }
        }
        return map;
    }

    /**
     * Event为pic_sysphoto, pic_photo_or_album, pic_weixin时触发
     * @param reader reader
     * @return 读取结果
     * @throws XMLStreamException XML解析异常
     */
    protected static Map<String, Object> eventSendPicsInfo(XMLEventReader reader) throws XMLStreamException {
        Map<String, Object> sendPicsInfoMap = new HashMap<String, Object>();
        while (reader.hasNext()){
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                String tagName = event.asStartElement().getName()
                        .toString();
                if("Count".equals(tagName)){
                    sendPicsInfoMap.put(tagName, reader.getElementText());
                }else if("PicList".equals(tagName)){
//                    StringBuilder sb = new StringBuilder();
                    List<Map<String, String>> picList = CollectionUtil.newArrayList();
                    while(reader.hasNext()){
                        XMLEvent event1 = reader.nextEvent();
                        if(event1.isStartElement() && "PicMd5Sum".equals(event1.asStartElement().getName()
                                .toString())){
                            Map<String, String> picMap = new HashMap<String, String>();
                            picMap.put("PicMd5Sum", reader.getElementText());
//                            sb.append(reader.getElementText());
//                            sb.append(",");
                            picList.add(picMap);
                        }else if(event1.isEndElement() && "PicList".equals(event1.asEndElement().getName().toString())){
                            break;
                        }
                    }
//                    sendPicsInfoMap.put(tagName, sb.substring(0, sb.length()));
                    sendPicsInfoMap.put(tagName, picList);
                }
            }
        }

        return sendPicsInfoMap;
    }

    /**
     * Event为location_select时触发
     * @param reader reader
     * @return 读取结果
     * @throws XMLStreamException XML解析异常
     */
    protected static Map<String, Object> eventSendLocationInfo(XMLEventReader reader) throws XMLStreamException{
        Map<String, Object> sendLocationInfo = new HashMap<String, Object>();
        while(reader.hasNext()){
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                String tagName = event.asStartElement().getName()
                        .toString();
                sendLocationInfo.put(tagName, reader.getElementText());
            }
        }
        return sendLocationInfo;
    }

    /**
     * Event为scancode_push, scancode_waitmsg时触发
     * @param reader reader
     * @return 读取结果
     * @throws XMLStreamException XML解析异常
     */
    protected static Map<String, Object> eventScanCodePush(XMLEventReader reader) throws XMLStreamException{
        Map<String, Object> scanCodePush = new HashMap<String, Object>();
        while(reader.hasNext()){
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                String tagName = event.asStartElement().getName()
                        .toString();
                scanCodePush.put(tagName, reader.getElementText());
            }
        }
        return scanCodePush;
    }
}
