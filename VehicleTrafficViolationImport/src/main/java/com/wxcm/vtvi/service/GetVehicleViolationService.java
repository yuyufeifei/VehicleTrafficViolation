package com.wxcm.vtvi.service;

import com.wxcm.vtvi.entity.TvqVehicle;
import net.sf.json.JSONObject;

import java.util.concurrent.CompletableFuture;

/**
 * @author GZH
 */
public interface GetVehicleViolationService {

    /**
     * 单次操作使用
     * 访问外部接口获取数据并将结果存入数据库
     * @param hphm  机动车号牌号码
     * @param hpzl  机动车号牌种类
     * @param sbdm  车辆识别代号后4位
     * @param num   返回结果为等待数据时，再次调用
     * @return JSONObject   访问机动车违章查询接口返回的结果
     */
    CompletableFuture<JSONObject> getVehicleViolation(String hphm, String hpzl, String sbdm, int num);

    /**
     * 批量操作使用
     * 访问外部接口获取数据并将结果存入数据库
     * @param tvqVehicle    TvqVehicle
     */
    void reacquireVehicleViolation(TvqVehicle tvqVehicle);

}
