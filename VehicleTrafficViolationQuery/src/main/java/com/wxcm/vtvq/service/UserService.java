package com.wxcm.vtvq.service;

import com.wxcm.vtvq.vo.secondary.License;
import com.wxcm.vtvq.vo.secondary.Vehicle;

import java.util.List;

/**
 * @author GZH
 */
public interface UserService {

    /**
     * 根据IPTV账号查询已绑定的车牌号
     * @param iptv IPTV账号
     * @return List<ApaVehicle>
     */
    List<Vehicle> getVehiclesByIptv(String iptv);

    /**
     * IPTV账号与车牌绑定
     * @param iptv IPTV账号
     * @param phone 手机号码
     * @param plate 机动车号牌
     * @param newenegry 是否新能源车 1:是 0:否
     * @param platetype 机动车号牌种类
     * @param identification 车辆识别代号后四位
     * @return 绑定结果code
     */
    Integer addVehicle(String iptv, String phone, String plate, String newenegry, String platetype, String identification);

    /**
     * IPTV账号与车牌解绑
     * @param iptv IPTV账号
     * @param plate 机动车号牌
     * @param platetype 机动车号牌种类
     * @param identification 车辆识别代号后四位
     * @return 解绑结果code
     */
    Integer deleteVehicle(String iptv, String plate, String platetype, String identification);

    /**
     * 根据IPTV账号查询已绑定的驾驶证
     * @param iptv IPTV账号
     * @return List<License>
     */
    List<License> getLicensesByIptv(String iptv);

    /**
     * IPTV账号与驾驶证绑定
     * @param iptv IPTV账号
     * @param phone 手机号码
     * @param idnumber 身份证号后四位
     * @param filenumber 12位档案编号
     * @return 绑定结果code
     */
    Integer addLicense(String iptv, String phone, String idnumber, String filenumber);

    /**
     * IPTV账号与驾驶证解绑
     * @param iptv IPTV账号
     * @param idnumber 身份证号后四位
     * @param filenumber 12位档案编号
     * @return 解绑结果code
     */
    Integer deleteLicense(String iptv, String idnumber, String filenumber);

    /**
     * 清除所有user缓存,包括userVehicle、userLicense
     */
    void deleteUserCache();
}
