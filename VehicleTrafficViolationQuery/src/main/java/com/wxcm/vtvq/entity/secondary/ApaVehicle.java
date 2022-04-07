package com.wxcm.vtvq.entity.secondary;

import javax.persistence.*;

/**
 * @author GZH
 */
@Entity
@Table(name = "apa_vehicle")
public class ApaVehicle {
    private long id;
    private long userid;
    private String phone;
    private String plate;
    private String newenegry;
    private String platetype;
    private String identification;
    private ApaUser apaUser;

    public ApaVehicle() {
    }

    public ApaVehicle(long userid, String phone, String plate, String newenegry, String platetype, String identification, ApaUser apaUser) {
        this.userid = userid;
        this.phone = phone;
        this.plate = plate;
        this.newenegry = newenegry;
        this.platetype = platetype;
        this.identification = identification;
        this.apaUser = apaUser;
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
    @Column(name = "userid", nullable = false)
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    @Column(name = "newenegry", nullable = false, length = 10)
    public String getNewenegry() {
        return newenegry;
    }

    public void setNewenegry(String newenegry) {
        this.newenegry = newenegry;
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

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ApaUser getApaUser() {
        return apaUser;
    }

    public void setApaUser(ApaUser apaUser) {
        this.apaUser = apaUser;
    }
}
