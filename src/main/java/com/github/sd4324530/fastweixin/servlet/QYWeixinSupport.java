package com.github.sd4324530.fastweixin.servlet;

import com.github.sd4324530.fastweixin.message.aes.AesException;
import com.github.sd4324530.fastweixin.message.aes.WXBizMsgCrypt;
import com.github.sd4324530.fastweixin.util.SignUtil;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 * ====================================================================
 */
public abstract class QYWeixinSupport extends WeixinSupport {

    private static final Logger LOG = LoggerFactory.getLogger(QYWeixinSupport.class);

    private WXBizMsgCrypt wxcpt;

    /**
     * 子类提供token用于绑定微信企业平台
     *
     * @return
     */
    protected abstract String getToken();

    /**
     * 子类提供cropId用于绑定微信企业平台
     *
     * @return
     */
    protected abstract String getCropId();

    /**
     * 加密的密钥，消息解密时需要
     *
     * @return
     */
    protected abstract String getAESKey();

    @Override
    protected String getAppId() {
        return getCropId();
    }

    /**
     * 绑定服务器的方法
     * @param request
     * @param response
     */
    public void bindServer(HttpServletRequest request, HttpServletResponse response){
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StrUtil.isBlank(getToken()) || StrUtil.isBlank(getAESKey()) || StrUtil.isBlank(getCropId())){
            pw.write("");
            pw.flush();
            pw.close();
        }
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(getToken(), getAESKey(), getCropId());
            String echoStr = pc.verifyUrl(request.getParameter("msg_signature"), request.getParameter("timestamp"), request.getParameter("nonce"), request.getParameter("echostr"));
            pw.write(echoStr);
            pw.flush();
            pw.close();
        } catch (AesException e) {
            e.printStackTrace();
            pw.write("");
            pw.flush();
            pw.close();
        }
    }

}
