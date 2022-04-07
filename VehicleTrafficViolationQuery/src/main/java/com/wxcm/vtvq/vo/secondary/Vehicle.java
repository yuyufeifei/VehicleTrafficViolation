package com.wxcm.vtvq.vo.secondary;

import com.wxcm.vtvq.entity.secondary.ApaVehicle;

/**
 * @author GZH
 */
public class Vehicle {

    private String phone;
    private String plate;
    private String newenegry;
    private String platetype;
    private String identification;

    public Vehicle() {
    }

    public Vehicle(ApaVehicle apaVehicle) {
        this.phone = apaVehicle.getPhone();
        this.plate = apaVehicle.getPlate();
        this.newenegry = apaVehicle.getNewenegry();
        this.platetype = apaVehicle.getPlatetype();
        this.identification = apaVehicle.getIdentification();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getNewenegry() {
        return newenegry;
    }

    public void setNewenegry(String newenegry) {
        this.newenegry = newenegry;
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

    @Override
    public String toString() {
        return "{" +
                "phone='" + phone + '\'' +
                ", plate='" + plate + '\'' +
                ", newenegry='" + newenegry + '\'' +
                ", platetype='" + platetype + '\'' +
                ", identification='" + identification + '\'' +
                '}';
    }
}
