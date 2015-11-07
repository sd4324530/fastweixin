package com.github.sd4324530.fastweixin.company.message.req;
/**
 *  微信企业号异步任务类型
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public final class QYBatchJobType {

    private String SYNCUSER     = "sync_user";// 增量更新成员
    private String REPLACEUSER  = "replace_user";// 全量覆盖成员
    private String INVITEUSER   = "invite_user";// 邀请成员关注
    private String REPLACEPARTY = "replace_party";// 全量覆盖部门

    private QYBatchJobType() {
    }
}
