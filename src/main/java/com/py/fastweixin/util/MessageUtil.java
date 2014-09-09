package com.py.fastweixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 * 消息工具类
 * 用于解析微信平台消息xml报文
 * @author peiyu
 */
public class MessageUtil {

    private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);

	public static Map<String, String> parseXml(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader reader = factory.createXMLEventReader(inputStream);
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement()) {
					String tagName = event.asStartElement().getName()
							.toString();
					if (!tagName.equals("xml")) {
						String text = reader.getElementText();
						map.put(tagName, text);
					}
				}
			}
		} catch (IOException e) {
			log.error("IO出现异常:", e);
		} catch (XMLStreamException e) {
			log.error("XML解析出现异常:", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				log.error("关闭资源出现异常:", e);
			}
		}
		return map;
	}
}
