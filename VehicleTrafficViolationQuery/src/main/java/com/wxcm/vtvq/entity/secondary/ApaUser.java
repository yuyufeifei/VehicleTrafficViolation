package com.wxcm.vtvq.entity.secondary;

import javax.persistence.*;
import java.util.List;

/**
 * @author GZH
 */
@Entity
@Table(name = "apa_user")
public class ApaUser {
    private long id;
    private String phone;
    private String iptv;
    private String remark;
    private List<ApaVehicle> apaVehicles;
    private List<ApaLicense> apaLicenses;

    public ApaUser() {
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
    @Column(name = "phone", nullable = true, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "iptv", nullable = false, length = 30)
    public String getIptv() {
        return iptv;
    }

    public void setIptv(String iptv) {
        this.iptv = iptv;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 50)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(mappedBy = "apaUser")
    public List<ApaVehicle> getApaVehicles() {
        return apaVehicles;
    }

    public void setApaVehicles(List<ApaVehicle> apaVehicles) {
        this.apaVehicles = apaVehicles;
    }

    @OneToMany(mappedBy = "apaUser")
    public List<ApaLicense> getApaLicenses() {
        return apaLicenses;
    }

    public void setApaLicenses(List<ApaLicense> apaLicenses) {
        this.apaLicenses = apaLicenses;
    }
}
