package com.wxcm.vtvq.entity.secondary;

import javax.persistence.*;

/**
 * @author GZH
 * @date 2021-03-22
 */
@Entity
@Table(name = "apa_license")
public class ApaLicense {
    private long id;
    private long userid;
    private String phone;
    private String idnumber;
    private String filenumber;
    private ApaUser apaUser;

    public ApaLicense() {
    }

    public ApaLicense(long userid, String phone, String idnumber, String filenumber, ApaUser apaUser) {
        this.userid = userid;
        this.phone = phone;
        this.idnumber = idnumber;
        this.filenumber = filenumber;
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
    @Column(name = "idnumber", nullable = false, length = 20)
    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    @Basic
    @Column(name = "filenumber", nullable = false, length = 30)
    public String getFilenumber() {
        return filenumber;
    }

    public void setFilenumber(String filenumber) {
        this.filenumber = filenumber;
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
