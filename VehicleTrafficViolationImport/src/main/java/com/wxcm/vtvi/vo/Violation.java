package com.wxcm.vtvi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wxcm.vtvi.entity.TvqViolation;

/**
 * @author GZH
 */
public class Violation {
    private String WFSJ;
    private String WFDZ;
    private String WFXW;
    private String FKJE;
    private Integer KFFS;

    public Violation() {
    }

    public Violation(TvqViolation tvqViolation) {
        this.WFSJ = tvqViolation.getDatetime();
        this.WFDZ = tvqViolation.getPlace();
        this.WFXW = tvqViolation.getAct();
        this.FKJE = tvqViolation.getFine();
        this.KFFS = tvqViolation.getPoints();
    }

    @JsonProperty("WFSJ")
    public String getWFSJ() {
        return WFSJ;
    }

    public void setWFSJ(String WFSJ) {
        this.WFSJ = WFSJ;
    }

    @JsonProperty("WFDZ")
    public String getWFDZ() {
        return WFDZ;
    }

    public void setWFDZ(String WFDZ) {
        this.WFDZ = WFDZ;
    }

    @JsonProperty("WFXW")
    public String getWFXW() {
        return WFXW;
    }

    public void setWFXW(String WFXW) {
        this.WFXW = WFXW;
    }

    @JsonProperty("FKJE")
    public String getFKJE() {
        return FKJE;
    }

    public void setFKJE(String FKJE) {
        this.FKJE = FKJE;
    }

    @JsonProperty("KFFS")
    public Integer getKFFS() {
        return KFFS;
    }

    public void setKFFS(Integer KFFS) {
        this.KFFS = KFFS;
    }

    @Override
    public String toString() {
        return "{" +
                "WFSJ='" + WFSJ + '\'' +
                ", WFDZ='" + WFDZ + '\'' +
                ", WFXW='" + WFXW + '\'' +
                ", FKJE='" + FKJE + '\'' +
                ", KFFS=" + KFFS +
                '}';
    }
}
