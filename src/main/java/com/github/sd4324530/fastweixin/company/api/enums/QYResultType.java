package com.github.sd4324530.fastweixin.company.api.enums;

import com.github.sd4324530.fastweixin.util.BeanUtil;

/**
 *
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public enum QYResultType {
    /**
     * 系统繁忙
     */
    SYSTEM_BUSY(-1, "系统繁忙"),
    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),
    /**
     * 获取access_token时Secret错误，或者access_token无效
     */
     APP_SECRET_ERROR(40001, "获取access_token时Secret错误，或者access_token无效"),
    /**
     * 不合法的凭证类型
     */
    ILLEGAL_TOKEN_TYPE(40002, "不合法的凭证类型"),
    /**
     * 不合法的UserID
     */
    ILLEAGL_USER_ID(40003, "不合法的UserID"),
    /**
     * 不合法的媒体文件类型
     */
    ILLEAGL_MEDIA_TYPE(40004, "不合法的媒体文件类型"),
    /**
     * 不合法的文件类型
     */
    ILLEAGL_FILE_TYPE(40005, "不合法的文件类型"),
    /**
     * 不合法的文件大小
     */
    ILLEAGL_FILE_SIZE(40006, "不合法的文件大小"),
    /**
     * 不合法的媒体文件id
     */
    ILLEGAL_MEDIA_ID(40007, "不合法的媒体文件id"),
    /**
     * 不合法的消息类型
     */
    ILLEGAL_MESSAGE_TYPE(40008, "不合法的消息类型"),
    /**
     * 不合法的corpid
     */
    ILLEGAL_PICTURE_SIZE(40013, "不合法的corpid"),
    /**
     * 不合法的access_token
     */
    ILLEGAL_ACCESS_TOKEN(40014, "不合法的access_token"),
    /**
     * 不合法的菜单类型
     */
    ILLEGAL_MENU_TYPE(40015, "不合法的菜单类型"),
    /**
     * 不合法的按钮个数
     */
    ILLEGAL_MENU_NUMBER(40016, "不合法的按钮个数"),
    /**
     * 不合法的按钮类型
     */
    ILLEGAL_BUTTON_TYPE(40017, "不合法的按钮类型"),
    /**
     * 不合法的按钮名字长度
     */
    ILLEGAL_BUTTON_NAME_LANGTH(40018, "不合法的按钮名字长度"),
    /**
     * 不合法的按钮KEY长度
     */
    ILLEGAL_BUTTON_KEY_LENGTH(40019, "不合法的按钮KEY长度"),
    /**
     * 不合法的按钮URL长度
     */
    ILLEGAL_BUTTON_URL_LENGTH(40020, "不合法的按钮URL长度"),
    /**
     * 不合法的菜单版本号
     */
    ILLEGAL_MENU_VERSION(40021, "不合法的菜单版本号"),
    /**
     * 不合法的子菜单级数
     */
    ILLEGAL_SUB_MENU_LEVEL(40022, "不合法的子菜单级数"),
    /**
     * 不合法的子菜单按钮个数
     */
    ILLEGAL_SUB_MENU_NUMBER(40023, "不合法的子菜单按钮个数"),
    /**
     * 不合法的子菜单按钮类型
     */
    ILLEGAL_SUB_MENU_TYPE(40024, "不合法的子菜单按钮类型"),
    /**
     * 不合法的子菜单按钮名字长度
     */
    ILLEGAL_SUB_MENU_LENTH(40025, "不合法的子菜单按钮名字长度"),
    /**
     * 不合法的子菜单按钮KEY长度
     */
    ILLEGAL_SUB_MENU_KEY_LENTH(40026, "不合法的子菜单按钮KEY长度"),
    /**
     * 不合法的子菜单按钮URL长度
     */
    ILLEGAL_SUB_MENU_URL_LENTH(40027, "不合法的子菜单按钮URL长度"),
    /**
     * 不合法的自定义菜单使用成员
     */
    ILLEGAL_MENU_USER(40028, "不合法的自定义菜单使用成员"),
    /**
     * 不合法的oauth_code
     */
    ILLEGAL_OAUTH_CODE(40029, "不合法的oauth_code"),
    /**
     * 不合法的UserID列表
     */
    ILLEGAL_USERID_LIST(40031, "不合法的UserID列表"),
    /**
     * 不合法的UserID列表长度
     */
    ILLEGAL_USERID_LIST_LENGTH(40032, "不合法的UserID列表长度"),
    /**
     * 不合法的请求字符，不能包含\\uxxxx格式的字符
     */
    ILLEGAL_REQUEST_STRING(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符"),
    /**
     * 不合法的参数
     */
    ILLEGAL_PARAM(40035, "不合法的参数"),
    /**
     * 不合法的请求格式
     */
    ILLEGAL_REQUEST_TYPE(40038, "不合法的请求格式"),
    /**
     * 不合法的URL长度
     */
    ILLEGAL_URL_LENGTH(40039, "不合法的URL长度"),
    /**
     * 不合法的插件token
     */
    ILLEGAL_PLUGIN_TOKEN(40040, "不合法的插件token"),
    /**
     * 不合法的插件id
     */
    ILLEGAL_PLUGIN_ID(40041, "不合法的插件id"),
    /**
     * 不合法的插件会话
     */
    ILLEGAL_PLUGIN_SESSION(40042, "不合法的插件会话"),
    /**
     * url中包含不合法domain
     */
    ILLEGAL_URL_DOMAIN(40048, "url中包含不合法domain"),
    /**
     * 不合法的子菜单url域名
     */
    ILLEGAL_SUB_MENU_URL_DOMAIN(40054, "不合法的子菜单url域名"),
    /**
     * 不合法的按钮url域名
     */
    ILLEGAL_BUTTON_URL_DOMAIN(40055, "不合法的按钮url域名"),
    /**
     * 不合法的agentid
     */
    ILLEGAL_AGENT_ID(40056, "不合法的agentid"),
    /**
     * 不合法的callbackurl
     */
    ILLEGAL_CALLBACK_URL(40057, "不合法的callbackurl"),
    /**
     * 不合法的红包参数
     */
    ILLEGAL_RED_PICKET(40058, "不合法的红包参数"),
    /**
     * 不合法的上报地理位置标志位
     */
    ILLEGAL_POSITION(40059, "不合法的上报地理位置标志位"),
    /**
     * 设置上报地理位置标志位时没有设置callbackurl
     */
    ILLEGAL_POSITION_NO_CALLBACK_URL(40060, "设置上报地理位置标志位时没有设置callbackurl"),
    /**
     * 设置应用头像失败
     */
    ILLEGAL_APP_LOGO(40061, "设置应用头像失败"),
    /**
     * 不合法的应用模式
     */
    ILLEGAL_APP_MODE(40062, "不合法的应用模式"),
    /**
     * 红包参数为空
     */
    ILLEGAL_RED_PICKET_NO_PARAM(40063, "红包参数为空"),
    /**
     * 管理组名字已存在
     */
    ILLEGAL_HAS_OPERATOR_GROUP_NAME(40064, "管理组名字已存在"),
    /**
     * 不合法的管理组名字长度
     */
    ILLEGAL_OPERATOR_GROUP_LENGTH(40065, "不合法的管理组名字长度"),
    /**
     * 不合法的部门列表
     */
    ILLEGAL_DEPARTMENT_LIST(40066, "不合法的部门列表"),
    /**
     * 标题长度不合法
     */
    ILLEGAL_TITLE_LENGTH(40067, "标题长度不合法"),
    /**
     * 不合法的标签ID
     */
    ILLEGAL_TAG_ID(40068, "不合法的标签ID"),
    /**
     * 不合法的标签ID列表
     */
    ILLEGAL_TAG_ID_LIST(40069, "不合法的标签ID列表"),
    /**
     * 列表中所有标签（成员）ID都不合法
     */
    ILLEGAL_ALL_TAG_ID_LIST(40070, "列表中所有标签（成员）ID都不合法"),
    /**
     * 不合法的标签名字，标签名字已经存在
     */
    ILLEGAL_HAS_TAG_NAME(40071, "不合法的标签名字，标签名字已经存在"),
    /**
     * 不合法的标签名字长度
     */
    ILLEGAL_TAG_LENGTH(40072, "不合法的标签名字长度"),
    /**
     * 不合法的openid
     */
    ILLEGAL_OPENID(40073, "不合法的openid"),
    /**
     * news消息不支持指定为高保密消息
     */
    ILLEGAL_NEWS_NO_SAFE(40074, "news消息不支持指定为高保密消息"),
    /**
     * 不合法的预授权码
     */
    ILLEGAL_AUTH_CODE(40077, "不合法的预授权码"),
    /**
     * 不合法的临时授权码
     */
    ILLEGAL_AUTH_TEMP_CODE(40078, "不合法的临时授权码"),
    /**
     * 不合法的授权信息
     */
    ILLEGAL_AUTH_INFO(40079, "不合法的授权信息"),
    /**
     * 不合法的suitesecret
     */
    ILLEGAL_SUITE_SECRET(40080, "不合法的suitesecret"),
    /**
     * 不合法的suitetoken
     */
    ILLEGAL_SUITE_TOKEN(40082, "不合法的suitetoken"),
    /**
     * 不合法的suiteid
     */
    ILLEGAL_SUITE_ID(40083, "不合法的suiteid"),
    /**
     * 不合法的永久授权码
     */
    ILLEGAL_PERMANENT_CODE(40084, "不合法的永久授权码"),
    /**
     * 不合法的suiteticket
     */
    ILLEGAL_SUITE_TICKET(40085, "不合法的suiteticket"),
    /**
     * 不合法的第三方应用appid
     */
    ILLEGAL_APPID(40086, "不合法的第三方应用appid"),
    /**
     * 缺少access_token参数
     */
    NO_ACCESS_TOKEN(41001, "缺少access_token参数"),
    /**
     * 缺少corpid参数
     */
    NO_CORPID(41002, "缺少corpid参数"),
    /**
     * 缺少refresh_token参数
     */
    NO_REFRESH_TOKEMN(41003, "缺少refresh_token参数"),
    /**
     * 缺少secret参数
     */
    NO_SECRET(41004, "缺少secret参数"),
    /**
     * 缺少多媒体文件数据
     */
    NO_MEDIA_DATA(41005, "缺少多媒体文件数据"),
    /**
     * 缺少media_id参数
     */
    NO_MEDIA_ID(41006, "缺少media_id参数"),
    /**
     * 缺少子菜单数据
     */
    NO_SUM_MENU_DATA(41007, "缺少子菜单数据"),
    /**
     * 缺少oauth code
     */
    NO_OAUTH_CODE(41008, "缺少oauth code"),
    /**
     * 缺少UserID
     */
    NO_USER_ID(41009, "缺少UserID"),
    /**
     * 缺少url
     */
    NO_URL(41010, "缺少url"),
    /**
     * 缺少agentid
     */
    NO_AGENT_ID(41011, "缺少agentid"),
    /**
     * 缺少应用头像mediaid
     */
    NO_APP_LOGO_MEDIA_ID(41012, "缺少应用头像mediaid"),
    /**
     * 缺少应用名字
     */
    NO_APP_NAME(41013, "缺少应用名字"),
    /**
     * 缺少应用描述
     */
    NO_APP_DESCRIPTION(41014, "缺少应用描述"),
    /**
     * 缺少Content
     */
    NO_CONTENT(41015, "缺少Content"),
    /**
     * 缺少标题
     */
    NO_TITLE(41016, "缺少标题"),
    /**
     * 缺少标签ID
     */
    NO_TAG_ID(41017, "缺少标签ID"),
    /**
     * 缺少标签名字
     */
    NO_TAG_NAME(41018, "缺少标签名字"),
    /**
     * 缺少suiteid
     */
    NO_SUITE_ID(41021, "缺少suiteid"),
    /**
     * 缺少suitetoken
     */
    NO_SUITE_TOKEN(41022, "缺少suitetoken"),
    /**
     * 缺少suiteticket
     */
    NO_SUITE_TICKET(41023, "缺少suiteticket"),
    /**
     * 缺少suitesecret
     */
    NO_SUITE_SECRET(41024, "缺少suitesecret"),
    /**
     * 缺少永久授权码
     */
    NO_PERMANENT_CODE(41025, "缺少永久授权码"),
    /**
     * access_token超时
     */
    ACCESS_TOKEN_TIMEOUT(42001, "access_token超时"),
    /**
     * refresh_token超时
     */
    REFRESH_TOKEN_TIMEOUT(42002, "refresh_token超时"),
    /**
     * oauth_code超时
     */
    OAUTH_CODE_TIMEOUT(42003, "oauth_code超时"),
    /**
     * 插件token超时
     */
    PLUGIN_TOKEN_TIMEOUT(42004, "插件token超时"),
    /**
     * 预授权码失效
     */
    AUTH_CODE_FAILD(42007, "预授权码失效"),
    /**
     * 临时授权码失效
     */
    AUTH_TEMP_CODE_FAILD(42008, "临时授权码失效"),
    /**
     * suitetoken失效
     */
    SUITE_TOKEN_FAILD(42009, "suitetoken失效"),
    /**
     * 需要GET请求
     */
    NEED_REQUEST_GET(43001, "需要GET请求"),
    /**
     * 需要POST请求
     */
    NEED_REQUEST_POST(43002, "需要POST请求"),
    /**
     * 需要HTTPS
     */
    NEED_REQUEST_HTTPS(43003, "需要HTTPS"),
    /**
     * 需要成员已关注
     */
    NEED_MEMBER_FOLLOW(43004, "需要成员已关注"),
    /**
     * 需要好友关系
     */
    NEED_FRIEND(43005, "需要好友关系"),
    /**
     * 需要订阅
     */
    NEED_SUBSCRIBE(43006, "需要订阅"),
    /**
     * 需要授权
     */
    NEED_AUTH(43007, "需要授权"),
    /**
     * 需要支付授权
     */
    NEED_PAY_AUTH(43008, "需要支付授权"),
    /**
     * 需要处于回调模式
     */
    NEED_IN_CALLBACK(43010, "需要处于回调模式"),
    /**
     * 需要企业授权
     */
    NEED_AUTH_COMPANY(43011, "需要企业授权"),
    /**
     * 多媒体文件为空
     */
    MEDIA_FILE_IS_NULL(44001, "多媒体文件为空"),
    /**
     * POST的数据包为空
     */
    POST_DATA_IS_NULL(44002, "POST的数据包为空"),
    /**
     * 图文消息内容为空
     */
    NEWS_MESSAGE_IS_NULL(44003, "图文消息内容为空"),
    /**
     * 文本消息内容为空
     */
    TEXT_MESSAGE_IS_NULL(44004, "文本消息内容为空"),
    /**
     * 多媒体文件大小超过限制
     */
    MEDIA_DATA_OVER_LIMIT(45001, "多媒体文件大小超过限制"),
    /**
     * 消息内容超过限制
     */
    MESSAGE_CONTENT_OVER_LIMIT(45002, "消息内容超过限制"),
    /**
     * 标题字段超过限制
     */
    TITLE_OVER_LIMIT(45003, "标题字段超过限制"),
    /**
     * 描述字段超过限制
     */
    DESCRIPTION_OVER_LIMIT(45004, "描述字段超过限制"),
    /**
     * 链接字段超过限制
     */
    LINK_OVER_LIMIT(45005, "链接字段超过限制"),
    /**
     * 图片链接字段超过限制
     */
    PICTURE_LINK_OVER_LIMIT(45006, "图片链接字段超过限制"),
    /**
     * 语音播放时间超过限制
     */
    VOICE_TIME_OVER_LIMIT(45007, "语音播放时间超过限制"),
    /**
     * 图文消息的文章数量不能超过10条
     */
    NEWS_MESSAGE_OVER_LIMIT(45008, "图文消息的文章数量不能超过10条"),
    /**
     * 接口调用超过限制
     */
    INTERFACE_OVER_LIMIT(45009, "接口调用超过限制"),
    /**
     * 创建菜单个数超过限制
     */
    MENU_OVER_LIMIT(45010, "创建菜单个数超过限制"),
    /**
     * 回复时间超过限制
     */
    REVIEW_TIME_OVER_LIMIT(45015, "回复时间超过限制"),
    /**
     * 系统分组，不允许修改
     */
    NO_MODIFY_SYSTEM_GROUP(45016, "系统分组，不允许修改"),
    /**
     * 分组名字过长
     */
    GROUP_NAME_TOO_LONG(45017, "分组名字过长"),
    /**
     * 分组数量超过上限
     */
    GROUP_COUNT_TOO_MANY(45018, "分组数量超过上限"),
    /**
     * 账号数量超过上限
     */
    ACCOUNT_COUNT_TOO_MANY(45024, "账号数量超过上限"),
    /**
     * mpnews每天只能发送100次
     */
    MP_NEWS_OVER_LIMIT(45027, "mpnews每天只能发送100次"),
    /**
     * 不存在媒体数据
     */
    NOT_EXIST_MEDIA_DATA(46001, "不存在媒体数据"),
    /**
     * 不存在的菜单版本
     */
    NOT_EXIST_MENU_VERSION(46002, "不存在的菜单版本"),
    /**
     * 不存在的菜单数据
     */
    NOT_EXIST_MENU_DATA(46003, "不存在的菜单数据"),
    /**
     * 不存在的成员
     */
    NOT_EXIST_MEMBER(46004, "不存在的成员"),
    /**
     * 解析JSON/XML内容错误
     */
    JSON_OR_XML_ERROR(47001, "解析JSON/XML内容错误"),
    /**
     * Api未授权
     */
    API_NOT_ALLOW_CALL(48001, "Api未授权"),
    /**
     * Api禁用
     */
    API_DISABLE(48002, "Api禁用"),
    /**
     * suitetoken无效
     */
    SUITE_TOKEN_NOT_ALLOW(48003, "suitetoken无效"),
    /**
     * 授权关系无效
     */
    AUTH_RELATION_NOT_ALLOW(48004, "授权关系无效"),
    /**
     * redirect_uri未授权
     */
    AUTH_REDIRECT_URI_NOT_ALLOW(50001, "redirect_uri未授权"),
    /**
     * 成员不在权限范围
     */
    USER_OUT_OF_AUTH(50002, "成员不在权限范围"),
    /**
     * 应用已停用
     */
    APP_DISABLE(50003, "应用已停用"),
    /**
     * 成员状态不正确，需要成员为企业验证中状态
     */
    NEED_MEMBER_IN_COMPANY_VALID(50004, "成员状态不正确，需要成员为企业验证中状态"),
    /**
     * 企业已禁用
     */
    COMPANY_DISABLE(50005, "企业已禁用"),
    /**
     * 部门长度不符合限制
     */
    LIMIT_DEPARTMENT_LENGTH(60001, "部门长度不符合限制"),
    /**
     * 部门层级深度超过限制
     */
    LIMIT_DEPARTMENT_LEVEL(60002, "部门层级深度超过限制"),
    /**
     * 部门不存在
     */
    NOT_FOUND_DEPARTMENT(60003, "部门不存在"),
    /**
     * 父亲部门不存在
     */
    NOT_FOUND_PARENT_DEPARTMENT(60004, "父亲部门不存在"),
    /**
     * 不允许删除有成员的部门
     */
    DELETE_HAS_MEMBER_DEPARTMENT_NOT_ALLOW(60005, "不允许删除有成员的部门"),
    /**
     * 不允许删除有子部门的部门
     */
    DELETE_HAS_CHILD_DEPARTMENT_NOT_ALLOW(60006, "不允许删除有子部门的部门"),
    /**
     * 不允许删除根部门
     */
    DELETE_ROOT_DEPARTMENT(60007, "不允许删除根部门"),
    /**
     * 部门名称已存在
     */
    DEPARTMENT_NAME_EXIST(60008, "部门名称已存在"),
    /**
     * 部门名称含有非法字符
     */
    ILLEGAL_DEPARTMENT_NAME_CHARACTER(60009, "部门名称含有非法字符"),
    /**
     * 部门存在循环关系
     */
    DEPARTMENT_WILL_LOOP(60010, "部门存在循环关系"),
    /**
     * 管理组权限不足，（user/department/agent）无权限
     */
    OPERATOR_AUTH_NOT_ENOUGH(60011, "管理组权限不足，（user/department/agent）无权限"),
    /**
     * 不允许删除默认应用
     */
    DELETE_DEFAULT_APP_NOT_ALLOW(60012, "不允许删除默认应用"),
    /**
     * 不允许关闭应用
     */
    CLOSE_APP_NOT_ALLOW(60013, "不允许关闭应用"),
    /**
     * 不允许开启应用
     */
    OPEN_APP_NOT_ALLOW(60014, "不允许开启应用"),
    /**
     * 不允许修改默认应用可见范围
     */
    UPDATE_DEFAULT_APP_NOT_ALLOW(60015, "不允许修改默认应用可见范围"),
    /**
     * 不允许删除存在成员的标签
     */
    DELETE_TAG_HAS_MEMBER_NOT_ALLOW(60016, "不允许删除存在成员的标签"),
    /**
     * 不允许设置企业
     */
    SET_COMPANY_NOT_ALLOW(60017, "不允许设置企业"),
    /**
     * 不允许设置应用地理位置上报开关
     */
    SET_APP_REPORT_LOCATION_FLAG_NOT_ALLOW(60019, "不允许设置应用地理位置上报开关"),
    /**
     * 访问ip不在白名单之中
     */
    REQUEST_IP_NOT_IN_WHITE_LIST(60020, "访问ip不在白名单之中"),
    /**
     * 应用已授权予第三方，不允许通过分级管理员修改菜单
     */
    OPERATOR_CHANGE_MENU_NOT_ALLOW(60023, "应用已授权予第三方，不允许通过分级管理员修改菜单"),
    /**
     * UserID已存在
     */
    USER_ID_MORE_THAN_ONE(60102, "UserID已存在"),
    /**
     * 手机号码不合法
     */
    ILLEGAL_MOBILE(60103, "手机号码不合法"),
    /**
     * 手机号码已存在
     */
    MOBILE_MORE_THAN_ONE(60104, "手机号码已存在"),
    /**
     * 邮箱不合法
     */
    ILLEGAL_EMAIL(60105, "邮箱不合法"),
    /**
     * 邮箱已存在
     */
    EMAIL_MORE_THAN_ONE(60106, "邮箱已存在"),
    /**
     * 微信号不合法
     */
    ILLEGAL_WECHAT(60107, "微信号不合法"),
    /**
     * 微信号已存在
     */
    WECHAT_MORE_THAN_ONE(60108, "微信号已存在"),
    /**
     * QQ号已存在
     */
    QQ_MORE_THAN_ONE(60109, "QQ号已存在"),
    /**
     * 用户同时归属部门超过20个
     */
    MEMBER_IN_DEPARTMENT_OVER_LIMIT(60110, "用户同时归属部门超过20个"),
    /**
     * UserID不存在
     */
    NOT_HAVE_USER_ID(60111, "UserID不存在"),
    /**
     * 成员姓名不合法
     */
    ILLEGAL_MEMBER_NAME(60112, "成员姓名不合法"),
    /**
     * 身份认证信息（微信号/手机/邮箱）不能同时为空
     */
    IDENTITY_INFO_CAN_NOT_EMPTY(60113, "身份认证信息（微信号/手机/邮箱）不能同时为空"),
    /**
     * 性别不合法
     */
    ILLEGAL_GENDER(60114, "性别不合法"),
    /**
     * 已关注成员微信不能修改
     */
    SUBSCRIBE_MEMBER_INFO_CAN_NOT_CHANGE(60115, "已关注成员微信不能修改"),
    /**
     * 扩展属性已存在
     */
    EXTRA_ATTR_MORE_THAN_ONE(60116, "扩展属性已存在"),
    /**
     * 成员无有效邀请字段（微信，邮箱，手机号）
     */
    MEMBER_HAVE_NO_VALID_FIELD(60118, "成员无有效邀请字段（微信，邮箱，手机号）"),
    /**
     * 成员已关注
     */
    MEMBER_HAS_SUBSCRIBE(60119, "成员已关注"),
    /**
     * 成员已禁用
     */
    MEMBER_DISABLED(60120, "成员已禁用"),
    /**
     * 找不到该成员
     */
    CAN_NOT_FIND_MEMBER(60121, "找不到该成员"),
    /**
     * 邮箱已被外部管理员使用
     */
    EMAIL_USED_BY_OPERATOR(60122, "邮箱已被外部管理员使用"),
    /**
     * 无效的部门id
     */
    UNVALID_DEPARTMENT_ID(60123, "无效的部门id"),
    /**
     * 无效的父部门id
     */
    UNVALID_PARENT_DEPARTMENT_ID(60124, "无效的父部门id"),
    /**
     * 部门名字长度超过限制
     */
    DEPARTMENT_LENGTH_OVER_LIMIT(60125, "部门名字长度超过限制"),
    /***
     * 创建部门失败
     */
    CREATE_DEPARTMENT_FAILED(60126, "创建部门失败"),
    /**
     * 缺少部门id
     */
    LACK_DEPARTMENT_ID(60127, "缺少部门id"),
    /**
     * 字段不合法，可能存在主键冲突或者格式错误
     */
    ILLEGAL_FIELD(60128, "字段不合法，可能存在主键冲突或者格式错误"),
    /**
     * 可信域名没有IPC备案，后续将不能在该域名下正常使用jssdk
     */
    DOMAIN_NEEVER_IPC(80001, "可信域名没有IPC备案，后续将不能在该域名下正常使用jssdk"),
    /**
     * 发送消息或者邀请的参数全部为空或者全部不合法
     */
    PARAMS_ALL_NULL_OR_ILLEGAL(82001, "发送消息或者邀请的参数全部为空或者全部不合法"),
    /**
     * 不合法的PartyID列表长度
     */
    ILLEGAL_PARTY_ID_LENGTH(82002, "不合法的PartyID列表长度"),
    /**
     * 不合法的TagID列表长度
     */
    ILLEGAL_TAG_ID_LENGTH(82003, "不合法的TagID列表长度"),
    /**
     * 微信版本号过低
     */
    WECHAT_VERSION_TOO_LOW(82004, "微信版本号过低");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 结果描述
     */
    private String description;

    QYResultType(Integer code, String description){
        this.code = code;
        this.description = description;
    }

    public static QYResultType get(String code){
        BeanUtil.requireNonNull(code, "code is null");
        QYResultType[] list = values();
        for(QYResultType resultType : list){
            if(code.equals(resultType.getCode().toString())){
                return resultType;
            }
        }
        return null;
    }

    /**
     * 获得结果码
     *
     * @return 结果码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获得结果描述
     *
     * @return 结果描述
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "QYResultType{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
