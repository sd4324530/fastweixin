package com.github.sd4324530.fastweixin.api.entity;

/**
 * 标签对象
 * @author peiyu
 * @since 1.3.9
 */
public class Tag extends BaseModel {

    private Integer id;

    private String name;

    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
