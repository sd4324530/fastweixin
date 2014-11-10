package com.github.sd4324530.fastweixin.api.enums;

/**
 * 微信接口全局返回码枚举
 * @author peiyu
 * @since 1.2
 */
public enum ResultType {
    /**
     * 系统繁忙
     */
    SYSTEM_BUSY(-1),

    /**
     * 请求成功
     */
    SUCCESS(0),

    /**
     * 获取access_token时AppSecret错误，或者access_token无效
     */
    APP_SECRET_ERROR(40001),

    /**
     * 不合法的凭证类型
     */
    ILLEGAL_TOKEN_TYPE(40002),

    /**
     * 不合法的OpenID
     */
    ILLEGAL_OPEN_ID(40003),

    /**
     * 不合法的媒体文件类型
     */
    ILLEGAL_MEDIA_TYPE(40004),

    /**
     * 不合法的文件类型
     */
    ILLEGAL_FILE_TYPE(40005),

    /**
     * 不合法的文件大小
     */
    ILLEGAL_FILE_SIZE(40006),

    /**
     * 不合法的媒体文件id
     */
    ILLEGAL_MEDIA_ID(40007),

    /**
     * 不合法的消息类型
     */
    ILLEGAL_MESSAGE_TYPE(40008),

    /**
     * 不合法的图片文件大小
     */
    ILLEGAL_PICTURE_SIZE(40009),

    /**
     * 不合法的语音文件大小
     */
    ILLEGAL_VOICE_SIZE(40010),

    /**
     * 不合法的视频文件大小
     */
    ILLEGAL_VIDEO_SIZE(40011),

    /**
     * 不合法的缩略图文件大小
     */
    ILLEGAL_THUMBNAIL_SIZE(40012),

    /**
     * 不合法的APPID
     */
    ILLEGAL_APP_ID(40013),

    /**
     * 不合法的access_token
     */
    ILLEGAL_ACCESS_TOKEN(40014),

    /**
     * 不合法的菜单类型
     */
    ILLEGAL_MENU_TYPE(40015),

    /**
     * 不合法的按钮个数
     */
    ILLEGAL_MENU_NUMBER(40016),

    /**
     * 不合法的按钮名字长度
     */
    ILLEGAL_BUTTON_NAME_LENTH(40018),

    /**
     * 不合法的按钮KEY长度
     */
    ILLEGAL_BUTTON_KEY_LENTH(40019),

    /**
     * 不合法的按钮URL长度
     */
    ILLEGAL_BUTTON_URL_LENTH(40020),

    /**
     * 不合法的菜单版本号
     */
    ILLEGAL_MENU_VERSION(40021),

    /**
     * 不合法的子菜单级数
     */
    ILLEGAL_SUB_MENU_LEVEL(40022),

    /**
     * 不合法的子菜单按钮个数
     */
    ILLEGAL_SUB_MENU_NUMBER(40023),

    /**
     * 不合法的子菜单按钮类型
     */
    ILLEGAL_SUB_MENU_TYPE(40024),

    /**
     * 不合法的子菜单按钮名字长度
     */
    ILLEGAL_SUB_MENU_LENTH(40025),

    /**
     * 不合法的子菜单按钮KEY长度
     */
    ILLEGAL_SUB_MENU_KEY_LENTH(40026),

    /**
     * 不合法的子菜单按钮URL长度
     */
    ILLEGAL_SUB_MENU_URL_LENTH(40027),

    /**
     * 不合法的自定义菜单使用用户
     */
    ILLEGAL_MENU_USER(40028),

    /**
     * 不合法的oauth_code
     */
    ILLEGAL_OAUTH_CODE(40029),

    /**
     * 不合法的refresh_token
     */
    ILLEGAL_REFRESH_TOKEN(40030),

    /**
     * 不合法的openid列表
     */
    ILLEGAL_OPENID_LIST(40031),

    /**
     * 不合法的openid列表长度
     */
    ILLEGAL_OPENID_LIST_LENTH(40032),

    /**
     * 不合法的请求字符
     */
    ILLEGAL_REQUEST_STRING(40033),

    /**
     * 不合法的参数
     */
    ILLEGAL_PARAM(40035),

    /**
     * 不合法的请求格式
     */
    ILLEGAL_REQUEST_TYPE(40038),

    /**
     * 不合法的URL长度
     */
    ILLEGAL_URL_LENTH(40039),

    /**
     * 不合法的分组id
     */
    ILLEGAL_GROUP_ID(40050),

