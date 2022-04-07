package com.wxcm.vtvq.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GZH
 */
public class Constants {

    /**
     * code值对应的msg
     */
    public static final Map<Integer, String> CODE_TO_MSG = new HashMap<Integer, String>() {
        {
            put(-1, "异常！");
            put(0, "参数错误！");
            put(1, "成功！");
            put(2, "暂无数据！");
            put(3, "失败！");
            put(30001, "绑定失败，该账号已绑定此车牌！");
            put(30002, "解绑失败，该账号未绑定此车牌！");
            put(30003, "绑定失败，该账号已绑定此驾驶证！");
            put(30004, "解绑失败，该账号未绑定此驾驶证！");
            put(30005, "绑定失败，机动车号牌、号牌种类和车辆识别代号后四位已被其他IPTV账号绑定！");
            put(30006, "绑定失败，身份证号后四位和档案编号已被其他IPTV账号绑定！");
        }
    };

    /**
     * 访问其他模块查询机动车违章接口
     */
    public static final String VEHICLE_VIOLATION = "vehicleViolation";

    /**
     * 访问其他模块查询驾驶证接口
     */
    public static final String DRIVING_LICENSE = "drivingLicense";


    public static final String CODE = "code";

    public static final String SUCCESS_CODE = "1";

    public static final String DATA = "data";
}
