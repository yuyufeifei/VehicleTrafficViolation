package com.wxcm.vtvq.controller;

import com.wxcm.vtvq.service.VehicleViolationService;
import com.wxcm.vtvq.util.Constants;
import com.wxcm.vtvq.util.DateUtil;
import com.wxcm.vtvq.vo.primary.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GZH
 */
@RestController
@RequestMapping(value = "/jdcwfcx")
public class VehicleViolationController {

    private final Logger logger = LogManager.getLogger(VehicleViolationController.class);

    @Autowired
    private VehicleViolationService vehicleViolationService;

    @PostMapping(value = "")
    @ResponseBody
    public Object vehicleViolation(@RequestParam String hphm, @RequestParam String hpzl, @RequestParam String sbdm){
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(hphm) && !"".equals(hpzl) && !"".equals(sbdm)) {
            try {
                Vehicle vehicle = vehicleViolationService.getVehicleViolation(hphm, hpzl, sbdm);
                Object object;
                if (vehicle == null || vehicle.getUpdatetime() == null || DateUtil.beforeTodayZero(vehicle.getUpdatetime())) {
                    object = vehicleViolationService.getVehicleViolationByVtvi(hphm, hpzl, sbdm);
                } else {
                    object = vehicleViolationService.getVehicleViolation(hphm, hpzl, sbdm, vehicle);
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
        logger.info("/jdcwfcx param: hphm=" + hphm + " hpzl=" + hpzl + " sbdm=" + sbdm + " ;result: " + result);
        return result;
    }

    @GetMapping("/delCache")
    public String deleteVehicleViolationCache() {
        logger.info("/jdcwfcx/delCache");
        String msg;
        try {
            vehicleViolationService.deleteVehicleViolationCache();
            msg = "OK";
        } catch (Exception e) {
            logger.error(e);
            msg = e.getMessage();
        }
        return msg;
    }

}
