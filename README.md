fastweixin
==========
作者:peiyu<br>
QQ:[125331682](http://wpa.qq.com/msgrd?v=3&uin=125331682&site=qq&menu=yes)<br>
技术讨论QQ群:[367162748](http://shang.qq.com/wpa/qunwpa?idkey=e279a5147f3cb248a536e118464c72068d9f6ef33278987e6f88a17aab603cbb)<br>

项目主页:[https://github.com/sd4324530/fastweixin](https://github.com/sd4324530/fastweixin)<br>
开源中国主页:[http://git.oschina.net/pyinjava/fastweixin](http://git.oschina.net/pyinjava/fastweixin)<br>
csdn主页:[https://code.csdn.net/sd4324530/fastweixin](https://code.csdn.net/sd4324530/fastweixin)<br>

[![Build Status](https://api.travis-ci.org/sd4324530/fastweixin.png?branch=master)](https://travis-ci.org/sd4324530/fastweixin)
[![@peiyu on weibo](https://img.shields.io/badge/weibo-%40peiyu-red.svg)](http://weibo.com/1728407960)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.sd4324530/fastweixin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.sd4324530/fastweixin)
[![Circle CI](https://circleci.com/gh/sd4324530/fastweixin/tree/master.svg?style=svg)](https://circleci.com/gh/sd4324530/fastweixin/tree/master)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/sd4324530/fastweixin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

#快速搭建微信公众平台服务器<br>
简单封装了所有与微信服务器交互的消息:文本消息、图片消息、图文消息等等<br>
提供了基于`springmvc`以及基于`servlet`框架的控制器，集成了微信服务器绑定、监听所有类型消息的方法<br>
使用时继承，重写即可，十分方便<br>
v1.2.0开始支持高级接口的API，https请求基于org.apache.httpcomponents 4.3.X，json解析基于fastjson 1.1.X<br>
框架中提供MenuAPI、CustomAPI、QrcodeAPI、UserAPI、MediaAPI、OauthAPI用于实现所有高级接口功能，使用极其简单<br>
内部实现token过期自动刷新，不用再关注token细节<br>

v1.2.6开始支持微信消息安全模式，但由于jdk的限制，导致想使用安全模式，必须修改jdk内部的jar包<br>
在官方网站下载：<br>
[JCE无限制权限策略文件JDK7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)<br>
[JCE无限制权限策略文件JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)<br>

下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt<br>
如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件<br>
如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件<br>

v1.3.0重构了微信消息接收控制器，将WeixinSupport类完全独立抽象出来，不再依赖web框架<br>
所以WeixinServletSupport类不再兼容之前的版本，具体使用方法如下:<br>


##基于`springmvc`项目的集成方法
```Java
@RestController
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {
        private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
        private static final String TOKEN = "myToken";
        //设置TOKEN，用于绑定微信服务器
        @Override
        protected String getToken() {
            return TOKEN;
        }
        //使用安全模式时设置：APPID
        //不再强制重写，有加密需要时自行重写该方法
        @Override
        protected String getAppId() {
            return null;
        }
        //使用安全模式时设置：密钥
        //不再强制重写，有加密需要时自行重写该方法
        @Override
        protected String getAESKey() {
            return null;
        }
        //重写父类方法，处理对应的微信消息
        @Override
        protected BaseMsg handleTextMsg(TextReqMsg msg) {
            String content = msg.getContent();
            log.debug("用户发送到服务器的内容:{}", content);
            return new TextMsg("服务器回复用户消息!");
        }
        /*1.1版本新增，重写父类方法，加入自定义微信消息处理器
         *不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
         *这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
         */
        @Override
        protected List<MessageHandle> initMessageHandles() {
                List<MessageHandle> handles = new ArrayList<MessageHandle>();
                handles.add(new MyMessageHandle());
                return handles;
        }
        //1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
        @Override
        protected List<EventHandle> initEventHandles() {
                List<EventHandle> handles = new ArrayList<EventHandle>();
                handles.add(new MyEventHandle());
                return handles;
        }
}
```

##基于`servlet`项目的集成方法
```Java
public class WeixinServlet extends WeixinServletSupport {
        @Override
        protected WeixinSupport getWeixinSupport() {
                return new MyServletWeixinSupport();
        }
}
//用户自行实现的微信消息收发处理器
public class MyServletWeixinSupport extends WeixinSupport {
    private static final Logger log = LoggerFactory.getLogger(MyServletWeixinSupport.class);
    @Override
    protected String getToken() {
        return "myToken";
    }
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        log.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg("服务器回复用户消息!");
    }
}
```
<br>
web.xml配置

```xml
<servlet>
    <servlet-name>weixin</servlet-name>
	<servlet-class>xxx.xxx.WeixinServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>weixin</servlet-name>
    <url-pattern>/weixin</url-pattern>
</servlet-mapping>
```

##基于`Jfinal`框架项目的集成方法
```Java
public class MyJfinalController extends Controller {
    //用户自行实现的消息处理器
    private WeixinSupport support = new MyServletWeixinSupport();
    public void index() {
            HttpServletRequest request = getRequest();
            log.debug("method:{}", request.getMethod());
            //绑定微信服务器
            if ("GET".equalsIgnoreCase(request.getMethod().toUpperCase())) {
                support.bindServer(request, getResponse());
                renderNull();
            } else {
                //处理消息
                renderText(support.processRequest(request), "text/xml");
            }
        }
}
```


Change Log
=========
[https://github.com/sd4324530/fastweixin/wiki/Change-Log](https://github.com/sd4324530/fastweixin/wiki/Change-Log)

Why Use
=========
[https://github.com/sd4324530/fastweixin/wiki/Why-use](https://github.com/sd4324530/fastweixin/wiki/Why-use)

Maven 项目引入
==========
```xml
<dependency>
    <groupId>com.github.sd4324530</groupId>
    <artifactId>fastweixin</artifactId>
    <version>1.3.10</version>
</dependency>
```

##感谢支持
支付宝<br>
![image](https://github.com/sd4324530/fastweixin/blob/master/alipay.png)