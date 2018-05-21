package com.github.sd4324530.fastweixin.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.api.entity.BaseModel;

/**
 * 获取已添加至帐号下所有模板
 * @author wuwentao
 */
public class PrivateTemplate extends BaseModel {
	
    /**
	 * 模板ID
	 */
	@JSONField(name="template_id")
	private String templateId;
	
	/**
	 * 模板标题
	 */
	private String title;
	
	/**
	 * 模板所属行业的一级行业
	 */
	@JSONField(name="primary_industry")
	private String primaryIndustry;
	
	/**
	 * 模板所属行业的二级行业
	 */
	@JSONField(name="deputy_industry")
	private String deputyIndustry;
	
	/**
	 * 模板内容
	 */
	private String content;
	
	/**
	 * 模板示例
	 */
	private String example;
	
	/**
	 * 模板备注
	 */
	private String remark;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrimaryIndustry() {
		return primaryIndustry;
	}

	public void setPrimaryIndustry(String primaryIndustry) {
		this.primaryIndustry = primaryIndustry;
	}

	public String getDeputyIndustry() {
		return deputyIndustry;
	}

	public void setDeputyIndustry(String deputyIndustry) {
		this.deputyIndustry = deputyIndustry;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
