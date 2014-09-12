fastweixin
==========
作者:peiyu<br>
[小弟微博](http://weibo.com/1728407960)<br>
QQ:125331682<br>
#快速搭建微信公众平台服务器<br>
简单封装了所有与微信服务器交互的消息:文本消息、图片消息、图文消息等等<br>
提供了一个基于`springmvc`的控制器，集成了微信服务器绑定、监听所有类型消息的方法<br>
使用时继承，重写即可，十分方便<br>

```Java
@RestController
@RequestMapping("/weixin")
public class WeixinController extends WeixinServletSupport {
        private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
        private static final String TOKEN = "erhuluanzi";
        //设置TOKEN，用于绑定微信服务器
        @Override
        protected String getToken() {
            return TOKEN;
        }
        //重写父类方法，处理对应的微信消息
        @Override
        protected BaseMsg handleTextMsg(TextReqMsg msg) {
            String content = msg.getContent();
            log.debug("发送内容:{}", content);
            return new TextMsg("回复消息!");
        }
}
```