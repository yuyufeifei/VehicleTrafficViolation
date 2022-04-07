package com.wxcm.vtvi.controller;

import com.wxcm.vtvi.service.GetVehicleViolationService;
import com.wxcm.vtvi.service.VehicleViolationService;
import com.wxcm.vtvi.util.Constants;
import com.wxcm.vtvi.vo.VehicleViolation;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author GZH
 */
@RestController
public class VehicleViolationController {

    private final Logger logger = LogManager.getLogger(VehicleViolationController.class);

    @Autowired
    private GetVehicleViolationService getVehicleViolationService;
    @Autowired
    private VehicleViolationService vehicleViolationService;

    @RequestMapping(value = "/vehicleViolation", method = RequestMethod.POST)
    public Object vehicleViolation(@RequestParam String hphm, @RequestParam String hpzl, @RequestParam String sbdm) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(hphm) && !"".equals(hpzl) && !"".equals(sbdm)) {
            CompletableFuture<JSONObject> jsonObjectCF = getVehicleViolationService.getVehicleViolation(hphm, hpzl, sbdm, 1);
            try {
                JSONObject jsonObject = jsonObjectCF.get();
                if(jsonObject != null) {
                    VehicleViolation vehicleViolation = vehicleViolationService.getVehicleViolation(hphm, hpzl, sbdm);
                    code = 1;
                    result.put("rawData", jsonObject);
                    result.put("data", vehicleViolation);
                }
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/vehicleViolation param: hphm=" + hphm + " hpzl=" + hpzl + " sbdm=" + sbdm + " ;result: " + result);
        return result;
    }
}
