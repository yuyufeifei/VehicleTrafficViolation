package com.wxcm.vtvq.vo.primary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wxcm.vtvq.entity.primary.TvqLicense;

/**
 * @author GZH
 * @date 2020-07-15
 */
public class License {
    private String status;
    private String msg;
    private String YXQZ;
    private String SYRQ;
    private Integer LJJF;
    private String ZT;

    public License() {
    }

    public License(TvqLicense tvqLicense) {
        this.status = tvqLicense.getStatus();
        this.msg = tvqLicense.getMsg();
        this.YXQZ = tvqLicense.getExpirydate();
        this.SYRQ = tvqLicense.getVerifydate();
        this.LJJF = tvqLicense.getPoints();
        this.ZT = tvqLicense.getLicensestatus();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonProperty("YXQZ")
    public String getYXQZ() {
        return YXQZ;
    }

    public void setYXQZ(String YXQZ) {
        this.YXQZ = YXQZ;
    }

    @JsonProperty("SYRQ")
    public String getSYRQ() {
        return SYRQ;
    }

    public void setSYRQ(String SYRQ) {
        this.SYRQ = SYRQ;
    }

    @JsonProperty("LJJF")
    public Integer getLJJF() {
        return LJJF;
    }

    public void setLJJF(Integer LJJF) {
        this.LJJF = LJJF;
    }

    @JsonProperty("ZT")
    public String getZT() {
        return ZT;
    }

    public void setZT(String ZT) {
        this.ZT = ZT;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", YXQZ='" + YXQZ + '\'' +
                ", SYRQ='" + SYRQ + '\'' +
                ", LJJF=" + LJJF +
                ", ZT='" + ZT + '\'' +
                '}';
    }
}
