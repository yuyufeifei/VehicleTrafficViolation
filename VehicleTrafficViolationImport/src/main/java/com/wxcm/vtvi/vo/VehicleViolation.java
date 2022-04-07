package com.wxcm.vtvi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wxcm.vtvi.entity.TvqVehicle;
import com.wxcm.vtvi.util.DateUtil;

import java.util.List;

/**
 * @author GZH
 */
public class VehicleViolation {
    private String status;
    private String msg;
    private String ZT;
    private String YXQZ;
    private Integer WFNUM;
    private List<Violation> WFXX;
    private String updateTime;

    public VehicleViolation() {
    }

    public VehicleViolation(TvqVehicle tvqVehicle) throws Exception {
        this.status = tvqVehicle.getStatus();
        this.msg = tvqVehicle.getMsg();
        this.ZT = tvqVehicle.getVehiclestatus();
        this.YXQZ = tvqVehicle.getInspectiondate();
        this.WFNUM = tvqVehicle.getViolationnum();
        this.updateTime = DateUtil.format(tvqVehicle.getUpdatetime(), "yyyy-MM-dd HH:mm:ss");
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

    @JsonProperty("ZT")
    public String getZT() {
        return ZT;
    }

    public void setZT(String ZT) {
        this.ZT = ZT;
    }

    @JsonProperty("YXQZ")
    public String getYXQZ() {
        return YXQZ;
    }

    public void setYXQZ(String YXQZ) {
        this.YXQZ = YXQZ;
    }

    @JsonProperty("WFNUM")
    public Integer getWFNUM() {
        return WFNUM;
    }

    public void setWFNUM(Integer WFNUM) {
        this.WFNUM = WFNUM;
    }

    @JsonProperty("WFXX")
    public List<Violation> getWFXX() {
        return WFXX;
    }

    public void setWFXX(List<Violation> WFXX) {
        this.WFXX = WFXX;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", ZT='" + ZT + '\'' +
                ", YXQZ='" + YXQZ + '\'' +
                ", WFNUM=" + WFNUM +
                ", WFXX=" + WFXX +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
