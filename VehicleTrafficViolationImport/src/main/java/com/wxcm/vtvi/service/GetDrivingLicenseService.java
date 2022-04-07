package com.wxcm.vtvi.service;

import com.wxcm.vtvi.entity.TvqLicense;
import net.sf.json.JSONObject;

import java.util.concurrent.CompletableFuture;

/**
 * @author GZH
 */
public interface GetDrivingLicenseService {

    /**
     * 单次操作使用
     * 访问外部接口获取数据并将结果存入数据库
     * @param sfzh  身份证号码后四位
     * @param dabh  12位档案编号
     * @param num   返回结果为等待数据时，再次调用
     * @return JSONObject   访问机动车违章查询接口返回的结果
     */
    CompletableFuture<JSONObject> getDrivingLicense(String sfzh, String dabh, int num);

    /**
     * 批量操作使用
     * 访问外部接口获取数据并将结果存入数据库
     * @param tvqLicense    TvqLicense
     */
    void reacquireDrivingLicense(TvqLicense tvqLicense);

}
