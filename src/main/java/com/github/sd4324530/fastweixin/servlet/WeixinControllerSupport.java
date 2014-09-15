package com.github.sd4324530.fastweixin.servlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 微信公众平台交互操作基类，提供几乎所有微信公众平台交互方式
 * 基于springmvc框架，方便使用此框架的项目集成
 *
 * @author peiyu
 */
@RestController
public abstract class WeixinControllerSupport extends WeixinSupport {

    /**
     * 绑定微信服务器
     *
     * @param request 请求
     * @return 响应内容
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    protected final String bind(HttpServletRequest request) {
        if (isLegal(request)) {
            //绑定微信服务器成功
            return request.getParameter("echostr");
        } else {
            //绑定微信服务器失败
            return "";
        }
    }

    /**
     * 微信消息交互处理
     *
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    protected final String process(HttpServletRequest request) throws ServletException, IOException {
        if (!isLegal(request)) {
            return "";
        }
        String resp = processRequest(request);
        return resp;
    }


}