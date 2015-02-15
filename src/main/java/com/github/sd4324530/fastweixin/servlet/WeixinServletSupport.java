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
 * 微信公众平台交互操作基类，提供几乎所有微信公众平台交互方式
 * 基于javaee servlet框架，方便使用此框架的项目集成
 *
 * @author peiyu
 * @since 1.1
 */
public abstract class WeixinServletSupport extends HttpServlet {

    private static final Logger LOG  = LoggerFactory.getLogger(WeixinServletSupport.class);
    
    private WeixinSupport support;
    
    public void setSupport(WeixinSupport support) {
		this.support = support;
	}

	/**
     * 重写servlet中的get方法，用于处理微信服务器绑定，置为final方法，用户已经无需重写这个方法啦
     *
     * @param request  http请求对象
     * @param response http响应对象
     * @throws ServletException servlet异常
     * @throws IOException      IO异常
     */
    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (support.isLegal(request)) {
            //绑定微信服务器成功
            PrintWriter pw = response.getWriter();
            pw.write(request.getParameter("echostr"));
            pw.flush();
            pw.close();
        }
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
        if (!support.isLegal(request)) {
            return;
        }
        //设置响应编码格式
        response.setCharacterEncoding("UTF-8");
        String resp = support.processRequest(request);
        PrintWriter pw = response.getWriter();
        pw.write(resp);
        pw.flush();
        pw.close();
    }

}