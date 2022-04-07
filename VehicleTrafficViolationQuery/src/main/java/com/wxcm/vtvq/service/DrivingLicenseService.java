package com.wxcm.vtvq.service;

import com.wxcm.vtvq.entity.primary.TvqLicense;

/**
 * @author GZH
 * @date 2020-07-16
 */
public interface DrivingLicenseService {

    /**
     * 从数据库根据条件查询，并返回结果
     * @param sfzh  身份证号码后四位
     * @param dabh  12位档案编号
     * @return  TvqLicense
     */
    TvqLicense getDrivingLicense(String sfzh, String dabh);

    /**
     *
     * @param sfzh  身份证号码后四位
     * @param dabh  12位档案编号
     * @return  接口返回的数据转成Object
     */
    Object getDrivingLicenseByVtvi(String sfzh, String dabh);

    /**
     * 清除所有drivingLicense缓存
     */
    void deleteDrivingLicenseCache();

}
