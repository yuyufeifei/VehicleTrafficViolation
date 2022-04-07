package com.wxcm.vtvq.entity.primary;

import javax.persistence.*;
import java.util.Date;

/**
 * @author GZH
 */
@Entity
@Table(name = "tvq_license")
public class TvqLicense {
    private long id;
    private String idnumber;
    private String filenumber;
    private String status;
    private String msg;
    private String expirydate;
    private String verifydate;
    private Integer points;
    private String licensestatus;
    private Date updatetime;

    public TvqLicense() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idnumber", nullable = true, length = 20)
    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    @Basic
    @Column(name = "filenumber", nullable = true, length = 30)
    public String getFilenumber() {
        return filenumber;
    }

    public void setFilenumber(String filenumber) {
        this.filenumber = filenumber;
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
    @Column(name = "expirydate", nullable = true, length = 30)
    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    @Basic
    @Column(name = "verifydate", nullable = true, length = 30)
    public String getVerifydate() {
        return verifydate;
    }

    public void setVerifydate(String verifydate) {
        this.verifydate = verifydate;
    }

    @Basic
    @Column(name = "points", nullable = true)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Basic
    @Column(name = "licensestatus", nullable = true, length = 50)
    public String getLicensestatus() {
        return licensestatus;
    }

    public void setLicensestatus(String licensestatus) {
        this.licensestatus = licensestatus;
    }

    @Basic
    @Column(name = "updatetime", nullable = true)
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
