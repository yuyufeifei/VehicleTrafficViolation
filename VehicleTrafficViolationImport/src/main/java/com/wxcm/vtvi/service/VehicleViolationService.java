package com.wxcm.vtvi.service;

import com.wxcm.vtvi.entity.TvqVehicle;
import com.wxcm.vtvi.vo.VehicleViolation;
import net.sf.json.JSONObject;

/**
 * @author GZH
 */
public interface VehicleViolationService {

    /**
     * 保存访问外部接口得到的数据
     * @param hphm  机动车号牌号码
     * @param hpzl  机动车号牌种类
     * @param sbdm  车辆识别代号后4位
     * @param jsonObject    访问机动车违章查询接口返回的结果
     */
    void saveVehicleViolation(String hphm, String hpzl, String sbdm, JSONObject jsonObject);

    /**
     * 保存访问外部接口得到的数据
     * @param tvqVehicle    TvqVehicle
     * @param jsonObject    访问机动车违章查询接口返回的结果
     */
    void saveViolation(TvqVehicle tvqVehicle, JSONObject jsonObject);

    /**
     * 从数据库根据条件查询，并返回结果
     * @param hphm  机动车号牌号码
     * @param hpzl  机动车号牌种类
     * @param sbdm  车辆识别代号后4位
     * @return  VehicleViolation
     */
    VehicleViolation getVehicleViolation(String hphm, String hpzl, String sbdm);

}
