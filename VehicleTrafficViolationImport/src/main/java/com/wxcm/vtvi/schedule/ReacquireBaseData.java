package com.wxcm.vtvi.schedule;

import com.wxcm.vtvi.dao.TvqLicenseDao;
import com.wxcm.vtvi.dao.TvqVehicleDao;
import com.wxcm.vtvi.entity.TvqLicense;
import com.wxcm.vtvi.entity.TvqVehicle;
import com.wxcm.vtvi.service.GetDrivingLicenseService;
import com.wxcm.vtvi.service.GetVehicleViolationService;
import com.wxcm.vtvi.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/**
 * @author GZH
 */
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ReacquireBaseData {

    private final Logger logger = LogManager.getLogger(ReacquireBaseData.class);

    @Autowired
    private TvqVehicleDao tvqVehicleDao;
    @Autowired
    private TvqLicenseDao tvqLicenseDao;

    @Autowired
    private GetVehicleViolationService getVehicleViolationService;
    @Autowired
    private GetDrivingLicenseService getDrivingLicenseService;

    /**
     * 每天凌晨1点执行
     */
//    @Scheduled(cron = "0 0 1 * * ?")
    public void reacquireVehicleViolation() {
        logger.info("run in 01:00");
        long start = System.currentTimeMillis();
        List<TvqVehicle> tvqVehicles = tvqVehicleDao.findAll();
        for (TvqVehicle tvqVehicle : tvqVehicles) {
            getVehicleViolationService.reacquireVehicleViolation(tvqVehicle);
        }
        float exc = (float)(System.currentTimeMillis() - start)/1000;
        logger.info("run all time:" + exc);
    }

    /**
     * 每天凌晨00:10点执行
     */
//    @Scheduled(cron = "0 10 0 * * ?")
    public void reacquireDrivingLicense() {
        logger.info("run in 00:10");
        long start = System.currentTimeMillis();
        try {
            String today = DateUtil.format(new Date(), "yyyy-MM-dd");
            List<TvqLicense> tvqLicenses = tvqLicenseDao.findByExpirydate(today);
            for (TvqLicense tvqLicense : tvqLicenses) {
                getDrivingLicenseService.reacquireDrivingLicense(tvqLicense);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        float exc = (float)(System.currentTimeMillis() - start)/1000;
        logger.info("run all time:" + exc);
    }

}
