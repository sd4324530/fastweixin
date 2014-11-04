package com.github.sd4324530.fastweixin.api.response;

import java.io.Serializable;

/**
 * @author peiyu
 */
public class GetUsersResponse extends BaseResponse {

    private long total;

    private int count;

    private Openid data;

    private String next_openid;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Openid getData() {
        return data;
    }

    public void setData(Openid data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    public class Openid implements Serializable {
        private String [] openid;

        public String[] getOpenid() {
            return openid;
        }

        public void setOpenid(String[] openid) {
            this.openid = openid;
        }
    }
}
