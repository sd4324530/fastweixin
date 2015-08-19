package com.github.sd4324530.fastweixin.company.api;

import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.entity.QYUser;
import com.github.sd4324530.fastweixin.company.api.enums.QYResultType;
import com.github.sd4324530.fastweixin.company.api.response.GetQYUserInfo4DepartmentResponse;
import com.github.sd4324530.fastweixin.company.api.response.GetQYUserInfoResponse;
import com.github.sd4324530.fastweixin.company.api.response.GetQYUserInviteResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class QYUserAPI extends QYBaseAPI {


    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    public QYUserAPI(QYAPIConfig config) {
        super(config);
    }

    /**
     * 创建一个新用户
     * @param user
     * @return
     */
    public QYResultType create(QYUser user){
        String url = BASE_API_URL + "cgi-bin/user/create?access_token=#";
        BaseResponse response = executePost(url, JSONUtil.toJson(user));
        return QYResultType.get(response.getErrcode());
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public QYResultType update(QYUser user){
        String url = BASE_API_URL + "cgi-bin/user/update?access_token=#";
        BaseResponse response = executePost(url, JSONUtil.toJson(user));
        return QYResultType.get(response.getErrcode());
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public QYResultType delete(String userId){
        String url = BASE_API_URL + "cgi-bin/user/delete?access_token=#&userid=" + userId;
        BaseResponse response = executeGet(url);
        return QYResultType.get(response.getErrcode());
    }

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    public QYResultType batchdelete(String[] userIds){
        String url = BASE_API_URL + "cgi-bin/user/batchdelete?access_token=#";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("useridlist", userIds);
        BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return QYResultType.get(response.getErrcode());
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public GetQYUserInfoResponse get(String userId){
        GetQYUserInfoResponse response;
        String url = BASE_API_URL + "cgi-bin/user/get?access_token=#&userid=" + userId;
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, GetQYUserInfoResponse.class);
        return response;
    }

    /**
     * 通过部门列表获取部门成员摘要。仅包含userid与name
     * @param departmentId
     * @param isLoop
     * @param status
     * @return
     */
    public GetQYUserInfo4DepartmentResponse simpleList(Integer departmentId, boolean isLoop, Integer status ){
        GetQYUserInfo4DepartmentResponse response;
        String url = BASE_API_URL + "cgi-bin/user/simplelist?access_token=#&department_id=" + departmentId + "&fetch_child=" + (isLoop? 0 : 1) + "&status=" + status;
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, GetQYUserInfo4DepartmentResponse.class);
        return response;
    }

    /**
     * 通过部门列表获取部门成员信息
     * @param departmentId
     * @param isLoop
     * @param status
     * @return
     */
    public GetQYUserInfo4DepartmentResponse getList(Integer departmentId, boolean isLoop, Integer status){
        GetQYUserInfo4DepartmentResponse response;
        String url = BASE_API_URL + "cgi-bin/user/list?access_token=#&department_id=" + departmentId + "&fetch_child=" + (isLoop? 0 : 1) + "&status=" + status;
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, GetQYUserInfo4DepartmentResponse.class);
        return response;
    }

    /**
     * 邀请成员关注。返回值type为1时表示微信邀请，2为邮件邀请
     * @param userid
     * @return
     */
    public GetQYUserInviteResponse invite(String userid){
        GetQYUserInviteResponse response = null;
        String url = BASE_API_URL + "cgi-bin/invite/send?access_token=#";
        final Map<String, String> params = new HashMap<String, String>();
        BaseResponse r = executePost(url, JSONUtil.toJson(params));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, GetQYUserInviteResponse.class);
        return response;
    }
}
