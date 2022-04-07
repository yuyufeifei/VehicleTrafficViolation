package com.wxcm.vtvi.controller;

import com.wxcm.vtvi.service.DrivingLicenseService;
import com.wxcm.vtvi.service.GetDrivingLicenseService;
import com.wxcm.vtvi.util.Constants;
import com.wxcm.vtvi.vo.License;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author GZH
 */
@RestController
public class DrivingLicenseController {

    private final Logger logger = LogManager.getLogger(DrivingLicenseController.class);

    @Autowired
    private GetDrivingLicenseService getDrivingLicenseService;
    @Autowired
    private DrivingLicenseService drivingLicenseService;

    @PostMapping(value = "/drivingLicense")
    public Object drivingLicense(@RequestParam String sfzh, @RequestParam String dabh) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(sfzh) && sfzh.length() == 4 && !"".equals(dabh) && dabh.length() == 12) {
            CompletableFuture<JSONObject> jsonObjectCF = getDrivingLicenseService.getDrivingLicense(sfzh, dabh, 1);
            try {
                JSONObject jsonObject = jsonObjectCF.get();
                if(jsonObject != null) {
                    License license = drivingLicenseService.getDrivingLicense(sfzh, dabh);
                    code = 1;
                    result.put("rawData", jsonObject);
                    result.put("data", license);
                }
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/drivingLicense param: sfzh=" + sfzh + " dabh=" + dabh + " ;result: " + result);
        return result;
    }

}
