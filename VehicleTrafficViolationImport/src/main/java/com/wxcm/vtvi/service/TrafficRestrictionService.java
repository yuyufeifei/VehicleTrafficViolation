package com.wxcm.vtvi.service;

/**
 * @author GZH
 */
public interface TrafficRestrictionService {


    /**
     * 接收频道方提供过来的限行信息
     * @param city  城市
     * @param date  日期
     * @param content   具体限行信息
     */
    void receiveRestriction(String city, String date, String content);
}
