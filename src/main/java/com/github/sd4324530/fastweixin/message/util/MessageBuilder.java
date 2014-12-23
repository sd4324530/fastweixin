package com.github.sd4324530.fastweixin.message.util;

/**
 * @author peiyu
 */
public class MessageBuilder {

    private StringBuilder builder;

    public MessageBuilder() {
        builder = new StringBuilder();
    }

    public MessageBuilder(int capacity) {
        builder = new StringBuilder(capacity);
    }

    public MessageBuilder(String str) {
        builder = new StringBuilder(str);
    }

    public void append(String str) {
        builder.append(str);
    }

    public void insert(String str) {
        builder.insert(0, str);
    }

    public void surroundWith(String tag) {
        StringBuilder sb = new StringBuilder(builder.capacity() + tag.length()
                * 2 + 5);
        sb.append("<").append(tag).append(">\n").append(builder).append("</")
                .append(tag).append(">\n");
        builder = sb;
    }

    public void addTag(String tagName, String text) {
        if (text == null) {
            return;
        }
        builder.append("<").append(tagName).append(">").append(text)
                .append("</").append(tagName).append(">\n");
    }

    public void addData(String tagName, String data) {
        if (data == null) {
            return;
        }
        builder.append("<").append(tagName).append("><![CDATA[").append(data)
                .append("]]></").append(tagName).append(">\n");
    }

    @Override
    public String toString() {
        return builder.toString();
    }

}
