package com.github.sd4324530.fastweixin.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by cl on 2018/4/25.
 * XML工具类
 */
public class XmlUtil {

    private XmlUtil() {
    }

    /**
     * Map转换成XML
     *
     * @param map 待转换的Map
     * @return
     */
    public static String mapToXml(Map<String, Object> map) {
        return mapToXml(map, true);
    }

    /**
     * Map转换成XML
     *
     * @param map        待转换的Map
     * @param ignoreNull 是否忽略空节点
     * @return
     */
    public static String mapToXml(Map<String, Object> map, boolean ignoreNull) {
        Document document = createDocument();
        parseMap(map, document, document, ignoreNull);
        return toXml(document);
    }

    /**
     * XML转换成Map
     *
     * @param xml 待转换的XML
     * @return
     */
    public static Map<String, Object> xmlToMap(String xml) {
        Document document = parseText(xml);
        document.normalize();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        parseNode(document, map);
        return map;
    }

    /**
     * 解析Map，生成xml节点
     *
     * @param map        待解析的Map
     * @param parentNode 父节点
     * @param document   Document对象，用于创建节点
     * @param ignoreNull 是否忽略空节点
     */
    private static void parseMap(Map<String, Object> map, Node parentNode, Document document, boolean ignoreNull) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Collection) {
                Collection<Object> list = (Collection<Object>) value;
                for (Object o : list) {
                    // List中的子元素只能是Map或基本类型数据
                    parseMap(name, o, parentNode, document, ignoreNull);
                }
            } else {
                parseMap(name, value, parentNode, document, ignoreNull);
            }
        }
    }

    /**
     * Map中的键值对解析成xml节点
     *
     * @param name       当前解析的节点名
     * @param value      当前解析的对象
     * @param parentNode 父节点
     * @param document   Document对象，用于创建节点
     * @param ignoreNull 是否忽略空节点
     */
    private static void parseMap(String name, Object value, Node parentNode, Document document, boolean ignoreNull) {
        Element node = document.createElement(name);
        if (value instanceof Map) {
            Map<String, Object> valueMap = (Map<String, Object>) value;
            if (valueMap.size() > 0 || (!ignoreNull)) {
                parseMap(valueMap, node, document, ignoreNull);
                parentNode.appendChild(node);
            }
        } else {
            String text = null == value ? "" : value.toString();
            if (text.trim().length() > 0 || (!ignoreNull)) {
                node.appendChild(document.createTextNode(text));
                parentNode.appendChild(node);
            }
        }
    }

    /**
     * 解析xml节点
     *
     * @param node      待解析的Node
     * @param parentMap 父节点Map
     */
    private static void parseNode(Node node, Map<String, Object> parentMap) {
        String name = node.getNodeName();
        if (parentMap.containsKey(name)) {
            List<Object> list = null;
            Object item = parentMap.get(name);
            if (item instanceof List) {
                list = (List<Object>) item;
            } else {
                list = new ArrayList<Object>();
                list.add(item);
                parentMap.put(name, list);
            }

            Map<String, Object> map = new LinkedHashMap<String, Object>();
            parseNode(node, map);
            list.add(map.get(name));
        } else if (node.getNodeType() == Node.DOCUMENT_NODE) {
            List<Node> childNodes = getChildElementNodes(node);
            for (Node n : childNodes) {
                parseNode(n, parentMap);
            }
        } else {
            List<Node> childNodes = getChildElementNodes(node);
            if (childNodes.size() > 0) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                for (Node n : childNodes) {
                    parseNode(n, map);
                }
                parentMap.put(name, map);
            } else {
                parentMap.put(name, node.getTextContent());
            }
        }
    }

    /**
     * 创建Document对象
     *
     * @return
     */
    public static Document createDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.newDocument();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * Document对象输出为XML
     *
     * @param document
     * @return
     */
    public static String toXml(Document document) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            transformer.transform(source, result);
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 解析XML生成Document对象
     *
     * @param xml
     * @return
     */
    public static Document parseText(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            return builder.parse(in);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 获取子元素节点
     *
     * @param node
     * @return
     */
    private static List<Node> getChildElementNodes(Node node) {
        List<Node> list = new ArrayList<Node>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node subNode = nodeList.item(i);
            if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                list.add(subNode);
            }
        }
        return list;
    }

}
