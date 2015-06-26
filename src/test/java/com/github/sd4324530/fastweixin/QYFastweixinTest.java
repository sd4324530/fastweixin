package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.company.api.QYAgentAPI;
import com.github.sd4324530.fastweixin.company.api.QYDepartmentAPI;
import com.github.sd4324530.fastweixin.company.api.QYMessageAPI;
import com.github.sd4324530.fastweixin.company.api.QYUserAPI;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.entity.QYAgent;
import com.github.sd4324530.fastweixin.company.api.entity.QYDepartment;
import com.github.sd4324530.fastweixin.company.api.entity.QYUser;
import com.github.sd4324530.fastweixin.company.api.enums.QYResultType;
import com.github.sd4324530.fastweixin.company.api.response.*;
import com.github.sd4324530.fastweixin.company.message.QYTextMsg;
import org.junit.Before;
import org.junit.Test;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class QYFastweixinTest {

    private QYAPIConfig config;

    @Before
    public void initConfig(){
        String corpId = "wx7e0a6276e0a2e235";
        String corpSecret = "36CdZB1Y4cGPEm1NRwASULdwxK7l_F-lUk9YWOc3kbG2yN-0EsC7Q0EnbriDluz0";
        config = new QYAPIConfig(corpId, corpSecret);
    }

    @Test
    public void getDepartmentList(){
        QYDepartmentAPI departmentAPI = new QYDepartmentAPI(config);
        GetDepartmentListResponse response = departmentAPI.getList(null);
        for(QYDepartment department : response.getDepartments()){
            System.out.println(department.toString());
        }
    }

//    @Test
    public void createDepartment(){
        QYDepartmentAPI departmentAPI = new QYDepartmentAPI(config);
        QYDepartment department = new QYDepartment("API创建部门", 2, 1);
        CreateDepartmentResponse response = departmentAPI.create(department);
        System.out.println(response.toString());
    }

//    @Test
    public void updateDepartment(){
        QYDepartmentAPI departmentAPI = new QYDepartmentAPI(config);
        QYDepartment department = new QYDepartment(3, "API更新部门", 2, 1);
        QYResultType resultType = departmentAPI.update(department);
        System.out.println(resultType.toString());
        GetDepartmentListResponse response = departmentAPI.getList(null);
        for(QYDepartment department2 : response.getDepartments()){
            System.out.println(department2.toString());
        }
    }

//    @Test
    public void deleteDepartment(){
        QYDepartmentAPI departmentAPI = new QYDepartmentAPI(config);
        QYResultType resultType = departmentAPI.delete(3);
        System.out.println(resultType.toString());
    }

//    @Test
    public void createUser(){
        QYUserAPI userAPI = new QYUserAPI(config);
        QYUser user = new QYUser("ceshi", "测试1", new Integer[]{2}, "主管", "18912267607", QYUser.Gender.MAN, "891084418@qq.com", "", null);
        QYResultType resultType = userAPI.create(user);
        System.out.println(resultType.toString());
    }

//    @Test
    public void sendMessage(){
        QYTextMsg qyTextMsg = new QYTextMsg();
        qyTextMsg.setText(new QYTextMsg.Text("测试消息"));
        qyTextMsg.setToUser("@all");
        qyTextMsg.setAgentId("1");
        QYMessageAPI messageAPI = new QYMessageAPI(config);
        GetQYSendMessageResponse response = messageAPI.send(qyTextMsg);
    }

//    @Test
    public void getAllAgent(){
        QYAgentAPI agentAPI = new QYAgentAPI(config);
        GetQYAgentListResponse response = agentAPI.getAll();
        for(QYAgent agent : response.getAgentList()){
            System.out.println(agent.toString());
        }
    }

//    @Test
    public void getAgentInfo(){
        QYAgentAPI agentAPI = new QYAgentAPI(config);
        GetQYAgentInfoResponse response = agentAPI.getInfo("1");
        System.out.println(response.getQyAgent().toString());
    }

    public void createAgent(){
        
    }
}
