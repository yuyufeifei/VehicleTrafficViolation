package com.wxcm.vtvq.entity.primary;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author GZH
 */
@Entity
@Table(name = "tvq_vehicle")
public class TvqVehicle {
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
    private List<TvqViolation> tvqViolationsById;

    public TvqVehicle() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "plate", nullable = false, length = 20)
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Basic
    @Column(name = "platetype", nullable = false, length = 10)
    public String getPlatetype() {
        return platetype;
    }

    public void setPlatetype(String platetype) {
        this.platetype = platetype;
    }

    @Basic
    @Column(name = "identification", nullable = false, length = 10)
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 5)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "msg", nullable = true, length = 200)
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Basic
    @Column(name = "vehiclestatus", nullable = true, length = 50)
    public String getVehiclestatus() {
        return vehiclestatus;
    }

    public void setVehiclestatus(String vehiclestatus) {
        this.vehiclestatus = vehiclestatus;
    }

    @Basic
    @Column(name = "inspectiondate", nullable = true, length = 20)
    public String getInspectiondate() {
        return inspectiondate;
    }

    public void setInspectiondate(String inspectiondate) {
        this.inspectiondate = inspectiondate;
    }

    @Basic
    @Column(name = "violationnum", nullable = true)
    public Integer getViolationnum() {
        return violationnum;
    }

    public void setViolationnum(Integer violationnum) {
        this.violationnum = violationnum;
    }

    @Basic
    @Column(name = "updatetime", nullable = true)
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @OneToMany(mappedBy = "tvqVehicleByVehicleid")
    public List<TvqViolation> getTvqViolationsById() {
        return tvqViolationsById;
    }

    public void setTvqViolationsById(List<TvqViolation> tvqViolationsById) {
        this.tvqViolationsById = tvqViolationsById;
    }
}
