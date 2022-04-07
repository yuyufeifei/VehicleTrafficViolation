package com.wxcm.vtvi.service;

/**
 * @author GZH
 */
public interface ImportVehicleService {

    /**
     * 从csv文件导入车牌号、车牌类型、车辆识别码后四位至数据库
     * @param path csv文件路径
     * @param charset 文件编码格式
     * @return 是否成功
     */
    boolean importVehicleFromCsv(String path, String charset);

    /**
     * 使用JdbcTemplate从csv文件导入车牌号、车牌类型、车辆识别码后四位至数据库
     * @param path csv文件路径
     * @param charset 文件编码格式
     * @return 是否成功
     */
    boolean importVehicleFromCsvByJdbcTemplate(String path, String charset);

}
