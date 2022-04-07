package com.wxcm.vtvi.entity;

import javax.persistence.*;

/**
 * @author GZH
 */
@Entity
@Table(name = "tvq_violation")
public class TvqViolation {
    private long id;
    private long vehicleid;
    private String datetime;
    private String place;
    private String act;
    private String fine;
    private Integer points;
    private TvqVehicle tvqVehicleByVehicleid;

    public TvqViolation() {
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
    @Column(name = "vehicleid", nullable = false)
    public long getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(long vehicleid) {
        this.vehicleid = vehicleid;
    }

    @Basic
    @Column(name = "datetime", nullable = true, length = 30)
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "place", nullable = true, length = 100)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "act", nullable = true, length = 300)
    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    @Basic
    @Column(name = "fine", nullable = true, length = 10)
    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    @Basic
    @Column(name = "points", nullable = true)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @ManyToOne
    @JoinColumn(name = "vehicleid", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TvqVehicle getTvqVehicleByVehicleid() {
        return tvqVehicleByVehicleid;
    }

    public void setTvqVehicleByVehicleid(TvqVehicle tvqVehicleByVehicleid) {
        this.tvqVehicleByVehicleid = tvqVehicleByVehicleid;
    }
}
