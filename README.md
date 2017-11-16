fastweixin
==========
作者:peiyu

QQ:[125331682](http://wpa.qq.com/msgrd?v=3&uin=125331682&site=qq&menu=yes)

技术讨论QQ群:[367162748](http://shang.qq.com/wpa/qunwpa?idkey=e279a5147f3cb248a536e118464c72068d9f6ef33278987e6f88a17aab603cbb)

码云主页:[https://gitee.com/pyinjava/fastweixin](https://gitee.com/pyinjava/fastweixin)


[![Build Status](https://api.travis-ci.org/sd4324530/fastweixin.png?branch=master)](https://travis-ci.org/sd4324530/fastweixin)
[![@peiyu on weibo](https://img.shields.io/badge/weibo-%40peiyu-red.svg)](http://weibo.com/1728407960)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.sd4324530/fastweixin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.sd4324530/fastweixin)
[![Circle CI](https://circleci.com/gh/sd4324530/fastweixin/tree/master.svg?style=svg)](https://circleci.com/gh/sd4324530/fastweixin/tree/master)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/sd4324530/fastweixin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

# 快速搭建微信公众平台服务器
简单封装了所有与微信服务器交互的消息:文本消息、图片消息、图文消息等等
提供了基于`springmvc`以及基于`servlet`框架的控制器，集成了微信服务器绑定、监听所有类型消息的方法
使用时继承，重写即可，十分方便
支持高级接口的API，框架中提供MenuAPI、CustomAPI、QrcodeAPI、UserAPI、MediaAPI、OauthAPI用于实现所有高级接口功能，使用极其简单

内部实现token过期自动刷新，不用再关注token细节

支持微信消息安全模式，但由于jdk的限制，导致想使用安全模式，必须修改jdk内部的jar包
官方网站下载：

[JCE无限制权限策略文件JDK7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)

[JCE无限制权限策略文件JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)



下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt
如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件
如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件

## 详细文档（感谢[luozhuang](http://blog.csdn.net/luozhuang) 的整理）

[【fastweixin框架教程1】一分钟快速搭建微信公众平台JAVA服务器](http://blog.csdn.net/luozhuang/article/details/51321485)

[【fastweixin框架教程2】weblogic javax.xml 解析xml问题](http://blog.csdn.net/luozhuang/article/details/51322065)

[【fastweixin框架教程3】JAVA进行HTTPS网站访问，PKIX path building failed解决方法](http://blog.csdn.net/luozhuang/article/details/51322127)

[【fastweixin框架教程4】AccessToken、QYAPIConfig管理](http://blog.csdn.net/luozhuang/article/details/51322177)

[【fastweixin框架教程5】企业号成员部门管理](http://blog.csdn.net/luozhuang/article/details/51322220)

[【fastweixin框架教程6】微信企业号给关注的用户主动发送推送消息](http://blog.csdn.net/luozhuang/article/details/51322292)

[【fastweixin框架教程7】微信企业号自定义菜单接口开发](http://blog.csdn.net/luozhuang/article/details/51322516)

[【fastweixin框架教程8】微信企业号临时素材管理](http://blog.csdn.net/luozhuang/article/details/51444137)

[【fastweixin框架教程9】扩展API实例——QYOauthAPI](http://blog.csdn.net/luozhuang/article/details/51444222)




## 基于`springmvc`项目的集成方法
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

## 基于`servlet`项目的集成方法
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

## 基于`Jfinal`框架项目的集成方法
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
    <version>1.3.15</version>
</dependency>
```