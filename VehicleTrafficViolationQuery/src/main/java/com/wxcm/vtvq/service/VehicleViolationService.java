package com.wxcm.vtvq.service;

import com.wxcm.vtvq.vo.primary.Vehicle;
import com.wxcm.vtvq.vo.primary.VehicleViolation;

/**
 * @author GZH
 */
public interface VehicleViolationService {

    /**
     * 从数据库根据条件查询，并返回结果
     * @param hphm  号牌号码
     * @param hpzl  号牌种类
     * @param sbdm  识别代码（后四位）
     * @return Vehicle
     */
    Vehicle getVehicleViolation(String hphm, String hpzl, String sbdm);

    /**
     * 从数据库查询数据
     * 前三个参数为缓存key值 在该函数中无实际用途
     * @param hphm  号牌号码
     * @param hpzl  号牌种类
     * @param sbdm  识别代码（后四位）
     * @param vehicle    vehicle
     * @return  VehicleViolation机动车违章信息
     * @throws Exception    初始化类VehicleViolation时可能抛出异常
     */
    VehicleViolation getVehicleViolation(String hphm, String hpzl, String sbdm, Vehicle vehicle) throws Exception;

    /**
     * 访问vtvi接口获取数据
     * @param hphm  号牌号码
     * @param hpzl  号牌种类
     * @param sbdm  识别代码（后四位）
     * @return  接口返回的数据转成Object
     */
    Object getVehicleViolationByVtvi(String hphm, String hpzl, String sbdm);

    /**
     * 清除所有vehicle、vehicleViolation缓存
     */
    void deleteVehicleViolationCache();

}
