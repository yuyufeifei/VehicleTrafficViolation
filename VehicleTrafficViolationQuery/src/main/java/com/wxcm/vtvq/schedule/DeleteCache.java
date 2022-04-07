package com.wxcm.vtvq.schedule;

import com.wxcm.vtvq.service.DrivingLicenseService;
import com.wxcm.vtvq.service.TrafficRestrictionService;
import com.wxcm.vtvq.service.VehicleViolationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author GZH
 * @date 2020-07-21
 */
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class DeleteCache {

    private final Logger logger = LogManager.getLogger(DeleteCache.class);

    @Autowired
    private TrafficRestrictionService trafficRestrictionService;
    @Autowired
    private VehicleViolationService vehicleViolationService;
    @Autowired
    private DrivingLicenseService drivingLicenseService;

    /**
     * 删除限行接口缓存，执行时间为每天00:15和12:15
     */
    @Scheduled(cron = "0 15 0,12 * * ?")
    public void deleteRestrictionCache() {
        logger.info("run deleteRestrictionCache");
        trafficRestrictionService.deleteRestrictionCache();
    }

    /**
     * 删除机动车违章、驾驶证缓存，执行时间为每天00:01
     */
    @Scheduled(cron = "0 1 0 * * ?")
    public void deleteCache() {
        logger.info("run deleteVehicleViolationCache");
        vehicleViolationService.deleteVehicleViolationCache();
        logger.info("run deleteDrivingLicenseCache");
        drivingLicenseService.deleteDrivingLicenseCache();
    }
}
