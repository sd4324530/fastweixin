package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.company.api.DepartmentAPI;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.entity.Department;
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
        config = new QYAPIConfig(corpId, corpSecret, true);
        System.out.println(config.getAccessToken());
        System.out.println(config.getJsApiTicket());
    }

    @Test
    public void getDepartmentList(){
        DepartmentAPI departmentAPI = new DepartmentAPI(config);
        GetDepartmentListResponse response = departmentAPI.getList(null);
        for(Department department : response.getDepartments()){
            System.out.println(department.toString());
        }
    }

    @Test
    public void createDepartment(){
        DepartmentAPI departmentAPI = new DepartmentAPI(config);
        Department department = new Department("API创建部门", 2, 1);
        CreateDepartmentResponse response = departmentAPI.createDepartment(department);
        System.out.println(response.toString());
    }

    @Test
    public void updateDepartment(){
        DepartmentAPI departmentAPI = new DepartmentAPI(config);
        Department department = new Department(3, "API更新部门", 2, 1);
        ResultType resultType = departmentAPI.updateDepartment(department);
        System.out.println(resultType.toString());
        GetDepartmentListResponse response = departmentAPI.getList(null);
        for(Department department2 : response.getDepartments()){
            System.out.println(department2.toString());
        }
    }

    @Test
    public void deleteDepartment(){
        DepartmentAPI departmentAPI = new DepartmentAPI(config);
        ResultType resultType = departmentAPI.deleteDepartment(3);
        System.out.println(resultType.toString());
    }
}
