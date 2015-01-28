package com.github.sd4324530.fastweixin.api;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.*;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据分析API
 *
 * @author peiyu
 */
public class DataCubeAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(DataCubeAPI.class);

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public DataCubeAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 获取用户增减数据，最大跨度为7天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 用户增减数据
     */
    public GetUserSummaryResponse getUserSummary(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUserSummaryResponse response = null;
        String url = BASE_API_URL + "datacube/getusersummary?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUserSummaryResponse.class);
        }
        return response;
    }

    /**
     * 获取累计用户数据，最大跨度为7天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 用户增减数据
     */
    public GetUserCumulateResponse getUserCumulate(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUserCumulateResponse response = null;
        String url = BASE_API_URL + "datacube/getusercumulate?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUserCumulateResponse.class);
        }
        return response;
    }

    /**
     * 获取图文群发每日数据
     * @param day 查询日期
     * @return 图文群发每日数据
     */
    public GetArticleSummaryResponse getArticleSummary(Date day) {
        BeanUtil.requireNonNull(day, "day is null");
        GetArticleSummaryResponse response = null;
        String url = BASE_API_URL + "datacube/getarticlesummary?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(day));
        param.put("end_date", DATE_FORMAT.format(day));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetArticleSummaryResponse.class);
        }
        return response;
    }

    /**
     * 获取图文群发总数据
     * @param day 查询日期
     * @return 图文群发总数据
     */
    public GetArticleTotalResponse getArticleTotal(Date day) {
        BeanUtil.requireNonNull(day, "day is null");
        GetArticleTotalResponse response = null;
        String url = BASE_API_URL + "datacube/getarticletotal?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(day));
        param.put("end_date", DATE_FORMAT.format(day));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetArticleTotalResponse.class);
        }
        return response;
    }

    /**
     * 获取图文统计数据，最大跨度为3天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 图文统计数据
     */
    public GetUserReadResponse getUserRead(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUserReadResponse response = null;
        String url = BASE_API_URL + "datacube/getuserread?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUserReadResponse.class);
        }
        return response;
    }

    /**
     * 获取图文统计分时数据
     * @param day 查询日期
     * @return 图文统计分时数据
     */
    public GetUserReadHourResponse getUserReadHour(Date day) {
        BeanUtil.requireNonNull(day, "day is null");
        GetUserReadHourResponse response = null;
        String url = BASE_API_URL + "datacube/getuserreadhour?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(day));
        param.put("end_date", DATE_FORMAT.format(day));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUserReadHourResponse.class);
        }
        return response;
    }

    /**
     * 获取图文分享转发数据，最大跨度为7天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 图文分享转发数据
     */
    public GetUserShareResponse getUserShare(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUserShareResponse response = null;
        String url = BASE_API_URL + "datacube/getusershare?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUserShareResponse.class);
        }
        return response;
    }

    /**
     * 获取图文分享转发分时数据
     * @param day 查询日期
     * @return 图文分享转发分时数据
     */
    public GetUserShareHourResponse getUserShareHour(Date day) {
        BeanUtil.requireNonNull(day, "day is null");
        GetUserShareHourResponse response = null;
        String url = BASE_API_URL + "datacube/getusersharehour?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(day));
        param.put("end_date", DATE_FORMAT.format(day));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUserShareHourResponse.class);
        }
        return response;
    }

    /**
     * 获取消息发送概况数据，最大跨度为7天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 消息发送概况数据
     */
    public GetUpstreamMsgResponse getUpstreamMsg(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUpstreamMsgResponse response = null;
        String url = BASE_API_URL + "datacube/getupstreammsg?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUpstreamMsgResponse.class);
        }
        return response;
    }

    /**
     * 获取消息分送分时数据
     * @param day 查询日期
     * @return 消息分送分时数据
     */
    public GetUpstreamMsgHourResponse getUpstreamMsgHour(Date day) {
        BeanUtil.requireNonNull(day, "day is null");
        GetUpstreamMsgHourResponse response = null;
        String url = BASE_API_URL + "datacube/getupstreammsghour?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(day));
        param.put("end_date", DATE_FORMAT.format(day));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUpstreamMsgHourResponse.class);
        }
        return response;
    }

    /**
     * 获取消息发送周数据，最大跨度为30天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 消息发送周数据
     */
    public GetUpstreamMsgWeekResponse getUpstreamMsgWeek(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUpstreamMsgWeekResponse response = null;
        String url = BASE_API_URL + "datacube/getupstreammsgweek?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUpstreamMsgWeekResponse.class);
        }
        return response;
    }

    /**
     * 获取消息发送月数据，最大跨度为30天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 消息发送月数据
     */
    public GetUpstreamMsgMonthResponse getUpstreamMsgMonth(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUpstreamMsgMonthResponse response = null;
        String url = BASE_API_URL + "datacube/getupstreammsgmonth?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUpstreamMsgMonthResponse.class);
        }
        return response;
    }

    /**
     * 获取消息发送分布数据，最大跨度为15天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 消息发送分布数据
     */
    public GetUpstreamMsgDistResponse getUpstreamMsgDist(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUpstreamMsgDistResponse response = null;
        String url = BASE_API_URL + "datacube/getupstreammsgdist?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUpstreamMsgDistResponse.class);
        }
        return response;
    }

    /**
     * 获取消息发送分布周数据，最大跨度为30天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 消息发送分布周数据
     */
    public GetUpstreamMsgDistWeekResponse getUpstreamMsgDistWeek(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUpstreamMsgDistWeekResponse response = null;
        String url = BASE_API_URL + "datacube/getupstreammsgdistweek?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUpstreamMsgDistWeekResponse.class);
        }
        return response;
    }

    /**
     * 获取消息发送分布月数据，最大跨度为30天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 消息发送分布月数据
     */
    public GetUpstreamMsgDistMonthResponse getUpstreamMsgDistMonth(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetUpstreamMsgDistMonthResponse response = null;
        String url = BASE_API_URL + "datacube/getupstreammsgdistmonth?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetUpstreamMsgDistMonthResponse.class);
        }
        return response;
    }

    /**
     * 获取接口分析数据，最大跨度为30天
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 接口分析数据
     */
    public GetInterfaceSummaryResponse getInterfaceSummary(Date beginDate, Date endDate) {
        BeanUtil.requireNonNull(beginDate, "beginDate is null");
        BeanUtil.requireNonNull(endDate, "endDate is null");
        GetInterfaceSummaryResponse response = null;
        String url = BASE_API_URL + "datacube/getinterfacesummary?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(beginDate));
        param.put("end_date", DATE_FORMAT.format(endDate));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetInterfaceSummaryResponse.class);
        }
        return response;
    }

    /**
     * 获取接口分析分时数据
     * @param day 查询日期
     * @return 接口分析分时数据
     */
    public GetInterfaceSummaryHourResponse getInterfaceSummaryHour(Date day) {
        BeanUtil.requireNonNull(day, "day is null");
        GetInterfaceSummaryHourResponse response = null;
        String url = BASE_API_URL + "datacube/getinterfacesummaryhour?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("begin_date", DATE_FORMAT.format(day));
        param.put("end_date", DATE_FORMAT.format(day));
        String json = JSONUtil.toJson(param);
        BaseResponse r = executePost(url, json);
        if (StrUtil.isBlank(r.getErrcode())) {
            response = JSONUtil.toBean(r.getErrmsg(), GetInterfaceSummaryHourResponse.class);
        }
        return response;
    }


}
