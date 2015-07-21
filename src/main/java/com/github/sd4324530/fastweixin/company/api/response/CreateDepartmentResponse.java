package com.github.sd4324530.fastweixin.company.api.response;

import com.github.sd4324530.fastweixin.api.entity.BaseModel;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class CreateDepartmentResponse extends BaseModel {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreateDepartmentResponse{" +
                "id=" + id +
                '}';
    }
}
