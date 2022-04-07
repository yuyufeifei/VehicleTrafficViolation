package com.wxcm.vtvi.service;

import com.wxcm.vtvi.entity.TvqLicense;
import com.wxcm.vtvi.vo.License;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * @author GZH
 */
public interface DrivingLicenseService {

    /**
     * 保存访问外部接口得到的数据
     * @param sfzh  身份证号码后四位
     * @param dabh  12位档案编号
     * @param jsonObject    访问驾驶证查询接口返回的结果
     */
    void saveDrivingLicense(String sfzh, String dabh, JSONObject jsonObject);

    /**
     * 保存访问外部接口得到的数据
     * @param tvqLicense    TvqLicense
     * @param jsonObject    访问驾驶证查询接口返回的结果
     */
    void saveDrivingLicense(TvqLicense tvqLicense, JSONObject jsonObject);

    /**
     * 从数据库根据条件查询，并返回结果
     * @param sfzh  身份证号码后四位
     * @param dabh  12位档案编号
     * @return  License
     */
    License getDrivingLicense(String sfzh, String dabh);

}
