package com.wxcm.vtvq.vo.primary;

import com.wxcm.vtvq.entity.primary.TrmRestriction;

/**
 * @author GZH
 */
public class Restriction {
    private String city;
    private String date;
    private String content;

    public Restriction() {
    }

    public Restriction(TrmRestriction trmRestriction) {
        this.city = trmRestriction.getCity();
        this.date = trmRestriction.getDate();
        this.content = trmRestriction.getContent();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
