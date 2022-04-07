package com.wxcm.vtvq.vo.secondary;

import com.wxcm.vtvq.entity.secondary.ApaLicense;

/**
 * @author GZH
 * @date 2021-03-22
 */
public class License {
    private String phone;
    private String idnumber;
    private String filenumber;

    public License() {
    }

    public License(ApaLicense apaLicense) {
        this.phone = apaLicense.getPhone();
        this.idnumber = apaLicense.getIdnumber();
        this.filenumber = apaLicense.getFilenumber();
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getFilenumber() {
        return filenumber;
    }

    public void setFilenumber(String filenumber) {
        this.filenumber = filenumber;
    }

    @Override
    public String toString() {
        return "{" +
                "phone='" + phone + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", filenumber='" + filenumber + '\'' +
                '}';
    }
}
