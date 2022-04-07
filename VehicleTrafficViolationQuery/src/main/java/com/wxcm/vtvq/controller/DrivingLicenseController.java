package com.wxcm.vtvq.controller;

import com.wxcm.vtvq.entity.primary.TvqLicense;
import com.wxcm.vtvq.service.DrivingLicenseService;
import com.wxcm.vtvq.util.Constants;
import com.wxcm.vtvq.util.DateUtil;
import com.wxcm.vtvq.vo.primary.License;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GZH
 * @date 2020-07-16
 */
@RestController
@RequestMapping(value = "/jszwfcx")
public class DrivingLicenseController {

    private final Logger logger = LogManager.getLogger(DrivingLicenseController.class);

    @Autowired
    private DrivingLicenseService drivingLicenseService;

    @PostMapping(value = "")
    @ResponseBody
    public Object drivingLicense(@RequestParam String sfzh, @RequestParam String dabh){
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(sfzh) && sfzh.length() == 4 && !"".equals(dabh) && dabh.length() == 12) {
            try {
                TvqLicense tvqLicense = drivingLicenseService.getDrivingLicense(sfzh, dabh);
                Object object;
                if(tvqLicense == null || tvqLicense.getUpdatetime() == null || DateUtil.beforeTodayZero(tvqLicense.getUpdatetime())) {
                    object = drivingLicenseService.getDrivingLicenseByVtvi(sfzh, dabh);
                } else {
                    object = new License(tvqLicense);
                    logger.info("从数据库或缓存中查找结果 (sfzh=" + sfzh + " dabh=" + dabh + ")");
                }
                if(object != null) {
                    code = 1;
                    result.put("data", object);
                } else {
                    code = 2;
                }
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/jszwfcx param: sfzh=" + sfzh + " dabh=" + dabh + " ;result: " + result);
        return result;
    }

    @GetMapping("/delCache")
    public String deleteDrivingLicenseCache() {
        logger.info("/jszwfcx/delCache");
        String msg;
        try {
            drivingLicenseService.deleteDrivingLicenseCache();
            msg = "OK";
        } catch (Exception e) {
            logger.error(e);
            msg = e.getMessage();
        }
        return msg;
    }

}
