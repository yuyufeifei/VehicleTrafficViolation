package com.wxcm.vtvq.vo.primary;

import com.wxcm.vtvq.entity.primary.TvqVehicle;

import java.util.Date;

/**
 * @author GZH
 * @date 2021-01-05
 */
public class Vehicle {
    private long id;
    private String plate;
    private String platetype;
    private String identification;
    private String status;
    private String msg;
    private String vehiclestatus;
    private String inspectiondate;
    private Integer violationnum;
    private Date updatetime;

    public Vehicle() {
    }

    /**
     * 复制TvqVehicle类 除tvqViolationsById之外的所有字段
     * @param tvqVehicle
     */
    public Vehicle(TvqVehicle tvqVehicle) {
        this.id = tvqVehicle.getId();
        this.plate = tvqVehicle.getPlate();
        this.platetype = tvqVehicle.getPlatetype();
        this.identification = tvqVehicle.getIdentification();
        this.status = tvqVehicle.getStatus();
        this.msg = tvqVehicle.getMsg();
        this.vehiclestatus = tvqVehicle.getVehiclestatus();
        this.inspectiondate = tvqVehicle.getInspectiondate();
        this.violationnum = tvqVehicle.getViolationnum();
        this.updatetime = tvqVehicle.getUpdatetime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPlatetype() {
        return platetype;
    }

    public void setPlatetype(String platetype) {
        this.platetype = platetype;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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

    public String getVehiclestatus() {
        return vehiclestatus;
    }

    public void setVehiclestatus(String vehiclestatus) {
        this.vehiclestatus = vehiclestatus;
    }

    public String getInspectiondate() {
        return inspectiondate;
    }

    public void setInspectiondate(String inspectiondate) {
        this.inspectiondate = inspectiondate;
    }

    public Integer getViolationnum() {
        return violationnum;
    }

    public void setViolationnum(Integer violationnum) {
        this.violationnum = violationnum;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
