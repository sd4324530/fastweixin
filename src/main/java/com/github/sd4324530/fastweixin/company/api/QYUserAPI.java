package com.github.sd4324530.fastweixin.company.api;

import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.entity.QYUser;
import com.github.sd4324530.fastweixin.company.api.enums.QYResultType;
import com.github.sd4324530.fastweixin.company.api.response.GetQYUserInfo4DepartmentResponse;
import com.github.sd4324530.fastweixin.company.api.response.GetQYUserInfoResponse;
import com.github.sd4324530.fastweixin.company.api.response.GetQYUserInviteResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
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
     * @param user 用户
     * @return 创建结果
     */
    public QYResultType create(QYUser user){
        BeanUtil.requireNonNull(user, "user is null");
        String url = BASE_API_URL + "cgi-bin/user/create?access_token=#";
        BaseResponse response = executePost(url, user.toJsonString());
        return QYResultType.get(response.getErrcode());
    }

    /**
     * 更新用户信息
     * @param user 用户
     * @return 更新结果
     */
    public QYResultType update(QYUser user){
        BeanUtil.requireNonNull(user, "user is null");
        String url = BASE_API_URL + "cgi-bin/user/update?access_token=#";
        BaseResponse response = executePost(url, user.toJsonString());
        return QYResultType.get(response.getErrcode());
    }

    /**
     * 删除用户
     * @param userId 用户ID
     * @return 删除结果
     */
    public QYResultType delete(String userId){
        BeanUtil.requireNonNull(userId, "userId is null");
        String url = BASE_API_URL + "cgi-bin/user/delete?access_token=#&userid=" + userId;
        BaseResponse response = executeGet(url);
        return QYResultType.get(response.getErrcode());
    }

    /**
     * 批量删除用户
     * @param userIds 要删除的用户ID数组
     * @return 删除结果
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
     * @param userId 用户ID
     * @return 用户信息
     */
    public GetQYUserInfoResponse get(String userId){
        BeanUtil.requireNonNull(userId, "userId is null");
        GetQYUserInfoResponse response;
        String url = BASE_API_URL + "cgi-bin/user/get?access_token=#&userid=" + userId;
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, GetQYUserInfoResponse.class);
        return response;
    }

    /**
     * 通过部门列表获取部门成员摘要。仅包含userid与name
     * @param departmentId 部门ID
     * @param isLoop 是否递归获取子部门下面的成员
     * @param status 0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加，未填写则默认为4
     * @return 部门成员
     */
    public GetQYUserInfo4DepartmentResponse simpleList(Integer departmentId, boolean isLoop, Integer status){
        GetQYUserInfo4DepartmentResponse response;
        String url = BASE_API_URL + "cgi-bin/user/simplelist?access_token=#&department_id=" + departmentId + "&fetch_child=" + (isLoop? 0 : 1) + "&status=" + status;
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, GetQYUserInfo4DepartmentResponse.class);
        return response;
    }

    /**
     * 通过部门列表获取部门成员信息
     * @param departmentId 部门ID
     * @param isLoop 是否递归获取子部门下面的成员
     * @param status 0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加，未填写则默认为4
     * @return 部门成员详情信息
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
     * @param userid 用户ID
     * @return 邀请结果
     */
    public GetQYUserInviteResponse invite(String userid){
        BeanUtil.requireNonNull(userid, "userid is null");
        GetQYUserInviteResponse response;
        String url = BASE_API_URL + "cgi-bin/invite/send?access_token=#";
        final Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userid);
        BaseResponse r = executePost(url, JSONUtil.toJson(params));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtil.toBean(resultJson, GetQYUserInviteResponse.class);
        return response;
    }
}
