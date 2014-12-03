fastweixin
==========
作者:peiyu<br>
[我的微博](http://weibo.com/1728407960)<br>
QQ:125331682<br>

项目主页:[https://github.com/sd4324530/fastweixin](https://github.com/sd4324530/fastweixin)<br>
开源中国主页:[http://git.oschina.net/pyinjava/fastweixin](http://git.oschina.net/pyinjava/fastweixin)<br>
csdn主页:[https://code.csdn.net/sd4324530/fastweixin](https://code.csdn.net/sd4324530/fastweixin)<br>
#快速搭建微信公众平台服务器<br>
简单封装了所有与微信服务器交互的消息:文本消息、图片消息、图文消息等等<br>
提供了基于`springmvc`以及基于`servlet`框架的控制器，集成了微信服务器绑定、监听所有类型消息的方法<br>
使用时继承，重写即可，十分方便<br>
v1.2.0开始支持高级接口的API，https请求基于org.apache.httpcomponents 4.3.X，json解析基于fastjson 1.1.X<br>
框架中提供MenuAPI、MessageAPI、QrcodeAPI、UserAPI用于实现所有高级接口功能，使用极其简单<br>
内部实现token过期自动刷新，不用再关注token细节<br>

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
        private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
        private static final String TOKEN = "myToken";
        //设置TOKEN，用于绑定微信服务器
        @Override
        protected String getToken() {
            return TOKEN;
        }
        //重写父类方法，处理对应的微信消息
        @Override
        protected BaseMsg handleTextMsg(TextReqMsg msg) {
            String content = msg.getContent();
            log.debug("用户发送到服务器的内容:{}", content);
            return new TextMsg("服务器回复用户消息!");
        }
        //1.1版本新增，重写父类方法，加入自定义微信消息处理器
        @Override
        protected List<MessageHandle> initMessageHandles() {
            List<MessageHandle> handles = new ArrayList<MessageHandle>();
            handles.add(new MyMessageHandle());
            return handles;
        }
        //1.1版本新增，重写父类方法，加入自定义微信事件处理器
        @Override
        protected List<EventHandle> initEventHandles() {
            List<EventHandle> handles = new ArrayList<EventHandle>();
            handles.add(new MyEventHandle());
            return handles;
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
    <version>1.2.5</version>
</dependency>
```

开源协议
==========
[Apache License](http://www.apache.org/licenses/LICENSE-2.0)
