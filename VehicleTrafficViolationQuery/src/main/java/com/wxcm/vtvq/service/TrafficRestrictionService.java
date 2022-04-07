package com.wxcm.vtvq.service;

import com.wxcm.vtvq.vo.primary.Restriction;

/**
 * @author GZH
 */
public interface TrafficRestrictionService {

    /**
     * 根据city和date查询限行信息
     * @param city  城市
     * @param date  日期
     * @return  Restriction限行信息
     */
    Restriction getRestriction(String city, String date);

    /**
     * 清除所有restriction缓存
     */
    void deleteRestrictionCache();
}
