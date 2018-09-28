package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.util.ObjectUtil;
import com.github.sd4324530.fastweixin.util.XmlUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cl on 2018/4/27.
 */
public class TestUtil {

    @Test
    public void testXmlUtil() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Map<String, Object> root = new LinkedHashMap<String, Object>();
        root.put("appid", "wxd930ea5d5a258f4f");
        root.put("amount", 1500);
        map.put("xml", root);
        String xml = XmlUtil.mapToXml(map);
        System.out.println(xml);

        System.out.println("==============================");
        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<xml>\n" +
                "<appid>wxd930ea5d5a258f4f</appid>\n" +
                "<amount>1500</amount>\n" +
                "<user>a</user>\n" +
                "<user>b</user>\n" +
                "<user>\n" +
                "<account>a1</account>\n" +
                "<account>a2</account>\n" +
                "</user>\n" +
                "</xml>";
        map = XmlUtil.xmlToMap(xml);
        xml = XmlUtil.mapToXml(map);
        System.out.println(xml);
    }

    @Test
    public void testObjectUtil() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "jack");
        map.put("age", 10);
        map.put("birthday", new Date().getTime());
        map.put("amount", "1530.04");

        TestObj obj = ObjectUtil.map2Object(map, TestObj.class);
        System.out.println(obj);
    }

    public static class TestObj {
        private String name;
        private int age;
        private Date birthday;
        private BigDecimal amount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }

}
