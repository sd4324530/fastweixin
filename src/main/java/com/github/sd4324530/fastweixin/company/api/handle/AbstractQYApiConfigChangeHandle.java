package com.github.sd4324530.fastweixin.company.api.handle;

import com.github.sd4324530.fastweixin.company.api.config.QYConfigChangeNotice;
import com.github.sd4324530.fastweixin.handle.ApiConfigChangeHandle;
import com.github.sd4324530.fastweixin.util.BeanUtil;

import java.util.Observable;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public abstract class AbstractQYApiConfigChangeHandle implements ApiConfigChangeHandle{

    public void update(Observable o, Object arg){
        if(BeanUtil.nonNull(arg) && arg instanceof QYConfigChangeNotice){
            configChange((QYConfigChangeNotice) arg);
        }
    }

    /**
     * 子类实现，当配置变化时触发该方法
     * @param notice 消息
     */
    public abstract void configChange(QYConfigChangeNotice notice);
}
