package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.pay.RedPackAPI;
import com.github.sd4324530.fastweixin.pay.TransferAPI;
import com.github.sd4324530.fastweixin.pay.config.DefaultPayConfig;
import com.github.sd4324530.fastweixin.pay.config.PayConfig;
import com.github.sd4324530.fastweixin.pay.entity.request.*;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackInfoResponse;
import com.github.sd4324530.fastweixin.pay.entity.response.RedPackResponse;
import com.github.sd4324530.fastweixin.pay.entity.response.TransferInfoResponse;
import com.github.sd4324530.fastweixin.pay.entity.response.TransferResponse;
import com.github.sd4324530.fastweixin.pay.request.DefaultPayRequestService;
import com.github.sd4324530.fastweixin.pay.request.PayRequestService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by cl on 2018/4/28.
 */
public class TestPay {

    private TransferAPI transferAPI;
    private RedPackAPI redPackAPI;

    @Before
    public void init() {
        PayConfig payConfig = new DefaultPayConfig(
                "xxx",
                "xxx",
                "xxx",
                "xxx");
        PayRequestService payRequestService = new DefaultPayRequestService();
        transferAPI = new TransferAPI(payConfig, payRequestService);
        redPackAPI = new RedPackAPI(payConfig, payRequestService);
    }


    /**
     * 测试付款
     */
    @Test
    public void testTransfer() {
        TransferRequest request = new TransferRequest();
        request.setPartner_trade_no("Test00000002");
        request.setOpenid("o2z9209-JPPr_v31OnawN-otqzLY");
        request.setCheck_name(CommonConstants.NO_CHECK);
        request.setAmount(100);
        request.setDesc("测试");
        request.setSpbill_create_ip("127.0.0.1");
        TransferResponse response = transferAPI.transfer(request);
        System.out.println(response);
    }

    /**
     * 测试普通红包
     */
    @Test
    public void testRedPack() {
        RedPackRequest request = new RedPackRequest();
        request.setMch_billno("TestR0000001");
        request.setSend_name("测试商户");
        request.setRe_openid("o2z9209-JPPr_v31OnawN-otqzLY");
        request.setTotal_amount(100);
        request.setTotal_num(1);
        request.setWishing("五一节日快乐");
        request.setClient_ip("127.0.0.1");
        request.setAct_name("测试活动");
        request.setRemark("test123");
//        request.setScene_id(CommonConstants.RP_PRODUCT_1);
        RedPackResponse response = redPackAPI.sendRedPack(request);
        System.out.println(response);
    }

    /**
     * 测试裂变红包
     */
    @Test
    public void testGroupPack() {
        GroupRedPackRequest request = new GroupRedPackRequest();
        request.setMch_billno("TestR0000002");
        request.setSend_name("测试商户");
        request.setRe_openid("o2z9209-JPPr_v31OnawN-otqzLY");
        request.setTotal_amount(300);
        request.setTotal_num(3);
        request.setAmt_type(CommonConstants.ALL_RAND);
        request.setWishing("裂变红包测试");
        request.setAct_name("测试活动");
        request.setRemark("test123");
        RedPackResponse response = redPackAPI.sendGroupRedPack(request);
        System.out.println(response);
    }

    /**
     * 测试查询付款
     */
    @Test
    public void testTransferInfo() {
        TransferInfoRequest request = new TransferInfoRequest();
        request.setPartner_trade_no("Test00000002");
        TransferInfoResponse response = transferAPI.getTransferInfo(request);
        System.out.println(response);
    }

    /**
     * 测试查询红包记录
     */
    @Test
    public void testRedPackInfo() {
        RedPackInfoRequest request = new RedPackInfoRequest();
        request.setMch_billno("TestR0000002");
        RedPackInfoResponse response = redPackAPI.getRedPackInfo(request);
        List<RedPackInfoResponse.HbInfo> list = response.getHbInfoList();
        System.out.println(list.size());
    }

}
