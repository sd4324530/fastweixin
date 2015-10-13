package com.github.sd4324530.fastweixin.company.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
public class QYDepartment extends BaseModel{

    @JSONField(name = "id")
    private Integer id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "parentid")
    private Integer parentId;
    @JSONField(name = "order")
    private Integer order;

    public QYDepartment() {
    }

    public QYDepartment(String name, Integer parentId, Integer order) {
        this.name = name;
        this.parentId = (parentId == null || parentId < 1) ? 1 : parentId;
        this.order = order;
    }

    public QYDepartment(Integer id, String name, Integer parentId, Integer order) {
        this.id = id;
        this.name = name;
        this.parentId = (parentId == null || parentId < 1) ? 1 : parentId;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", order=" + order +
                '}';
    }
}
