package com.wxcm.vtvi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GZH
 */
public class Constants {

    // https访问方式（带域名）

    public static final String USER_NAME = "sjzpptv";
    public static final String PASSWORD = "Y9t-g_rf.@PkF48W";
    /**
     * 地址：https://inter.hbgajg.com/  IP：222.222.29.24:443
     */
    public static final String URL_IP_PORT = "https://inter.hbgajg.com/";
    /**
     * 获取session接口
     */
    public static final String SESSION_URL = "user/inter/login";
    /**
     * 获取机动车及违章信息接口
     */
    public static final String VEHICLE_URL = "user/inter/jdcwfcx";
    /**
     * 获取驾驶证接口
     */
    public static final String LICENSE_URL = "user/inter/jszwfcx";

    // http访问方式（IP地址）

    /**
     * 获取机动车及违章信息接口
     */
    public static final String IP_VEHICLE_URL = "http://222.222.29.21:10055/json/wf/jdc";
    /**
     * 单位ID
     */
    public static final String COMPANY_ID = "";
    /**
     * 头加密码
     */
    public static final String PREFIX_KEY = "";
    /**
     * 尾加密码
     */
    public static final String SUFFIX_KEY = "";

    // http访问方式（IP地址）（新）

    public static final String APP_NO = "20";
    public static final String APP_KEY = "ZER1P9DHT8YV0CCAYZFL5F2GNJXUTP3E";
    /**
     * 获取token
     */
    public static final String TOKEN_URL = "http://222.222.29.21:10055/user/inter_auth/getAccessToken";
    /**
     * 机动车违法查询接口
     */
    public static final String HTTP_VEHICLE_URL = "http://222.222.29.21:10055/user/inter_jdcjsr/info_jdc";
    /**
     * 驾驶证查询接口
     */
    public static final String HTTP_LICENSE_URL = "http://222.222.29.21:10055/user/inter_jdcjsr/info_jsz";

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
        }
    };


    /**
     * 返回信息码
     */
    public static final String RESPONSE_STATUS = "status";
    /**
     * 信息查询成功
     */
    public static final String RESPONSE_STATUS_SUCCESS = "1";
    /**
     * 信息查询成功，等待数据回传中。
     */
    public static final String RESPONSE_STATUS_WAIT = "-1";
    /**
     * 信息查询成功，等待数据回传中。 http接口
     */
    public static final String RESPONSE_STATUS_WAIT_H = "2";
    /**
     * 信息查询失败
     */
    public static final String RESPONSE_STATUS_FAILED = "0";
    /**
     * 返回信息解释 status为0或-1时出现
     */
    public static final String RESPONSE_MSG = "msg";
    /**
     * 返回数据
     */
    public static final String RESPONSE_DATA = "data";
    /**
     * 车辆状态
     */
    public static final String RESPONSE_ZT = "ZT";
    /**
     * 检车日期，有效期至；驾驶证有效期至
     */
    public static final String RESPONSE_YXQZ = "YXQZ";
    /**
     * 违法数量
     */
    public static final String RESPONSE_WFNUM = "WFNUM";
    /**
     * 违法信息 Array
     */
    public static final String RESPONSE_WFXX = "WFXX";
    /**
     * 违法时间
     */
    public static final String RESPONSE_WFSJ = "WFSJ";
    /**
     * 违法地址
     */
    public static final String RESPONSE_WFDZ = "WFDZ";
    /**
     * 违法行为
     */
    public static final String RESPONSE_WFXW = "WFXW";
    /**
     * 罚款金额
     */
    public static final String RESPONSE_FKJE = "FKJE";
    /**
     * 扣分分数
     */
    public static final String RESPONSE_KFFS = "KFFS";
    /**
     * 驾驶证累积记分
     */
    public static final String RESPONSE_LJJF = "LJJF";
    /**
     * 驾驶证审验日期
     */
    public static final String RESPONSE_SYRQ = "SYRQ";

    /**
     * 重新访问接口延迟时间
     */
    public static final long SLEEP_TIME = 12000L;

}
