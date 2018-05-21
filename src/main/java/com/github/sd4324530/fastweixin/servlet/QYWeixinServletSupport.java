package com.github.sd4324530.fastweixin.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  微信企业平台交互操作基类，提供几乎所有微信企业平台交互方式
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public abstract class QYWeixinServletSupport extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(QYWeixinServletSupport.class);
    private final QYWeixinSupport support = getQYWeixinsupport();

    /**
     * 强制要求servlet框架开发者自行实现QYWeixinSupport类
     *
     * @return 用户自行实现的QYWeixinSupport对象
     */
    protected abstract QYWeixinSupport getQYWeixinsupport();

    /**
     * 重写servlet中的get方法，用于处理微信服务器绑定，置为final方法，用户已经无需重写这个方法啦
     *
     * @param request  http请求对象
     * @param response http响应对象
     * @throws javax.servlet.ServletException servlet异常
     * @throws java.io.IOException      IO异常
     */
    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        support.bindServer(request, response);
    }

    /**
     * 重写servlet中的post方法，用于接收微信服务器发来的消息，置为final方法，用户已经无需重写这个方法啦
     *
     * @param request  http请求对象
     * @param response http响应对象
     * @throws ServletException servlet异常
     * @throws IOException      IO异常
     */
    @Override
    protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应编码格式
        response.setCharacterEncoding("UTF-8");
        String resp = support.processRequest(request);
        PrintWriter pw = response.getWriter();
        pw.write(resp);
        pw.flush();
        pw.close();
    }
}
