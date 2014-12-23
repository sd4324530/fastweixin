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
    public static Map<String, String> parseXml(HttpServletRequest request, String token, String appId, String aesKey) {
        Map<String, String> map = new HashMap<String, String>();

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
                    if (!"xml".equals(tagName)) {
                        String text = reader.getElementText();
                        map.put(tagName, text);
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
}