    /**
     * 分组名字不合法
     */
    ILLEGAL_GROUP_NAME(40051),

    /**
     * 缺少access_token参数
     */
    NO_ACCESS_TOKEN(41001),

    /**
     * 缺少appid参数
     */
    NO_APPID(41002),

    /**
     * 缺少refresh_token参数
     */
    NO_REFRESH_TOKEN(41003),

    /**
     * 缺少secret参数
     */
    NO_SECRET(41004),

    /**
     * 缺少多媒体文件数据
     */
    NO_MEDIA_DATA(41005),

    /**
     * 缺少media_id参数
     */
    NO_MEDIA_ID(41006),

    /**
     * 缺少子菜单数据
     */
    NO_SUB_MENU_DATA(41007),

    /**
     * 缺少oauth code
     */
    NO_OAUTH_CODE(41008),

    /**
     * 缺少openid
     */
    NO_OPEN_ID(41009),

    /**
     * access_token超时
     */
    ACCESS_TOKEN_TIMEOUT(42001),

    /**
     * refresh_token超时
     */
    REFRESH_TOKEN_TIMEOUT(42002),

    /**
     * oauth_code超时
     */
    OAUTH_CODE_TIMEOUT(42003),

    /**
     * 需要GET请求
     */
    NEED_REQUEST_GET(43001),

    /**
     * 需要POST请求
     */
    NEED_REQUEST_POST(43002),

    /**
     * 需要HTTPS请求
     */
    NEED_REQUEST_HTTPS(43003),

    /**
     * 需要接收者关注
     */
    NEED_USER_FOLLOW(43004),

    /**
     * 需要好友关系
     */
    NEED_FRIEND(43005),

    /**
     * 多媒体文件为空
     */
    MEDIA_FILE_IS_NULL(44001),

    /**
     * POST的数据包为空
     */
    POST_DATA_IS_NULL(44002),

    /**
     * 图文消息内容为空
     */
    NEWS_MESSAGE_IS_NULL(44003),

    /**
     * 文本消息内容为空
     */
    TEXT_MESSAGE_IS_NULL(44004),

    /**
     * 多媒体文件大小超过限制
     */
    MEDIA_DATA_OVER_LIMIT(45001),

    /**
     * 消息内容超过限制
     */
    MESSAGE_CONTENT_OVER_LIMIT(45002),

    /**
     * 标题字段超过限制
     */
    TITLE_OVER_LIMIT(45003),

    /**
     * 描述字段超过限制
     */
    DESCRIPTION_OVER_LIMIT(45004),

    /**
     * 链接字段超过限制
     */
    LINK_OVER_LIMIT(45005),

    /**
     * 图片链接字段超过限制
     */
    PICTURE_LINK_OVER_LIMIT(45006),

    /**
     * 语音播放时间超过限制
     */
    VOICE_TIME_OVER_LIMIT(45007),

    /**
     * 图文消息超过限制
     */
    NEWS_MESSAGE_OVER_LIMIT(45008),

    /**
     * 接口调用超过限制
     */
    INTERFACE_OVER_LIMIT(45009),

    /**
     * 创建菜单个数超过限制
     */
    MENU_OVER_LIMIT(45010),

    /**
     * 回复时间超过限制
     */
    REVIEW_TIME_OVER_LIMIT(45015),

    /**
     * 系统分组，不允许修改
     */
    NO_MODIFY_SYSTEM_GROUP(45016),

    /**
     * 分组名字过长
     */
    GROUP_NAME_TOO_LONG(45017),

    /**
     * 分组数量超过上限
     */
    GROUP_COUNT_TOO_MANY(45018),

    /**
     * 不存在媒体数据
     */
    NOT_EXIST_MEDIA_DATA(46001),

    /**
     * 不存在的菜单版本
     */
    NOT_EXIST_MENU_VERSION(46002),

    /**
     * 不存在的菜单数据
     */
    NOT_EXIST_MENU_DATA(46003),

    /**
     * 不存在的用户
     */
    NOT_EXIST_USER(46004),

    /**
     * 解析JSON/XML内容错误
     */
    JSON_OR_XML_ERROR(47001),

    /**
     * api功能未授权
     */
    API_NOT_ALLOW_CALL(48001),

    /**
     * 用户未授权该api
     */
    USER_NOT_ALLOW_API(50001);

    Integer code;

    private ResultType(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code.toString();
    }
}
