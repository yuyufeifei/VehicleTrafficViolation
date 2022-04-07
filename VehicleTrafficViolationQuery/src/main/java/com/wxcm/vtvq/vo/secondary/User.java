package com.wxcm.vtvq.vo.secondary;

import com.wxcm.vtvq.entity.secondary.ApaUser;

/**
 * @author GZH
 */
public class User {

    private long id;
    private String phone;
    private String iptv;
    private String remark;

    public User() {
    }

    public User(ApaUser apaUser) {
        this.id = apaUser.getId();
        this.phone = apaUser.getPhone();
        this.iptv = apaUser.getIptv();
        this.remark = apaUser.getRemark();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIptv() {
        return iptv;
    }

    public void setIptv(String iptv) {
        this.iptv = iptv;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", iptv='" + iptv + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
