package com.github.sd4324530.fastweixin.company.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.api.entity.BaseModel;

import java.util.Map;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 *          ====================================================================
 */
public class QYAgent extends BaseModel {

    @JSONField(name = "agentid")
    private String              agentId;
    @JSONField(name = "name")
    private String              name;
    @JSONField(name = "square_logo_url")
    private String              squareLogoUrl;
    @JSONField(name = "round_logo_url")
    private String              roundLogoUrl;
    @JSONField(name = "description")
    private String              description;
    @JSONField(name = "allow_userinfos")
    private Map<String, Object> allowUserInfos;
    @JSONField(name = "allow_partys")
    private Map<String, Object> allowPartys;
    @JSONField(name = "allow_tags")
    private Map<String, Object> allowTags;
    @JSONField(name = "close")
    private Integer             close;
    @JSONField(name = "redirect_domain")
    private String              redirectDomain;
    @JSONField(name = "report_location_flag")
    private Integer             reportLocationFlag;
    @JSONField(name = "isreportuser")
    private Integer             isReportUser;
    @JSONField(name = "isreportenter")
    private Integer             isReportEnter;

    public QYAgent() {
    }

    /**
     * 创建新应用时候需要用到的参数
     *
     * @param agentId            ID
     * @param name               名称
     * @param description        应用详情
     * @param redirectDomain     应用可信域名
     * @param reportLocationFlag 企业应用是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报
     * @param isReportUser       是否接收用户变更通知。0：不接收；1：接收
     * @param isReportEnter      是否上报用户进入应用事件。0：不接收；1：接收
     */
    public QYAgent(String agentId, String name, String description, String redirectDomain, Integer reportLocationFlag, Integer isReportUser, Integer isReportEnter) {
        this.agentId = agentId;
        this.name = name;
        this.description = description;
        this.redirectDomain = redirectDomain;
        this.reportLocationFlag = reportLocationFlag;
        this.isReportEnter = isReportEnter;
        this.isReportUser = isReportUser;
    }

    public QYAgent(String agentId, String name, String squareLogoUrl, String roundLogoUrl, String description, Map<String, Object> allowUserInfos, Map<String, Object> allowPartys, Map<String, Object> allowTags, Integer close, String redirectDomain, Integer reportLocationFlag, Integer isReportUser, Integer isReportEnter) {
        this(agentId, name, description, redirectDomain, reportLocationFlag, isReportEnter, isReportUser);
        this.squareLogoUrl = squareLogoUrl;
        this.roundLogoUrl = roundLogoUrl;
        this.allowUserInfos = allowUserInfos;
        this.allowPartys = allowPartys;
        this.allowTags = allowTags;
        this.close = close;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSquareLogoUrl() {
        return squareLogoUrl;
    }

    public void setSquareLogoUrl(String squareLogoUrl) {
        this.squareLogoUrl = squareLogoUrl;
    }

    public String getRoundLogoUrl() {
        return roundLogoUrl;
    }

    public void setRoundLogoUrl(String roundLogoUrl) {
        this.roundLogoUrl = roundLogoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getAllowUserInfos() {
        return allowUserInfos;
    }

    public void setAllowUserInfos(Map<String, Object> allowUserInfos) {
        this.allowUserInfos = allowUserInfos;
    }

    public Map<String, Object> getAllowPartys() {
        return allowPartys;
    }

    public void setAllowPartys(Map<String, Object> allowPartys) {
        this.allowPartys = allowPartys;
    }

    public Map<String, Object> getAllowTags() {
        return allowTags;
    }

    public void setAllowTags(Map<String, Object> allowTags) {
        this.allowTags = allowTags;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }

    public String getRedirectDomain() {
        return redirectDomain;
    }

    public void setRedirectDomain(String redirectDomain) {
        this.redirectDomain = redirectDomain;
    }

    public Integer getReportLocationFlag() {
        return reportLocationFlag;
    }

    public void setReportLocationFlag(Integer reportLocationFlag) {
        this.reportLocationFlag = reportLocationFlag;
    }

    public Integer getIsReportUser() {
        return isReportUser;
    }

    public void setIsReportUser(Integer isReportUser) {
        this.isReportUser = isReportUser;
    }

    public Integer getIsReportEnter() {
        return isReportEnter;
    }

    public void setIsReportEnter(Integer isReportEnter) {
        this.isReportEnter = isReportEnter;
    }

    @Override
    public String toString() {
        return "QYAgent{" +
                "agentId='" + agentId + '\'' +
                ", name='" + name + '\'' +
                ", squareLogoUrl='" + squareLogoUrl + '\'' +
                ", roundLogoUrl='" + roundLogoUrl + '\'' +
                ", description='" + description + '\'' +
                ", allowUserInfos=" + allowUserInfos +
                ", allowPartys=" + allowPartys +
                ", allowTags=" + allowTags +
                ", close=" + close +
                ", redirectDomain='" + redirectDomain + '\'' +
                ", reportLocationFlag=" + reportLocationFlag +
                ", isReportUser=" + isReportUser +
                ", isReportEnter=" + isReportEnter +
                '}';
    }
}
