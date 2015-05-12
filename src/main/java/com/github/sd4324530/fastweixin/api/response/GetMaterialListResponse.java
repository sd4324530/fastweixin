package com.github.sd4324530.fastweixin.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.message.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class GetMaterialListResponse extends BaseResponse {

    private static final Logger LOG = LoggerFactory.getLogger(GetMaterialListResponse.class);

    @JSONField(name="total_count")
    private int totalCount;// 该类型素材总数
    @JSONField(name="item_count")
    private int itemCount;// 本次获取的数量

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
