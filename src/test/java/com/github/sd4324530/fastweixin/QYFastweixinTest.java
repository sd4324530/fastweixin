package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.company.api.QYDepartmentAPI;
import com.github.sd4324530.fastweixin.company.api.QYUserAPI;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.entity.Department;
import com.github.sd4324530.fastweixin.company.api.entity.User;
import com.github.sd4324530.fastweixin.company.api.response.CreateDepartmentResponse;
import com.github.sd4324530.fastweixin.company.api.response.GetDepartmentListResponse;
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
        for(Department department : response.getDepartments()){
            System.out.println(department.toString());
        }
    }

//    @Test
    public void createDepartment(){
        QYDepartmentAPI departmentAPI = new QYDepartmentAPI(config);
        Department department = new Department("API创建部门", 2, 1);
        CreateDepartmentResponse response = departmentAPI.create(department);
        System.out.println(response.toString());
    }

//    @Test
    public void updateDepartment(){
        QYDepartmentAPI departmentAPI = new QYDepartmentAPI(config);
        Department department = new Department(3, "API更新部门", 2, 1);
        ResultType resultType = departmentAPI.update(department);
        System.out.println(resultType.toString());
        GetDepartmentListResponse response = departmentAPI.getList(null);
        for(Department department2 : response.getDepartments()){
            System.out.println(department2.toString());
        }
    }

//    @Test
    public void deleteDepartment(){
        QYDepartmentAPI departmentAPI = new QYDepartmentAPI(config);
        ResultType resultType = departmentAPI.delete(3);
        System.out.println(resultType.toString());
    }

    @Test
    public void createUser(){
        QYUserAPI userAPI = new QYUserAPI(config);
        User user = new User("ceshi", "测试1", new Integer[]{2}, "主管", "18912267607", User.Gender.MAN, "891084418@qq.com", "", null);
        ResultType resultType = userAPI.create(user);
        System.out.println(resultType.toString());
    }
}
